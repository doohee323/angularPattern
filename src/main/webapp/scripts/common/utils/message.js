'use strict';

app.factory('MessageCtrl', function () {
	var $scope;
	function init(scope) {
		$scope = scope;
	    $scope.alerts = [];
		
		$scope.alert = function (data, type, input) {
	        var alerts = [{ type: 'success', msg: 'Sucess!' },
	                      { type: 'info', msg: 'No data!' },
	                      { type: 'required', msg: 'Required! @' },
	                      { type: 'error', msg: 'Error!' }
	                      ];
	        
	        if(type) {
	        	for(var i=0;i<alerts.length;i++) {
	        		if(alerts[i].type == type) {
        				var msg = alerts[i].msg;
	        			if(input) {
	        				msg = msg.replace('@', input);
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
	        } else {
		        if(data) {
		    	 	$scope.alerts.push(alerts[0]);
				} else {
		    	 	$scope.alerts.push(alerts[1]);
				}
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


