angular.module("app").directive("userNameInputWidget", function() {
	return {
		restrict: "E",
		scope: {
			"value": "=",
			"errors": "="
		},
		templateUrl: "/static/templates/widgets/user-name-input-widget.html",
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