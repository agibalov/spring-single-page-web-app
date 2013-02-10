angular.module("app", [], function($routeProvider) {
	$routeProvider
		.when("/", {
			templateUrl: "/static/templates/index.html",
			controller: "IndexController"
		})
		.otherwise({
			redirectTo: "/"
		});
});