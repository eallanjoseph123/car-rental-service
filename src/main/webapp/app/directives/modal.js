(function(){
	 "use strict";
	angular.module('carapp-modal',[]).directive('carModal',carModal);
	
	function carModal(){
		  var result =  {
			        restrict: 'EA',
			        scope: {
			            title: '=modalTitle',
			            header: '=modalHeader',
			            body: '=modalBody',
			            footer: '=modalFooter',
			            callbackbuttonleft: '&ngClickLeftButton',
			            callbackbuttonright: '&ngClickRightButton',
			            handler: '=modalName'
			        },
			        templateUrl: 'app/views/carModalTemplate.html',
			        transclude: true,
			        controller: function ($scope) {
			        	$scope.handler = 'car-modal'; 
			        },
			    };
		
		return result;
	}
})();