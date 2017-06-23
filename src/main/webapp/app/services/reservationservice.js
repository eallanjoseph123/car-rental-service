(function (){
	angular.module('carApp.reserveService',[]).
			factory('reserveService',reserveService);
		
		reserveService.$inject = ['$http','$q','$window'];
		
		
		function reserveService(http,q,window){
			var vm = this;
			
			vm.car = null;		    
			vm.cars = {};
			
			function setCars(cars){
		    	vm.cars = cars;
		    	
			}
			function getCars(){
				return vm.cars;
			}
		    
			
			function setCar(car){
		    	vm.car = car;
		    	
			}
			function getCar(){
				return vm.car;
			}
		    
		    var service = {
		    		addCar:setCar,
		    		getCar:getCar,
		    		addCars:setCars,
		    		getCars:getCars
		    };
		    
		   return service;
		}
})();