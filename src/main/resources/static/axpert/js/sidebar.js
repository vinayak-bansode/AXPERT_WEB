document.addEventListener("DOMContentLoaded", function () {
    const profileIcon = document.querySelector(".profile-icon");
    const profileDropdown = document.querySelector(".profile-dropdown-content");
    const shrinkBtn = document.querySelector('.shrink-btn');
    const search = document.querySelector('.search');
    const sidebarLinks = document.querySelectorAll('.sidebar-links a');
    const activeTab = document.querySelector('.active-tab');
    const shortcuts = document.querySelector('.sidebar-links h4');
    const tooltipElements = document.querySelectorAll('.tooltip-element');
    const dashboardContent = document.getElementById('dashboard-content');

    let activeIndex;

    shrinkBtn.addEventListener('click', () => {
        document.body.classList.toggle('shrink');
        setTimeout(moveActiveTab, 400);

        shrinkBtn.classList.add('hovered');

        setTimeout(() => {
            shrinkBtn.classList.remove('hovered');
        }, 500);
    });

    search.addEventListener('click', () => {
        document.body.classList.remove('shrink');
        search.lastElementChild.focus();
    });

    function moveActiveTab() {
        let topPosition = activeIndex * 58 + 2.5;

        if (activeIndex > 5) {
            topPosition += shortcuts.clientHeight;
        }

        activeTab.style.top = `${topPosition}px`;
        if (activeTab === 2) {
            // Modify this part if needed
        }
    }

    function changeLink() {
        sidebarLinks.forEach((sideLink) => sideLink.classList.remove('active'));
        this.classList.add('active');

        activeIndex = this.dataset.active;

        moveActiveTab();

        // Update content based on the clicked tab
        updateContent(activeIndex);
    }

    function updateContent(tabIndex) {
        // Hide all content sections initially
        document.querySelectorAll('.content-section').forEach(section => {
            section.style.display = 'none';
        });

        // Set default content
        dashboardContent.innerHTML = `
            <h1>My Dashboard</h1>
            <p class="text">
                Default content for the Dashboard tab.
            </p>
        `;

        // Update content based on the clicked tab
        if (tabIndex === "1") {
            dashboardContent.innerHTML = `
                <h1>My Dashboard</h1>
                <p class="text">
                    Content specific to the Dashboard tab.
                </p>
            `;
        } else if (tabIndex == "7") {
            dashboardContent.innerHTML = `
                <h1>Raj</h1>
                <p class="text">
                    Content specific to the Raj tab.
                </p>
            `;
        }
    }

    sidebarLinks.forEach((link) => link.addEventListener('click', changeLink));

    // Call updateContent with a default index when the page loads
    updateContent("0");

    document.addEventListener("click", function (event) {
        if (!event.target.matches(".profile-icon") && !event.target.closest(".profile-dropdown")) {
            profileDropdown.classList.remove("show");
        }
    });

    profileIcon.addEventListener("click", function (event) {
        event.stopPropagation(); // Prevent the click event from propagating to document

        // Toggle the visibility of the profile dropdown
        profileDropdown.classList.toggle("show");
    });
});
