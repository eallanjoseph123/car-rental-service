(function () {
	 "use strict";
    angular.module("carApp.homeCtrl",['ui.bootstrap','ngTable']).controller('homeCtrl',homeController);
    
    homeController.$inject = ['$scope','$log','homeService','NgTableParams','$window','reserveService'];
    
    function homeController($scope,log,homeService,NgTableParams,window,reserveService){
    	var vm = this;
    	
    	console.log("$scope ",$scope);
    	vm.index = 0;
    	
    	vm.addCar = function(car,index){
    		reserveService.addCar(car,index);
    	};
    	
    	 homeService.fetchAvailableCars().then(
     			function(success) {
     				log.info(success);
     				vm.customConfigParams = createUsingFullOptions(success);
     				reserveService.addCars(success);
         		},function(error) {
     				log.error(error);
         		}
         		
         		
     	 );
        function createUsingFullOptions(data) {
          var initialParams = {count:3};
          var initialSettings = {
            counts: [],
            paginationMaxBlocks: 13,
            paginationMinBlocks: 3,
            dataset: data
          };
          return new NgTableParams(initialParams, initialSettings);
        }
        
        
	}
})();