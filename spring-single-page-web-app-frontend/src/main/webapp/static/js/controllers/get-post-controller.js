angular.module("app").controller("GetPostController", function($scope, $routeParams, api) {
	$scope.PostId = $routeParams.postId;
	$scope.Text = "";
	
	api.getPost($scope.PostId, function(result) {
		if(result.ok !== true) {
			console.log(result);
			throw new "something terrible happened";
		}
		
		$scope.Text = result.payload.Text;
	});
});