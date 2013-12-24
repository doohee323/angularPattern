'use strict';

app.controller('RegionsCtrl', function ($scope, $location, $stateParams, $state, RegionService, MessageCtrl) {
	$scope.$location = $location;
    $scope.newRegion = {};

    MessageCtrl.init($scope);
    
    var center_id = $stateParams.id;
    
    $scope.retrieveData = function () {
    	$scope.alerts = [];
    	RegionService.R.get({uip_center_code: $scope.queryCenterCode, code : $scope.queryCode}, function(data) {
    	 	$scope.uip_region = data.uip_regions;
    	 	$scope.alert.retrieve(data.uip_regions);
    	});
    };

    $scope.saveData = function () {
    	$scope.alerts = [];
    	if(!$scope.queryCenterCode) {
    		$scope.alert('required', 'pbf.required', '[Center Code]');
    		return;
    	}
    	$scope.uip_region.uip_center_id = $scope.queryCenterCode;
    	if(!$scope.uip_region.id) {
        	var params = $scope.uip_region;
        	RegionService.CUD.save(params, function (data) {
        		$scope.uip_region = data.uip_regions;
        		$scope.alert.save(data.uip_regions.id);
        	})
    	} else {
        	var params = $scope.uip_region;
		   	RegionService.CUD.update(params, function (data) {
		   		$scope.uip_region = data.uip_regions;
        		$scope.alert.save(data.uip_regions.id);
		   	})
    	}
    };

    $scope.deleteData = function () {
    	$scope.alerts = [];
    	var params = $scope.uip_region;
    	RegionService.CUD.delete(params, function (data) {
    		$scope.alert.delete(data.id);
    		$scope.uip_region = {};
    	})
    };

    $scope.initData = function () {
    	$scope.alerts = [];
    	$scope.uip_region = {};
    }

    $scope.goCenterData = function () {
        $state.go('default.centers');
	}
});
