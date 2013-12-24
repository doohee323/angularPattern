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
    	CenterService.R.get({queryCode : $scope.queryCode}, function(data) {
    	 	$scope.uip_center = data.uip_centers;
    	 	$scope.alert.retrieve(data.uip_centers);
    	});
    };
    $scope.saveData = function () {
    	$scope.alerts = [];
    	if(!$scope.uip_center.id) {
        	var params = $scope.uip_center;
        	CenterService.CUD.save(params, function (data) {
        		$scope.uip_center = data.uip_centers;
        		$scope.alert.save(data.uip_centers.id);
        	})
    	} else {
        	var params = $scope.uip_center;
		   	CenterService.CUD.update(params, function (data) {
		   		$scope.uip_center = data.uip_centers;
        		$scope.alert.save(data.uip_centers.id);
		   	})
    	}
    };
    $scope.deleteData = function () {
    	$scope.alerts = [];
    	var params = $scope.uip_center;
    	CenterService.CUD.delete(params, function (data) {
    		$scope.alert.delete(data.id);
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
