'use strict';

app.controller('CentersCtrl', function ($scope, $location, $stateParams, $state, CenterService, MessageCtrl) {
	$scope.$location = $location;
    $scope.uip_center = {};
    
    if($scope.queryCode) {
    	retrieveCenter();
    };

    MessageCtrl.init($scope);
    
    $scope.retrieveData = function () {
    	$scope.alerts = [];
    	CenterService.get({code : $scope.queryCode}, function(data) {
    	 	$scope.uip_center = data.uip_center;
    	 	$scope.alert(data.uip_center);
    	});
    };
    $scope.saveData = function () {
    	$scope.alerts = [];
    	if(!$scope.uip_center.id) {
        	var params = {uip_center : $scope.uip_center};
        	params = $scope.uip_center; // java
        	CenterService.save(params, function (data) {
        		$scope.uip_center = data.uip_center;
        		$scope.alert(data.uip_center.id);
        	})
    	} else {
        	var params = {uip_center : $scope.uip_center,
   				 id : $scope.uip_center.id};
		   	params = params.uip_center; // java
		   	CenterService.update(params, function (data) {
		   		$scope.uip_center = data.uip_center;
		   		$scope.alert(data.uip_center.id);
		   	})
    	}
    };
    $scope.deleteData = function () {
    	$scope.alerts = [];
    	CenterService.delete({"id" : $scope.uip_center.id}, function (data) {
    		$scope.alert(data.uip_center);
    		$scope.uip_center = {};
    	})
    };

    $scope.initData = function () {
    	$scope.alerts = [];
    	$scope.uip_center = {};
    }
    
    $scope.goHomeData = function () {
    	document.location = '/';
	}
    
    $scope.goRegionData = function () {
    	$state.go('default.regions');
	}
    
  });
