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
    
	$scope.$watch('uip_region', function(){
		if($scope.gridInit && !$scope['gridRegion']) {
			$scope.uip_center = config.uip_center;
			
	        $timeout(function() {
	        	$scope.queryCenterCode = $stateParams.id;
	        }, 500);
			
		    var params = {uip_center_code: $stateParams.id};
		    $scope.gridInit(RegionService, columnDefs);
		    $scope.getDatas(params, function (data){
	    		$scope.alert(data.uip_regions);
			});
		}
	}, true);

    $scope.searchData = function () {
    	$scope.alerts = [];
    	$scope.retrieveData({uip_center_code: $scope.queryCenterCode, code: $scope.queryCode}, function (data){
    		$scope.alert(data.uip_regions);
		});
    };	
	
    $scope.goCenterData = function () {
        $state.go('default.centers');
    }
    
    $scope.getRegions = function (code) {
	    var params = {uip_center_code: code, code: $scope.queryCode};
    	$scope.getDatas(params, function (data){
    		$scope.alert(data.uip_regions);
		});
    }
    
  });
