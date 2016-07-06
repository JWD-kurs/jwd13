var wafepaApp = angular.module('wafepaApp', ['ngRoute']);

wafepaApp.controller('MyController', function($scope) {
	
	$scope.tekst = 'Neki tekst';
	
	$scope.broj = 5;
	
	$scope.objekat = { polje: 'vrednost' };
	
	$scope.lista = [23, 51, 33, 'qwe', {}, []];
	
	$scope.funkcija = function(p) {
		$scope.objekat.polje = 'neka nova vrednost';
		alert('Hello: ' + p);
	};
});

wafepaApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : '/static/app/html/partial/home.html'
        })
        .when('/activities', {
            templateUrl : '/static/app/html/partial/activities.html'
        })
        .otherwise({
            redirectTo: '/'
        });
}]);