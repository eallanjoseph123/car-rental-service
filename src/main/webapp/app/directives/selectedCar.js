(function(){
	  angular.module('carapp.selectedCar',[]).
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