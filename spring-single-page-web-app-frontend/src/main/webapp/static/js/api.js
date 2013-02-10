angular.module("app").factory("api", function($http, apiServiceRoot) {
	return {
		createUser: function(userName, password, onSuccess) {
			$http
			.post(
				apiServiceRoot + "createUser", 
				null, 
				{ 
					params: {
						userName: userName, 
						password: password
					} 
				})
			.success(function(result) {
				onSuccess(result);
			});
		},
		
		authenticate: function(userName, password, onSuccess) {
			$http
			.post(
				apiServiceRoot + "authenticate", 
				null, 
				{ 
					params: {
						userName: userName, 
						password: password
					} 
				})
			.success(function(result) {
				onSuccess(result);
			});
		},
		
		createPost: function(sessionToken, text, onSuccess) {
			$http
			.post(
				apiServiceRoot + "createPost",
				null,
				{
					params: {
						sessionToken: sessionToken,
						text: text
					}
				})
			.success(function(result) {
				onSuccess(result);
			});
		},
		
		getPost: function(sessionToken, postId, onSuccess) {
			$http
			.get(
				apiServiceRoot + "getPost",
				{
					params: {
						sessionToken: sessionToken,
						postId: postId
					}
				})
			.success(function(result) {
				onSuccess(result);
			});
		},
		
		updatePost: function(sessionToken, postId, text, onSuccess) {
			$http
			.post(
				apiServiceRoot + "updatePost",
				{
					params: {
						sessionToken: sessionToken,
						postId: postId,
						text: text
					}
				})
			.success(function(result) {
				onSuccess(result);
			});
		},
		
		deletePost: function(sessionToken, postId) {
			$http
			.post(
				apiServiceRoot + "deletePost",
				{
					params: {
						sessionToken: sessionToken,
						postId: postId
					}
				})
			.success(function(result) {
				onSuccess(result);
			});
		}
	};	
});