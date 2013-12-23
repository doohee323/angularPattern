'use strict';

app.factory('CenterService', function ($resource, config) {
	return $resource(config.url + "/uip_centers/:queryCode", {
		queryCode:"@queryCode"
	}, {
		update: {
			method: "PUT"
		}
	});

});
