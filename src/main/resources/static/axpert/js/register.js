function register() {
    // Get input values
    var fname = document.getElementById("fname").value;
    var lname = document.getElementById("lname").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var country = document.getElementById("country").value;


    // Create an object with the input values
    var data = {
        name: fname + lname,
        email: username,
        password: password,
        authtype: "WEB",
        country: country
    };

    // Create a new XMLHttpRequest object
    var xhr = new XMLHttpRequest();

    // Configure it: specify the type of request and the URL
    xhr.open("POST", "/auth/register", true);

    // Set the request header
    xhr.setRequestHeader("Content-Type", "application/json");

    // Set up a callback function to handle the response
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
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
                // Registration failed
                console.log("Registration failed. Status code: " + xhr.status);
                // Handle failed registration (e.g., show an error message to the user)
            }
        }
    };

    // Convert the data to JSON format
    var jsonData = JSON.stringify(data);

    // Send the request with the JSON data
    xhr.send(jsonData);

}
