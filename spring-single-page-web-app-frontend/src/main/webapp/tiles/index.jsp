<!DOCTYPE html>
<html ng-app="app">
    <head>
        <title></title>
        <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
        <script src="http://cdnjs.cloudflare.com/ajax/libs/angular.js/1.1.1/angular.min.js"></script>
        
        <script type="text/javascript" src="/static/js/app.js"></script>
        <script type="text/javascript" src="/static/js/api.js"></script>
        <script type="text/javascript" src="/static/js/controllers/chrome-controller.js"></script>
        <script type="text/javascript" src="/static/js/controllers/sign-up-controller.js"></script>
        <script type="text/javascript" src="/static/js/controllers/sign-in-controller.js"></script>
        <script type="text/javascript" src="/static/js/controllers/index-controller.js"></script>
        <script type="text/javascript" src="/static/js/controllers/create-post-controller.js"></script>
        <script type="text/javascript" src="/static/js/controllers/get-post-controller.js"></script>
        <script type="text/javascript" src="/static/js/controllers/edit-post-controller.js"></script>
        <script type="text/javascript" src="/static/js/widgets/user-name-input-widget.js"></script>
        <script type="text/javascript" src="/static/js/widgets/password-input-widget.js"></script>
        
        <script type="text/javascript">
        	angular.module("app").constant("apiServiceRoot", "/api/BlogService/");
        </script>
	</head>
	<body ng-controller="ChromeController">
		<div class="container">
			<div class="row">
				<div class="page-header">
					<h1>Single Page Blogger ({{user.userName}})</h1>
				</div>
			</div>		
			<ng-view></ng-view>
		</div>
	</body>
</html>