angular.module("app").controller("CreatePostController", function($scope, api, $location, $route) {	
	$scope.Text = "";
	
	$scope.createPostClicked = function() {
		console.log("create post");
		console.log($scope.Text);
		
		api.createPost($scope.Text, function(result) {
			console.log(result);
			
			if(result.ok !== true) {				
				throw new "something terrible happened";
			}
			
			var postId = result.payload.PostId;
			$location.path("/getPost/" + postId);
			$route.reload();
		});
	};
});