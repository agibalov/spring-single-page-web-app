angular.module("app").controller("SignUpController", function($scope, api) {
	$scope.userName = "";
	$scope.password = "";
	
	$scope.signUpClicked = function() {
		console.log("Sing Up clicked");
		console.log("userName: " + $scope.userName);
		console.log("password: " + $scope.password);
		
		api.createUser(
				$scope.userName, 
				$scope.password, 
				function(result) {
					console.log("Service says:");
					console.log(result);
					
					$scope.userNameErrors = undefined;
					$scope.passwordErrors = undefined;
					
					if(
							result.blogServiceErrorCode !== null && 
							result.blogServiceErrorCode === "ValidationError") {
						
						if(result.fieldErrors.userName !== undefined) {
							$scope.userNameErrors = result.fieldErrors.userName;
						}					
						
						if(result.fieldErrors.password !== undefined) {
							$scope.passwordErrors = result.fieldErrors.password;
						}
					}
				});
	};
});