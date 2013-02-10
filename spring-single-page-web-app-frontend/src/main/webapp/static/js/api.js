angular.module("app").factory("api", function($http) {
	
	var apiServiceRoot = "/api/BlogService/";
	
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
		}		
	};
	
});