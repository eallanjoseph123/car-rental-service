(function(){
	 "use strict";
	  angular.module('car-app.directives').
	     directive('selectedCar', selectedCar);
	  
		  function selectedCar(){
			  var result = {
					     restrict :'AE',
			    		link : createView,
			    		replace: false,
			    		templateUrl:"app/views/selected-car.html",
			    		
			  };
			  
			  function createView(scope, el, attr) {
				  
			  }
			  
			  return result;
		  }
})();