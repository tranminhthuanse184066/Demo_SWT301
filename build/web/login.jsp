<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body {
                font-family: Arial, sans-serif;
                background: url('https://images2.thanhnien.vn/zoom/686_429/Uploaded/chungntk/2021_03_14/6b69ed2bb2b341ed18a2_EXOB.jpg') no-repeat center center fixed;
                background-size: cover;
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;

            }

            h1 {
                color: #333;
                margin-bottom: 20px;
                background-color: rgba(255, 255, 255, 0.8);
                padding: 10px;
                border-radius: 5px;
            }

            .container {
                background-color: rgba(255, 255, 255, 0.8);
                padding: 20px;
                border-radius: 25px;
                width: 300px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                text-align: center;
                border: 1px solid #ccc;
            }

            form {
                display: flex;
                flex-direction: column;
                align-items: center;

            }

            input[type="text"], input[type="password"] {
                margin: 10px 0;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                width: 100%;
            }

            input[type="submit"], input[type="reset"] {
                margin: 10px 0;
                padding: 10px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                width: 100%;
            }

            input[type="submit"]:hover, input[type="reset"]:hover {
                background-color: #0056b3;
            }

            a {
                display: block;
                margin: 10px 0;
                color: #007bff;
                text-decoration: none;
                text-align: center;
            }

            a:hover {
                text-decoration: underline;
            }

            #error {
                color: red;
                margin-top: 10px;
            }

            .g-recaptcha {
                margin: 10px 0;
                display: flex;
                justify-content: center;
                width: 100%;
            }

        </style>
    </head>

    <body>
        <div class="container">
            <h1>Login Information</h1>
            <form action="MainController" method="POST" id="form">
                User ID<input type="text" name="userID">
                <br> Password<input type="password" name="password">

                <div id="error">${requestScope.ERROR}</div>
                <input type="hidden" name="search" value="">
                <div class="g-recaptcha" data-sitekey="6LfaWQkqAAAAAHM5I2B2Na_49OHNH3yMY5EJpRk1"></div>
                <br> <input type="hidden" name="action" value="Login">
                <input type="submit" value="Submit">
                <input type="reset" value="Reset">
            </form>
            <a href="https://accounts.google.com/o/oauth2/auth?scope=profile&redirect_uri=http://localhost:8084/PRJ301-T4S4-JSP_V2_JSTL/LoginGoogleController&response_type=code&client_id=295697103075-tadgilba9vmvhodcfrl520e52arhdstc.apps.googleusercontent.com&approval_prompt=force">Login Google</a>
            <a href="MainController?action=Create_Page">Create User</a>
            <a href="MainController?action=Search_Product&search=">Shopping</a>
            <a href="MainController?action=Top1">Top 1</a>
        </div>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script>
            window.onload = function () {
                let isValid = false;
                const form = document.getElementById('form');
                const error = document.getElementById('error');

                form.addEventListener('submit', function (event) {
                    event.preventDefault();
                    const response = grecaptcha.getResponse();
                    if (response) {
                        form.submit();
                    } else {
                        error.innerHTML = "Xac thuc that bai";
                    }
                });
            }
        </script>
    </body>

</html>
