angular.module("app", [], function($routeProvider) {	
	$routeProvider
		.when("/", {
			templateUrl: "/static/templates/controllers/index.html",
			controller: "IndexController"
		})
		.when("/createPost", {
			templateUrl: "/static/templates/controllers/create-post.html",
			controller: "CreatePostController"
		})
		.when("/getPost/:postId", {
			templateUrl: "/static/templates/controllers/get-post.html",
			controller: "GetPostController"
		})
		.when("/editPost/:postId", {
			templateUrl: "/static/templates/controllers/edit-post.html",
			controller: "EditPostController"
		})
		.otherwise({
			redirectTo: "/"
		});
});