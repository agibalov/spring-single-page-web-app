angular.module("app").directive("passwordInputWidget", function() {
	return {
		restrict: "E",
		scope: {
			"value": "=",
			"errors": "="
		},
		templateUrl: "/static/templates/widgets/password-input-widget.html",
		controller: function($scope) {
			$scope.getClass = function() {
				if($scope.errors !== undefined) {
					return "error";
				}
				
				return "";
			};
		}
	};
});