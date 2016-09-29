<!DOCTYPE html>
<html ng-app="carApp">
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js" type="text/javascript"></script>
    <script src="http://angular-ui.github.io/ui-router/release/angular-ui-router.min.js" type="text/javascript"></script>
    <script src="/js/ui-bootstrap-tpls-2.1.4.min.js" type="text/javascript"></script>

    <link rel="stylesheet" type="text/css" href="/css/angular-growl.css"/>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-theme.css"/>
    <link rel="stylesheet" type="text/css" href="/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>

    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>

<div ng-controller="appCtrl">
    <div ng-show="userName">
        Dzień dobry {{userName}} :)<br/>
        <button ng-click="logout()">WYLOGUJ</button>
    </div>
    <div ng-show="!userName">
        Witaj Gość.
    </div>
    <hr/>
    <div ui-view>
        Loading...
    </div>
</div>

<hr/>

<example-directive kolorek="#8b0000">to jest napis wewnatrz znacznika</example-directive>

<script src="/js/application.js" type="text/javascript"></script>
<script src="/js/controller/app_controller.js" type="text/javascript"></script>
<script src="/js/controller/login_controller.js" type="text/javascript"></script>
<script src="/js/controller/modal_controller.js" type="text/javascript"></script>
<script src="/js/controller/car_controller.js" type="text/javascript"></script>
<script src="/js/service/car_service.js" type="text/javascript"></script>
<script src="/js/directive/example.js" type="text/javascript"></script>

</body>
</html>

