'use strict';

app.controller('NavbarCtrl', function ($scope, $location, $state) {
    $scope.$state = $state;
    $scope.$location = $location;
    
    $state.go('default.centers');
	
    $scope.goTo = function ( path, row ) {
    	if(path == '/') {
    	} else if(row) {
    		if($scope.$$childHead.$$childHead.$$childHead.uip_center) {
        		config.uip_center = $scope.$$childHead.$$childHead.$$childHead.uip_center;
    		}
    		$state.go('default.' + path, {id: row.entity.code});
    	} else if($scope.uip_center[0]) { // go to centers
    		//path += $scope.uip_center[0].id;
    		$state.go('default.centers');
    	}
	}	

    $scope.goLocation = function ( path ) {
	  	document.location = path;
	}	
});

