'use strict';

app.controller('NavbarCtrl', function ($scope, $location, $state) {
    $scope.$state = $state;
    $scope.$location = $location;
    
    $state.go('default.centers');
	
    $scope.goTo = function ( baseUrl, row ) {
    	var path = baseUrl;
    	if(baseUrl == '/') {
    	} else if(row) { // go to regions
    		//path += row.entity.id;
    		$state.go('default.regions', {id: row.entity.id});
    	} else if($scope.uip_center[0]) { // go to centers
    		//path += $scope.uip_center[0].id;
    		$state.go('default.centers');
    	}
        // if(uip_center) config.centers = uip_center;
	}	

    $scope.goLocation = function ( path ) {
	  	document.location = path;
	}	
});

