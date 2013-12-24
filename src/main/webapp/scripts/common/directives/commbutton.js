'use strict';

app.directive('commbutton', function($compile) {
		var getTemplate = function(aType, aData, aCallback) {
			var template = '<button id="' + aType + aData + '" class="btn btn-primary" type="button" ng-click="' + aType + 'Data';
			if(aCallback) {
				template += '(\'' + aCallback + '\')"';
			} else {
				template += '()"';
			}
			template += '>' + aType + '</button>';
			return template;
		};

		var linker = function(scope, element, attr) {
			element.html(getTemplate(attr["type"], attr["data"], attr["callback"])).show();
//			element.bind('click', function() {
//				eval('scope.' + attr["type"] + attr["data"] + '()');
//				return;
//			});
			$compile(element.contents())(scope);
		};

		return {
			restrict : 'E',
			link : linker,
			replace : true
		};
	});

app.directive('ngBlur', function () {
  return function (scope, elem, attrs) {
    elem.bind('blur', function () {
      scope.$apply(attrs.ngBlur);
    });
  };
});
