'use strict';

app.controller('CentersCtrl', function ($scope, $location, $stateParams, $timeout, $state, config, CenterService, MessageCtrl) {
	$scope.$location = $location;

	var code = $stateParams.id;
	if(code) {
		$scope.queryCode = code;
		if($scope.retrieveData) {
			$scope.retrieveData();
		}
	}
	
    $scope.cellValue;
    var columnDefs = [
                 {field:'link', displayName:'link', width: 80, cellTemplate: '<div><button ng-click="goTo(\'detail\', row)">to detail</button></div>'},
                 {field:'id', displayName:'id', enableCellEdit: false},
                 // {field:'code', displayName:'code', editableCellTemplate: cellEditableTemplate},
                 {field:'code', displayName:'code', enableCellEdit: false},
                 {field:'name', displayName:'name', enableCellEdit: false},
                 {field:'chief', displayName:'chief', enableCellEdit: false},
                 {field:'address', displayName:'address', enableCellEdit: false},
                 {field:'phone', displayName:'phone', enableCellEdit: false}
                 ];
    
	MessageCtrl.init($scope);
	
	$scope.$watch('uip_center', function(){
		if($scope.gridInit && !$scope['gridCenter']) {
		    $scope.gridInit(CenterService, columnDefs);
		    $scope.getDatas(null, function (data){
	    		$scope.alert(data.uip_centers);
			});
		}
	}, true);
	
    $scope.searchData = function () {
    	$scope.alerts = [];
    	$scope.retrieveData({code : $scope.queryCode}, function (data){
    		$scope.alert(data.uip_centers);
		});
    };
	
    $scope.retrieveData = function () {
    	$scope.alerts = [];
    	CenterService.get({code : $scope.queryCode}, function(data) {
    	 	$scope.uip_center = data.uip_centers;
    	 	$scope.alert(data.uip_centers);
    	});
    };
    $scope.saveData = function () {
    	$scope.alerts = [];
    	if(!$scope.uip_center.id) {
        	var params = {uip_center : $scope.uip_center};
        	params = $scope.uip_center; // java
        	CenterService.save(params, function (data) {
        		$scope.uip_center = data.uip_centers;
        		$scope.alert(data.uip_centers.id);
        	})
    	} else {
        	var params = {uip_center : $scope.uip_center,
   				 id : $scope.uip_center.id};
		   	params = params.uip_center; // java
		   	CenterService.update(params, function (data) {
		   		$scope.uip_center = data.uip_centers;
		   		$scope.alert(data.uip_centers.id);
		   	})
    	}
    };
    $scope.deleteData = function () {
    	$scope.alerts = [];
    	CenterService.delete({"id" : $scope.uip_center.id}, function (data) {
    		$scope.alert(data.uip_centers);
    		$scope.uip_center = {};
    	})
    };

    $scope.initData = function () {
    	$scope.alerts = [];
    	$scope.uip_center = {};
    }
    
    $scope.goHomeData = function () {
    	document.location = '/angularPattern';
	}
    
    $scope.listData = function () {
    	$state.go('default.centers');
	}
    
  });
