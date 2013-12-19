'use strict';

app.factory('CenterService', function ($resource, config) {
	return $resource(config.url + "/uip_centers/:id", {
		id:"@id"
	}, {
		update: {
			method: "PUT"
		}
	});
});
