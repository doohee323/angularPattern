'use strict';

app.directive('ngExcel', function($compile, $timeout, config){
	
	var _service;
	var _grid;
	var _dataset;	// ex) uip_center
	var _input;		// params
	
	var getTemplate = function(grid) {
		return "<div ng-grid=\"" + grid + "\" ng-style=\"myprop()\"></div>";
	};
	
	var linker = function(scope, element, attr) {
		_grid = attr['id'];
		_dataset = attr['data'];

		scope.gridInit = function(service, columnDefs, input) {
			_service = service;
			_input = input;
			
			scope[_grid] = {
			        data: _dataset,
			        multiSelect: false,  
			        enableCellSelection: true,
			        enableCellEditOnFocus: true,
			        enableRowSelection: true,
			        enablePinning: true,
			        enableSorting: true,
			        columnDefs: columnDefs,
			        selectedItems: []
			    };
			
			element.html(getTemplate(_grid)).show();
//			element.bind('click', function() {
//				eval('scope.' + attr["type"] + attr["data"] + '()');
//				return;
//			});
			$compile(element.contents())(scope);
			
			scope[_grid].attr = attr;
		};		
		
		scope.updateEntity = function(column, row, cellValue) {
	        var data = scope[_dataset][row.rowIndex];
	        var status = scope[_dataset][row.rowIndex].status;
	        if(status && status == 'I') {
	        } else {
	            if(data[column.colDef.field] != cellValue) {
	                scope[_dataset][row.rowIndex].status = 'U';
	            }
	        }
	        row.entity[column.field] = cellValue;
	    };
		
		scope.$watch(_dataset, function(newData){
			scope.myprop = function() {
				var attr = scope[_grid].attr;
				if(attr) {
		            return {
		                width: attr["width"] + 'px',
		                height: attr["height"] + 'px'
		            };
				} else {
					return {};
				}
	        };
		}, true);

		scope.getDatas = function(input, callback) {
			scope[_dataset] = [];
			scope.callback = callback;
	    	var params = {};
	    	if(input) params = input;
	    	if(_input) params = mergeData(_input, params);
	    	_service.R.get(params, function(data) {
	    		if(data[_dataset]) {
	    			if(data[_dataset].length) {
			            for (var i = 0; i < data[_dataset].length; i++) {
			                data[_dataset][i].status = 'R';
			            };
			            scope[_dataset] = data[_dataset];
	    			} else if(data[_dataset].id){
			            data[_dataset].status = 'R';
			            var list = [];
			            list[0] = data[_dataset];
			            scope[_dataset] = list;
	    			}
	    		}
	    		if(scope.callback) {
	    			scope.callback(data);
	    		}
	        });
	    };

	    scope.setInput = function (input) {
	    	_input = input;
	    };

	    scope.retrieveData = function (input, callback) {
	    	scope.getDatas(input, callback);
	    };

	    scope.insertData = function (callback) {
	        var data = scope[_grid].columnDefs;
	        var newData = getAddRow(data);
	        if(_input) newData = mergeData(_input, newData);
	        newData.status = 'I';
	        scope[_dataset].unshift(newData);
	        
	        if(callback) {
		        eval('scope.' + callback + '(this)');
	        }

	        var selectRow = function() {
	            scope[_grid].selectRow(0, true);
	            //$($($(".ngCellText.col3.colt1")[1]).parent()).parent().focus();
	        }
	        $timeout(selectRow, 500);
	    };

	    scope.deleteData = function (callback) {
	        var id = scope[_grid].selectedItems[0].id;
	        for (var i = 0; i < scope[_dataset].length; i++) {
	            if(scope[_dataset][i].id == id) {
	                if(scope[_dataset][i].status == 'I') {
	                    scope[_dataset].splice(i, 1);
	                } else {
	                    scope[_dataset][i].status = 'D';
	                }
	            }
	        };
	        if(callback) {
		        eval('scope.' + callback + '(this)');
	        }
	    };

	    scope.initData = function (callback) {
	    	if(!scope[_grid].selectedItems[0]) return;
	        var id = scope[_grid].selectedItems[0].id;
	        for (var i = 0; i < scope[_dataset].length; i++) {
	            if(scope[_dataset][i].id == id) {
	                for (var j = 0; j < Object.keys(scope[_dataset][i]).length; j++) {
	                    scope[_dataset][i][Object.keys(scope[_dataset][i])[j]] = null;
	                };
	                break;
	            }
	        };
	        if(callback) {
		        eval('scope.' + callback + '(this)');
	        }
	    };

	    scope.saveData = function (callback) {
	    	scope.alerts = [];
	        var dataset = angular.copy(scope[_dataset]);
	        for (var i = 0; i < dataset.length; i++) {
	            var status = dataset[i].status;
	            var currow = i;
	            delete dataset[i].status;
	            var params = dataset[i];
	            if(status == 'I') {
	            	params = mergeData(_input, params);
	                _service.CUD.save(params, function (data) {
	                    scope[_dataset][0].id = data[_dataset].id;
	                    scope.alert.save(data[_dataset]);
	                })
	            } else if(status == 'U') {
	                _service.CUD.update(params, function (data) {
	                    scope[_dataset][currow] = data[_dataset].id;
	                    scope.alert.save(data[_dataset]);
	                })
	            } else if(status == 'D') {
            		_service.CUD.delete(params, function (data) {
            			scope[_dataset].splice(i-1, 1);
            			scope.alert.delete(data);
                	})
	            }
	            scope[_dataset][i].status = 'R';
	        };
	        if(callback) {
		        eval('scope.' + callback + '(this)');
	        }
	    };

		var lookupDs = function ( id, callback ) {
	    	for (var i = 0; i < scope[_dataset].length; i++) {
	    		if (scope[_dataset][i].id == (id + '')) {
					callback(i);
					break;
				}
			}
		}

		var getAddRow = function(source) {
	        var data = angular.copy(source);
	        var target = {};
	        for (var i = 0; i < data.length; i++) {
	            if(data[i].field 
	            	&& data[i].field != 'link'
	            	&& data[i].field != 'CHK') {
	                target[data[i].field] = null;
	            }
	        };
	        return target;
	    };		

	    var mergeData = function(source, target) {
	        for (var j = 0; j < Object.keys(source).length; j++) {
                target[Object.keys(source)[j]] = source[Object.keys(source)[j]];
            };
	        return target;
	    };
	};
	
	return {
		restrict : 'EA',
		link : linker,
		replace : true
	};
});