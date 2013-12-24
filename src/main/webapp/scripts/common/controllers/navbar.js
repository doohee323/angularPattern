'use strict';

app.controller('NavbarCtrl', function ($scope, $location, $state) {
    $scope.$state = $state;
    $scope.$location = $location;
    
    $state.go('default.centers');
	
    $scope.goTo = function ( path, row ) {
    	if(path == '/') {
    	} else if(row) {
    		if($scope.$$childHead.$$childHead.$$childHead.uip_centers) {
        		config.uip_centers = $scope.$$childHead.$$childHead.$$childHead.uip_centers;
    		}
    		$state.go('default.' + path, {code: row.entity.code, id: row.entity.id});
    	} else if($scope.uip_center[0]) { // go to centers
    		//path += $scope.uip_center[0].id;
    		$state.go('default.centers');
    	}
	}	

    $scope.goLocation = function ( path ) {
	  	document.location = path;
	}	
});

