angular.module("app").controller("GetPostController", function($scope, $routeParams, api) {
	$scope.postId = $routeParams.postId;
	$scope.text = "";
	
	api.getPost($scope.postId, function(result) {
		if(result.ok !== true) {
			console.log(result);
			throw new "something terrible happened";
		}
		
		$scope.text = result.payload.Text;
	});
});