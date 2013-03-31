angular.module("app").factory("api", function($http, $cookieStore, apiServiceRoot) {		
	return {
		isAuthenticated: function() {
			return this.getSessionToken() !== null;
		},
		
		getSessionToken: function() {
			return $cookieStore.get("SessionToken");
		},
		
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
				if(result.ok === true) {
					$cookieStore.put("SessionToken", result.payload.SessionToken);
				}
				
				onSuccess(result);
			});
		},
		
		createPost: function(text, onSuccess) {
			if(!this.isAuthenticated()) {
				throw "not authenticated";
			}
			
			$http
			.post(
				apiServiceRoot + "createPost",
				null,
				{
					params: {
						sessionToken: this.getSessionToken(),
						text: text
					}
				})
			.success(function(result) {
				onSuccess(result);
			});
		},
		
		getPost: function(postId, onSuccess) {
			if(!this.isAuthenticated()) {
				throw "not authenticated";
			}
			
			$http.get(
				apiServiceRoot + "getPost",
				{
					params: {
						sessionToken: this.getSessionToken(),
						postId: postId
					}
				})
			.success(function(result) {
				onSuccess(result);
			});
		},
		
		getPosts: function(onSuccess) {
			if(!this.isAuthenticated()) {
				throw "not authenticated";
			}
			
			$http.get(
				apiServiceRoot + "getPosts",
				{
					params: {
						sessionToken: this.getSessionToken(),
					}
				})
			.success(function(result) {
				onSuccess(result);
			});
		},
		
		updatePost: function(postId, text, onSuccess) {
			if(!this.isAuthenticated()) {
				throw "not authenticated";
			}
			
			$http
			.post(
				apiServiceRoot + "updatePost",
				{
					params: {
						sessionToken: this.getSessionToken(),
						postId: postId,
						text: text
					}
				})
			.success(function(result) {
				onSuccess(result);
			});
		},
		
		deletePost: function(postId) {
			if(!this.isAuthenticated()) {
				throw "not authenticated";
			}
			
			$http
			.post(
				apiServiceRoot + "deletePost",
				{
					params: {
						sessionToken: this.getSessionToken(),
						postId: postId
					}
				})
			.success(function(result) {
				onSuccess(result);
			});
		}
	};	
});