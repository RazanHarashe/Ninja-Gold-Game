<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ninja Gold Game</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
        }
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .gold {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .activities {
            margin-top: 20px;
            width: 50%;
        }
        .activities ul {
            list-style-type: none;
            padding: 0;
        }
        .activities li {
            margin: 5px 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Ninja Gold Game</h1>
        <div class="gold">
            Your Gold: <span><%= session.getAttribute("gold") != null ? session.getAttribute("gold") : 0 %></span>
        </div>
        <div>
            <form action="/process" method="post">
                <button type="submit" name="action" value="farm">Farm (earns 10-20 gold)</button>
            </form>
            <form action="/process" method="post">
                <button type="submit" name="action" value="cave">Cave (earns 5-10 gold)</button>
            </form>
            <form action="/process" method="post">
                <button type="submit" name="action" value="house">House (earns 2-5 gold)</button>
            </form>
            <form action="/process" method="post">
                <button type="submit" name="action" value="quest">Quest (earns/takes 0-50 gold)</button>
            </form>
            <form action="/process" method="post">
                <button type="submit" name="action" value="spa">Spa (loses 5-20 gold)</button>
            </form>
        </div>
        <div class="activities">
            <h2>Activities:</h2>
            <ul>
                <%
                    java.util.List<String> activities = (java.util.List<String>) session.getAttribute("activities");
                    if (activities != null) {
                        for (String activity : activities) {
                %>
                    <li><%= activity %></li>
                <%
                        }
                    }
                %>
            </ul>
        </div>
    </div>
</body>
</html>
