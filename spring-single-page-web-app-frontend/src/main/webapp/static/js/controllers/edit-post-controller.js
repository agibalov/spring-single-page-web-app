angular.module("app").controller("EditPostController", function($scope, $routeParams, api) {
	$scope.postId = $routeParams.postId;
	$scope.text = "";
	
	api.getPost($scope.postId, function(result) {
		if(result.ok !== true) {
			console.log(result);
			throw new "something terrible happened";
		}
		
		$scope.text = result.payload.Text;
	});
	
	$scope.updatePostClicked = function() {
		console.log("update post");
		console.log($scope.text);
		
		api.updatePost($scope.postId, $scope.text, function(result) {
			console.log(result);
		});
	};
});