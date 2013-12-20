'use strict';

app.factory('CenterService', function ($resource, config) {
	return $resource(config.url + "/uip_centers/:code/:id", {
		id:"@id",
		code:"@code"
	}, {
		update: {
			method: "PUT"
		}
	});

});
