'use strict';

app.factory('CenterService', function ($resource, config) {
  return {
    R: $resource(config.url + "/uip_centers/:queryCode", {
    	queryCode:"@queryCode"
	}),
    CUD: $resource(config.url + "/uip_centers/:id", {
		id:"@id"
		}, {
			update: {
				method: "PUT"
			}
		})
	};
});

