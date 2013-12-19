'use strict';

app.controller('CentersCtrl', function ($scope, $location, $stateParams, $timeout, $state, config, CenterService) {
	$scope.$location = $location;

    $scope.cellValue;
    var checkboxCellTemplate='<div class="ngSelectionCell"><input tabindex="-1" class="ngSelectionCheckbox" type="checkbox" ng-checked="row.selected" /></div>';
    var cellEditableTemplate = "<input style=\"width: 90%\" step=\"any\" type=\"string\" ng-class=\"'colt' + col.index\" ng-input=\"COL_FIELD\" ng-blur=\"updateEntity(col, row, cellValue)\" ng-model='cellValue'/>";
    var columnDefs = [
                 {field:'CHK', displayName:'chk', width: 50 , 
                     cellTemplate:checkboxCellTemplate,
                     sortable:false, pinned:false, enableCellEdit: false },
                 {field:'status', displayName:'CRUD', width: 50 , sortable:false, pinned:false, enableCellEdit: false },
                 {field:'link', displayName:'link', width: 80, cellTemplate: '<div><button ng-click="goTo(\'/regions/\', row)">to region</button></div>'},
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
						
	$scope.$on('$stateChangeStart', function(event, toState,
			toParams, fromState, fromParams) {
		//debugger;
		// event.preventDefault();
		// transitionTo() promise will be rejected with
		// a 'transition prevented' error
	});

	$scope.$on('$viewContentLoading', function(event,
			viewConfig) {
		//debugger;
		// Access to all the view config properties.
		// and one special property 'targetView'
		// viewConfig.targetView
	});

	$scope.$on('$viewContentLoaded', function(event) {
		//debugger;
	});

	// somewhere else
	$scope.$on('$stateNotFound', function(event, unfoundState,
			fromState, fromParams) {
		//debugger;
		console.log(unfoundState.to); // "lazy.state"
		console.log(unfoundState.toParams); // {a:1, b:2}
		console.log(unfoundState.options); // {inherit:false} +
											// default options
	});
	
    $scope.goHomeData = function () {
    	document.location = '/';
	}
    
  });
