angular.module("app").controller("GetPostController", function($scope, $routeParams, api) {
	$scope.postId = $routeParams.postId;
	$scope.text = "";
});