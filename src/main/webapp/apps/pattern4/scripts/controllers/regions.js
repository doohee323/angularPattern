'use strict';

app.controller('RegionsCtrl', function ($scope, $location, $stateParams, $state, RegionService, RegionServiceGet, MessageCtrl) {
	$scope.$location = $location;
    $scope.newRegion = {};

    MessageCtrl.init($scope);
    
    var center_id = $stateParams.id;
    
    $scope.retrieveData = function () {
    	$scope.alerts = [];
    	RegionServiceGet.get({uip_center_code: $scope.queryCenterCode, code : $scope.queryCode}, function(data) {
    	 	$scope.uip_region = data.uip_regions;
    	 	$scope.alert(data.uip_regions);
    	});
    };

    $scope.saveData = function () {
    	$scope.alerts = [];
    	if(!$scope.queryCenterCode) {
    		$scope.alert(null, 'required', '[Center Code]');
    		return;
    	}
    	$scope.uip_region.uip_center_id = $scope.queryCenterCode;
    	if(!$scope.uip_region.id) {
        	var params = {uip_region : $scope.uip_region};
        	params = $scope.uip_region; // java
        	RegionService.save(params, function (data) {
        		$scope.uip_region = data.uip_regions;
        	 	$scope.alert(data.uip_regions.id);
        	})
    	} else {
        	var params = {uip_region : $scope.uip_region,
   				 id : $scope.uip_region.id};
		   	params = params.uip_region; // java
		   	RegionService.update(params, function (data) {
		   		$scope.uip_region = data.uip_regions;
	    	 	$scope.alert(data.uip_regions.id);
		   	})
    	}
    };

    $scope.deleteData = function () {
    	$scope.alerts = [];
    	RegionService.delete({"id" : $scope.uip_region.id}, function (data) {
    	 	$scope.alert(data.uip_regions);
    		$scope.uip_region = {};
    	})
    };

    $scope.initData = function () {
    	$scope.alerts = [];
    	$scope.uip_region = {};
    }

    $scope.goHomeData = function () {
        $state.go('default.centers');
	}
});
