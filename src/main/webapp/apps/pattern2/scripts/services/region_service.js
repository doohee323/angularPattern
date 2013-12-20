'use strict';

app.factory('RegionService', function ($resource, config) {
	debugger;
	return $resource(config.url + "/uip_regions/:uip_center_code/:code", {
		uip_center_code:"@uip_center_code",
		code:"@code"
	}, {
		update: {
			method: "PUT"
		}
	});
});