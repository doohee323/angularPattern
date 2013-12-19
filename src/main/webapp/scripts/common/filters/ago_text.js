'use strict';

app.filter('agoText', function () {
    return function (input) {
    	moment.lang('en');
    	return moment(input).fromNow();
    };
  });
