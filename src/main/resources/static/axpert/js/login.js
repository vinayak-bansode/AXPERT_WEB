function registerNavigate(bool) {
    if (bool) {

        window.location.href = "registerpage.html";
    }
}

function showAlert(message, color) {
    var alertDiv = document.createElement("div");
    alertDiv.style.position = "fixed";
    alertDiv.style.top = "0";
    alertDiv.style.height = "50px";
    alertDiv.style.width = "250px";
    alertDiv.style.right = "0";
    alertDiv.style.background = color;
    alertDiv.style.color = "#fff";
    alertDiv.style.padding = "10px";
    alertDiv.style.borderRadius = "10px";
    alertDiv.style.zIndex = "1000";
    alertDiv.textContent = message;
    document.body.appendChild(alertDiv);

    // Remove the alert after a certain period (e.g., 5 seconds)
    setTimeout(function () {
        document.body.removeChild(alertDiv);
    }, 5000);
}

function login() {
    // Get the values from the input fields
    var email = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    // Create a new XMLHttpRequest object
    var xhr = new XMLHttpRequest();

    // Configure it: specify the type of request and the URL
    xhr.open("POST", "/auth/login", true);

    // Set the request header if needed
    xhr.setRequestHeader("Content-Type", "application/json");

    // Set up a callback function to handle the response
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Request was successful
                var response = JSON.parse(xhr.responseText);

                if (response.status === true) {
                    // Login successful
                    console.log("Login successful!");
                    // Redirect to the welcome.html after successful login
                    window.location.href = "welcome.html";
                } else {
                    // Login unsuccessful
                    console.log("Login failed. Error message: " + response.errorMessage);
                    // Handle failed login (e.g., display an error message to the user)
                    showAlert("Login failed. " + response.errorMessage, "red");
                }
            } else {
                // Request failed
                console.log("Login failed. Status code: " + xhr.status);
                // Handle failed login (e.g., show an error message to the user)
                showAlert("Login failed. Please try again.", "red");
            }
        }
    };

    // Prepare the data to be sent in the request body
    var data = {
        email: email,
        password: password
    };

    // Convert the data to JSON format
    var jsonData = JSON.stringify(data);

    // Send the request with the JSON data
    xhr.send(jsonData);
}
