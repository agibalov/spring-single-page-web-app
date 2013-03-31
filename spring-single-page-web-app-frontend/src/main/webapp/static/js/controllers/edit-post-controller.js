angular.module("app").controller("EditPostController", function($scope, $routeParams, api) {
	$scope.PostId = $routeParams.postId;
	$scope.Text = "";
	
	api.getPost($scope.PostId, function(result) {
		if(result.ok !== true) {
			console.log(result);
			throw new "something terrible happened";
		}
		
		$scope.Text = result.payload.Text;
	});
	
	$scope.updatePostClicked = function() {
		console.log("update post");
		console.log($scope.Text);
		
		api.updatePost($scope.PostId, $scope.Text, function(result) {
			console.log(result);
		});
	};
});