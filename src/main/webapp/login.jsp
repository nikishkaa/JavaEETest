<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 28.08.2024
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
</head>
<body>

<div class="main">
    <h1>GeeksforGeeks</h1>
    <h3>Enter your login credentials</h3>
    <!--    POST - Secure way to send credentials to the server (Back end) -->
    <form action="login" method="post">

        <label for="email">
            Email:
        </label>
        <input type="text"
               id="email"
               name="email"
               placeholder="Enter your Email" required>

        <label for="psw">
            Password:
        </label>
        <input type="password"
               id="psw"
               name="psw"
               placeholder="Enter your Password" required>

        <div class="wrap">
            <button type="submit">
                Submit
            </button>
        </div>
    </form>
    <p>Not registered?
        <a href="#"
           style="text-decoration: none;">
            Create an account
        </a>
    </p>
</div>

<style>
    body {
        display: flex;
        align-items: center;
        justify-content: center;
        font-family: sans-serif;
        line-height: 1.5;
        min-height: 100vh;
        background: #f3f3f3;
        flex-direction: column;
        margin: 0;
    }

    .main {
        background-color: #fff;
        border-radius: 15px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
        padding: 10px 20px;
        transition: transform 0.2s;
        width: 500px;
        text-align: center;
    }

    h1 {
        color: #4CAF50;
    }

    label {
        display: block;
        width: 100%;
        margin-top: 10px;
        margin-bottom: 5px;
        text-align: left;
        color: #555;
        font-weight: bold;
    }


    input {
        display: block;
        width: 100%;
        margin-bottom: 15px;
        padding: 10px;
        box-sizing: border-box;
        border: 1px solid #ddd;
        border-radius: 5px;
    }

    button {
        padding: 15px;
        border-radius: 10px;
        margin-top: 15px;
        margin-bottom: 15px;
        border: none;
        color: white;
        cursor: pointer;
        background-color: #4CAF50;
        width: 100%;
        font-size: 16px;
    }

    .wrap {
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>

</body>
</html>
