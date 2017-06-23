(function () {
    angular.module("carApp.reserveCtrl",[]).	
    		controller('reserveCtrl',reserveController);
    reserveController.$inject = ['reserveService','$log'];
    function reserveController(reserveService,log){
    	var vm = this;
    	vm.car = reserveService.getCar();
    	vm.cars = reserveService.getCars();
    	vm.counts = vm.cars.length;
    	log.info("reservation ctrl car data ",vm.car);
    	log.info("reservation ctrl cars ",vm.cars);
	}
})();