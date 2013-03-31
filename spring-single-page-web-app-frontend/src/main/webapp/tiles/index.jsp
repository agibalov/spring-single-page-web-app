<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html ng-app="app">
    <head>
        <title></title>
        <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
        <script src="http://code.angularjs.org/1.1.3/angular.min.js"></script>
        <script src="http://code.angularjs.org/1.1.3/angular-cookies.min.js"></script>
        
        <script type="text/javascript" src="<c:url value="/static/js/app.js" />"></script>
        <script type="text/javascript" src="<c:url value="/static/js/api.js" />"></script>
        <script type="text/javascript" src="<c:url value="/static/js/controllers/chrome-controller.js" />"></script>
        <script type="text/javascript" src="<c:url value="/static/js/controllers/sign-up-controller.js" />"></script>
        <script type="text/javascript" src="<c:url value="/static/js/controllers/sign-in-controller.js" />"></script>
        <script type="text/javascript" src="<c:url value="/static/js/controllers/index-controller.js" />"></script>
        <script type="text/javascript" src="<c:url value="/static/js/controllers/create-post-controller.js" />"></script>
        <script type="text/javascript" src="<c:url value="/static/js/controllers/get-post-controller.js" />"></script>
        <script type="text/javascript" src="<c:url value="/static/js/controllers/edit-post-controller.js" />"></script>
        <script type="text/javascript" src="<c:url value="/static/js/widgets/user-name-input-widget.js" />"></script>
        <script type="text/javascript" src="<c:url value="/static/js/widgets/password-input-widget.js" />"></script>
        
        <script type="text/javascript">        
        	angular.module("app").constant("apiServiceRoot", "<c:url value="/api/BlogService/" />");
        </script>
	</head>
	<body ng-controller="ChromeController">
		<div class="container">
			<div class="row">
				<div class="page-header">
					<h1>Single Page Blogger ({{getSessionToken()}})</h1>
				</div>
			</div>		
			<ng-view></ng-view>
		</div>
	</body>
</html>