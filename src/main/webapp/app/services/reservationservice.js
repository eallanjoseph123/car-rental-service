(function (){
	angular.module('carApp.reserveService',[]).
			factory('reserveService',reserveService);
		
		reserveService.$inject = ['$http','$q','$window'];
		
		
		function reserveService(http,q,window){
			var vm = this;
			
			vm.car = {};		    
			
			vm.cars = {};
			
			function setCars(cars){
		    	vm.cars = cars;
		    	
			}
			function getCars(){
				return vm.cars;
			}
		    
			
			function setCar(car,index){
		    	vm.car = {car:car,index,index};
		    	
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