angular.module("app").controller("ChromeController", function($scope, api) {
	$scope.getSessionToken = function() {
		return api.getSessionToken();
	};
});