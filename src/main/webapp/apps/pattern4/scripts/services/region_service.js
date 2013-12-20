'use strict';

app.factory('RegionServiceGet', function ($resource, config) {
	return $resource(config.url + "/uip_regions/:uip_center_code/:code", {
		uip_center_code:"@uip_center_code",
		code:"@code"
	}, {
		update: {
			method: "PUT"
		}
	});
});

app.factory('RegionService', function ($resource, config) {
	return $resource(config.url + "/uip_regions/:uip_center_id/:id", {
		uip_center_id:"@uip_center_id",
		id:"@id"
	}, {
		update: {
			method: "PUT"
		}
	});
});
