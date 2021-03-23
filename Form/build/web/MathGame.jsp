<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <style>
            body{
                background-repeat: no-repeat;
                background-size: cover;
                margin: auto;
                text-align: center;
            }
            header{
                margin: auto auto;
                width: 600px;
                height: 50px;
                background-image: linear-gradient(#ffff00, #ff0000);
                box-shadow: 0px 8px 6px -1px #f2000d;
                color: #ffffff;
            }
            #expression{
                margin: auto;
                padding-top: 6px;
                width: 120px;
                height: 35px;
                border-radius: 25px;
                background-color: #f2000d;
                border: 3px solid #ffff00;
                box-shadow: 0px 8px 6px -1px red;
                color: #ffffff;
            }
            #con{
                text-transform: uppercase;
                word-spacing: 4px;
                color: #ff0000;
                font-size: larger;
                font-weight: 900;
            }
            input{
                width: 150px;
                height: 30px;
                font-size: large;
                font-weight: 700;
                text-align: center;
                border-radius: 25px;
                outline: none;
            }
            button, #btn{
                width: 150px;
                height: 35px;
                font-size: medium;
                font-weight: 900;
                color: #05bb60;
                border-radius: 25px;
                border: 3px groove #ffffff;
                box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.7);
                cursor: pointer;
                outline: none;
            }
            #output{
                font-size: x-large;
                font-weight: 900;
                color: #05bb60;
            }
            #streak, #Bstreak{
                font-size: x-large;
                font-weight: 900;
                color: #0040ff;
            }
        </style>
        <title>Math Game</title>
    </head>
    <body background="background.jpg">
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0
            response.setHeader("Expires", "0"); // Proxy servers
            
            if(session.getAttribute("userName")==null){
                response.sendRedirect("index.html");
            }
        %>
        
        <div style="text-align:right; margin: 8px 8px; font-weight: 600; font-size: 20px">
            ${userName}&nbsp;
            <img src = "./UserImage" width="25" height="25" style="border-radius: 50%" />
        </div>
        <header><h1>Welcome to Math Game</h1></header>
        <br>
        <h2 id="expression">
        </h2>
        <br>
        <p id="con">
            Your answer:
            <input id = "answer">
            <button type = "button" onclick = "addition()">Submit</button><br><br>
            <div id = "output"></div>
        </p>
        <br>
        <p id="streak">Current Streak: 0</p>
        <p id="Bstreak">Best Streak: <span id="highScore">${highScore}</span></p>
        <br><br><br>
        <form action="SignOut">
            <input type="submit" value="Sign Out" id="btn">
        </form>
        <script type="text/javascript" src = "MathGameJS.js"></script>
    </body>
</html>