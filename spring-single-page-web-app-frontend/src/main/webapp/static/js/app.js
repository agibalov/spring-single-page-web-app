angular.module("app", [], function($routeProvider) {
	$routeProvider
		.when("/", {
			templateUrl: "/static/templates/controllers/index.html",
			controller: "IndexController"
		})
		.otherwise({
			redirectTo: "/"
		});
});