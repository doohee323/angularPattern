'use strict';

var config = {
	// url : 'http://localhost\\:3000',
	// url: 'http://sheeprails.herokuapp.com',
	url : 'http://localhost\\:8080/rest',
	// url : '/pattern/pt42/masterdetail',
	server : 'spring', // spring, rails,
	uip_center : {}
};

var app = angular.module('sheepwebApp',
		[ 'ngResource', 'ui.router', 'ngGrid' ])

app.constant('config', config).config(
function($stateProvider, $urlRouterProvider, $locationProvider) {
	// default route
	$urlRouterProvider.otherwise("/");

	// default route
	$stateProvider.state('default', {
		templateUrl : '/apps/pattern2/views/layout/default.html',
		controller : 'DefaultCtrl',
		abstract : true
	}).state('default.centers', {
		templateUrl : '/apps/pattern2/views/centers.html',
		controller : 'CentersCtrl',
	}).state('default.regions', {
		url : "/regions/:id",
		templateUrl : '/apps/pattern2/views/regions.html',
		controller : 'RegionsCtrl',
	});

	$locationProvider.html5Mode(true).hashPrefix('!');

});
