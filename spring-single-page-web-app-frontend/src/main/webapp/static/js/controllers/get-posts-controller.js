angular.module("app").controller("GetPostsController", function($scope, api) {
	api.getPosts(function(result) {
		if(result.ok !== true) {
			console.log(result);
			throw new "something terrible happened";
		}
		
		$scope.Posts = result.payload;
	});
	
	$scope.CreateDummyPost = function() {
		api.createPost("text for dummy post goes here", function(result) {
			console.log(result);
		}); 
	};
});