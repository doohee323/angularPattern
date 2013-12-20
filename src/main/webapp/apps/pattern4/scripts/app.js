'use strict';

var config = {
//	url : 'http://localhost\\:3000',
//	url: 'http://sheeprails.herokuapp.com',
	url : 'http://localhost\\:8080/rest',
	// url : '/pattern/pt42/masterdetail',
	server: 'spring' // spring, rails
};

var app = angular.module('sheepwebApp',
		[ 'ngCookies',
          'ngResource',
          'ngSanitize',
          'ui.router',
          'ui.bootstrap'])

app.constant('config', config)
	.config(function($stateProvider, $urlRouterProvider, $locationProvider) {
		// default route
		$urlRouterProvider.otherwise("/");
		
		// default route
		$stateProvider.state('default', {
			templateUrl : '/apps/pattern4/views/layout/default.html',
			controller : 'DefaultCtrl',
			abstract : true
		}).state('default.centers', {
			templateUrl : '/apps/pattern4/views/centers.html',
			controller : 'CentersCtrl'
		});

		$locationProvider.html5Mode(true).hashPrefix('!');

});
