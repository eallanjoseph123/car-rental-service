(function (){
	"use strict";
	angular.module('car-app.services').
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
			
			function addReservation(item){
		     var data = Object.assign({}, item);
		     
		     var response = http({
	                method: 'POST',
	                url:window.baseUrl+'reserve/addReservation',
	                data: data,
	                headers: {
	                    'Content-Type': 'application/json'
	                }
	            }).then(
	                function(response) {
	                    return response.data;
	                }, 
	                function(errResponse) {
	                    console.error('Error !!');
	                    return $q.reject(errResponse);
	                });
				
				return response;
			}
		    
		    var service = {
		    		addCar:setCar,
		    		getCar:getCar,
		    		addCars:setCars,
		    		getCars:getCars,
		    		addReservation:addReservation
		    };
		    
		   return service;
		}
})();