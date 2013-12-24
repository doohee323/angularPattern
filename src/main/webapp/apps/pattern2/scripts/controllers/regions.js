'use strict';

app.controller('RegionsCtrl', function ($scope, $location, $stateParams, $timeout, $state, config, RegionService, MessageCtrl) {
	$scope.$location = $location;

    $scope.cellValue;
    var checkboxCellTemplate='<div class="ngSelectionCell"><input tabindex="-1" class="ngSelectionCheckbox" type="checkbox" ng-checked="row.selected" /></div>';
    var cellEditableTemplate = "<input style=\"width: 90%\" step=\"any\" type=\"string\" ng-class=\"'colt' + col.index\" ng-input=\"COL_FIELD\" ng-blur=\"updateEntity(col, row, cellValue)\" ng-model='cellValue'/>";
    var columnDefs = [
                 {field:'CHK', displayName:'chk', width: 50 , 
		            cellTemplate:checkboxCellTemplate,
		            sortable:false, pinned:false, enableCellEdit: false },
		        {field:'status', displayName:'CRUD', width: 50 , sortable:false, pinned:false, enableCellEdit: false },
		        {field:'uip_center_id', displayName:'uip_center_id', enableCellEdit: false},
		        {field:'id', displayName:'id', enableCellEdit: false},
		        {field:'code', displayName:'code', editableCellTemplate: cellEditableTemplate},
		        {field:'region_code', displayName:'region_code', editableCellTemplate: cellEditableTemplate},
		        {field:'name', displayName:'name', editableCellTemplate: cellEditableTemplate},
		        {field:'chief', displayName:'chief', editableCellTemplate: cellEditableTemplate},
		        {field:'address', displayName:'address', editableCellTemplate: cellEditableTemplate}
                 ];

    MessageCtrl.init($scope);
    
	$scope.$watch('uip_regions', function(){
		if($scope.gridInit && !$scope['gridRegion']) {
			$scope.uip_centers = config.uip_centers;
			
	        $timeout(function() {
	        	$scope.queryCenterCode = $stateParams.code;
	        }, 500);
			
		    var params = {uip_center_code: $stateParams.code};
		    $scope.gridInit(RegionService, columnDefs);
		    $scope.getDatas(params, function (data){
		    	$scope.alert.retrieve(data.uip_regions);
		    	$scope.setInput({'uip_center_id': $stateParams.id});
			});
		}
	}, true);

    $scope.searchData = function () {
    	$scope.alerts = [];
    	$scope.retrieveData({uip_center_code: $scope.queryCenterCode, code: $scope.queryCode}, function (data){
	    	$scope.alert.retrieve(data.uip_regions);
		});
    };	
	
    $scope.goCenterData = function () {
        $state.go('default.centers');
    }
    
    $scope.getRegions = function (code) {
	    var params = {uip_center_code: code, code: $scope.queryCode};
    	$scope.getDatas(params, function (data){
	    	$scope.alert.retrieve(data.uip_regions);
		});
    }
    
    $scope.postInsert = function (scope) {
        scope.uip_regions[0].uip_center_id = $scope.queryCenterCode
    }    
    $scope.deleteInsert = function (scope) {
    }    
    $scope.saveInsert = function (scope) {
    }    
    $scope.initInsert = function (scope) {
    }     
    
  });
