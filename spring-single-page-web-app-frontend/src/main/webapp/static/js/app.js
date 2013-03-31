angular.module("app", ["ngCookies"], function($routeProvider) {	
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
		.when("/getPosts/", {
			templateUrl: "/static/templates/controllers/get-posts.html",
			controller: "GetPostsController"
		})
		.when("/editPost/:postId", {
			templateUrl: "/static/templates/controllers/edit-post.html",
			controller: "EditPostController"
		})
		.otherwise({
			redirectTo: "/"
		});
});