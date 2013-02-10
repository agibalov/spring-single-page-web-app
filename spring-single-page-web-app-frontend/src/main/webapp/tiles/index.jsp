<!DOCTYPE html>
<html ng-app="app">
    <head>
        <title></title>
        <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
        <script src="http://cdnjs.cloudflare.com/ajax/libs/angular.js/1.1.1/angular.min.js"></script>
        
        <script type="text/javascript" src="/static/js/app.js"></script>
        <script type="text/javascript" src="/static/js/api.js"></script>
        <script type="text/javascript" src="/static/js/sign-up-controller.js"></script>
        <script type="text/javascript" src="/static/js/index-controller.js"></script>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="page-header">
					<h1>Hi there</h1>
				</div>
			</div>		
			<ng-view></ng-view>
		</div>
	</body>
</html>