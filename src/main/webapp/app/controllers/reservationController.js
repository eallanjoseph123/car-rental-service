(function () {
	 "use strict";
	 
    angular.module("carApp.reserveCtrl",['ui.bootstrap']).	
    		controller('reserveCtrl',reserveController);
    
    reserveController.$inject = ['$scope','reserveService','$log','$window'];
    
    function reserveController($scope,reserveService,log,$window){
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
    		reserveService.addReservation(item).then(succes,error);
    	};
    	
    	
    	vm.myRightButton = function(){
    		$window.location.href = $window.baseUrl;
    	}
    	
    	function succes(data){
    		log.info("[success] response after adding reservation",data);
    		vm.modalBody = 'Success you will be received an email for your reservation '+ data.reserveNumber ;
    	}
    	
    	function error(data){
    		log.info("[error] response after adding reservation",data);
    	}
    	
	}
})();