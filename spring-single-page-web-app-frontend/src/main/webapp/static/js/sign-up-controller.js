angular.module("app").controller("SignUpController", function($scope, api) {
	$scope.userName = "";
	$scope.password = "";
		
	$scope.getUserNameClass = function() {
		if($scope.userNameErrors !== undefined) {
			return "error";
		}
		
		return "";
	};
	
	$scope.getPasswordClass = function() {
		if($scope.passwordErrors !== undefined) {
			return "error";
		}
		
		return "";
	}
	
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
					
					$scope.userNameErrors = result.fieldErrors.userName;
					$scope.passwordErrors = result.fieldErrors.password;
				});
	};
});