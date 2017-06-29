(function () {
	 "use strict";
	 
    angular.module("carApp.reserveCtrl",['ui.bootstrap']).	
    		controller('reserveCtrl',reserveController);
    
    reserveController.$inject = ['$scope','reserveService','$log'];
    
    function reserveController($scope,reserveService,log){
    	var vm = this;
    	vm.objectCar = reserveService.getCar();
    	vm.item = vm.item  || [];
    	vm.cars = reserveService.getCars();
    	vm.counts = vm.cars.length;
    	
    	var curentIndex = vm.objectCar.index;
    	
    	vm.slider = {
    		active: 0,  
    		interval: 5000,
    		enableSlide:false
    	};
    	
    	vm.checkOut = function(item){
    		log.info("item ",item);
    		var response = reserveService.addReservation(item);
    		log.info("response ",response);
    	};
    	
	}
})();