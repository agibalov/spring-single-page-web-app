angular.module("app").controller("EditPostController", function($scope, $routeParams, api) {
	$scope.postId = $routeParams.postId;
	$scope.text = "";
});