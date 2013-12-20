'use strict';

app.controller('CentersCtrl', function ($scope, $location, $stateParams, $timeout, $state, config, CenterService, MessageCtrl) {
	$scope.$location = $location;

    $scope.cellValue;
    var checkboxCellTemplate='<div class="ngSelectionCell"><input tabindex="-1" class="ngSelectionCheckbox" type="checkbox" ng-checked="row.selected" /></div>';
    var cellEditableTemplate = "<input style=\"width: 90%\" step=\"any\" type=\"string\" ng-class=\"'colt' + col.index\" ng-input=\"COL_FIELD\" ng-blur=\"updateEntity(col, row, cellValue)\" ng-model='cellValue'/>";
    var columnDefs = [
                 {field:'CHK', displayName:'chk', width: 50 , 
                     cellTemplate:checkboxCellTemplate,
                     sortable:false, pinned:false, enableCellEdit: false },
                 {field:'status', displayName:'CRUD', width: 50 , sortable:false, pinned:false, enableCellEdit: false },
                 {field:'link', displayName:'link', width: 80, cellTemplate: '<div><button ng-click="goTo(\'regions\', row)">to region</button></div>'},
                 {field:'id', displayName:'id', enableCellEdit: false},
                 // {field:'code', displayName:'code', editableCellTemplate: cellEditableTemplate},
                 {field:'code', displayName:'code', editableCellTemplate: cellEditableTemplate},
                 {field:'name', displayName:'name', editableCellTemplate: cellEditableTemplate},
                 {field:'chief', displayName:'chief', editableCellTemplate: cellEditableTemplate},
                 {field:'address', displayName:'address', editableCellTemplate: cellEditableTemplate},
                 {field:'phone', displayName:'phone', editableCellTemplate: cellEditableTemplate}
                 ];
    
	$scope.$watch('uip_center', function(){
		if($scope.gridInit && !$scope['gridCenter']) {
		    $scope.gridInit(CenterService, columnDefs);
		    $scope.getDatas();
		}
	}, true);
	
    $scope.goHomeData = function () {
    	document.location = '/';
	}
    
  });
