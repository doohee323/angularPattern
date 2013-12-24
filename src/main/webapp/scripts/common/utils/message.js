'use strict';

app.factory('MessageCtrl', function () {
	var $scope;
	function init(scope) {
		$scope = scope;
	    $scope.alerts = [];
		
		$scope.alert = function (type, msgId, param) {
	        var alerts = [{ type: 'success', msgId: 'pbf.success', msg: '@ sucess!' },
	                      { type: 'info', msgId: 'pbf.nodata', msg: 'No data!' },
	                      { type: 'required', msgId: 'pbf.required', msg: '@ required!' },
	                      { type: 'error', msgId: 'pbf.error', msg: '@ error!' }
	                      ];
	        
        	for(var i=0;i<alerts.length;i++) {
        		if(alerts[i].msgId == msgId) {
    				var msg = alerts[i].msg;
        			if(param) {
        				msg = msg.replace('@', param);
        				alerts[i].msg = msg;
	        			$scope.alerts.push(alerts[i]);
        			} else {
        				msg = msg.replace('@', '');
        				alerts[i].msg = msg;
	        			$scope.alerts.push(alerts[i]);
        			}
        			return;
        		}
        	}
	    }

    	$scope.alert.retrieve = function (data) {
    		if(data) {
	    		$scope.alert('success', 'pbf.success', 'Retrieve');
	    	} else {
	    		$scope.alert('info', 'pbf.nodata');
	    	}
    	}
		
    	$scope.alert.save = function (data) {
    		if(data) {
	    		$scope.alert('success', 'pbf.success', 'Save');
	    	} else {
	    		$scope.alert('error', 'pbf.error');
	    	}
    	}
		
    	$scope.alert.delete = function (data) {
    		if(data) {
	    		$scope.alert('success', 'pbf.success', 'Delete');
	    	} else {
	    		$scope.alert('error', 'pbf.error');
	    	}
    	}
		
	    $scope.closeAlert = function(index) {
	        $scope.alerts.splice(index, 1);
		};
	}
	
	return  {
	    init: init
	  };
});


