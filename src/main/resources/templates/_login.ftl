<!DOCTYPE html>
<html ng-app="carApp">
<head>
    <title>Login</title>
</head>
<body>
<div>
    looogin site:
    <form name="form" action="/login" method="POST">
        <fieldset>
            <input type="text" name="username" value="" placeholder="Username"/>
            <input type="password" name="password" placeholder="Password"/>
        </fieldset>
        <input type="submit" id="login" value="Login"/>
    </form>
</div>
</body>
</html>