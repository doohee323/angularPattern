'use strict';

angular.module('sheepwebApp')
.controller('MainCtrl',function($scope) {
	$scope.title = 'UI Pattern driven!';
	$scope.slogan = 'Always a pleasure copying your apps.';

	$scope.patterns = [
		{
			name : 'P1 Single Detail',
			image : 'images/1.png',
			desc : 'Perform on a screen for CRUD actions.',
			sample : 'apps/pattern1/index.html'
		},
		{
			name : 'P2 Multi Detail (Edit)',
			image : 'images/2.png',
			desc : 'Retrieve list and perform CRUD actions for multi-rows on a screen.',
			sample : 'apps/pattern2/index.html'
		},
		{
			name : 'P3 Multi Detail (List to Edit)',
			image : 'images/3.png',
			desc : 'Retrieve list and perform CRUD actions for 1 row using by two screens.',
			sample : 'apps/pattern3/index.html'
		},
		{
			name : 'P4 Master / Detail [1:n]',
			image : 'images/4.png',
			desc : 'Retrieve single master data and perform CRUD actions for multi detail data on a screen.',
			sample : 'apps/pattern4/index.html'
		},
		{
			name : 'P5 Master / Detail [n:1]</h4>',
			image : 'images/5.png',
			desc : 'Retrieve multi master data and perform CRUD actions for sigle detail data on a screen.',
			sample : '/'
		},
		{
			name : 'P6 Master / Detail [n:n]',
			image : 'images/6.png',
			desc : 'Retrieve multi master data and perform CRUD actions for multi detail data on a screen.',
			sample : '/'
		} ];
});
