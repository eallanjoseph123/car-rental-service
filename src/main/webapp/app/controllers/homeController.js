(function () {
	 "use strict";
    angular.module("carApp.homeCtrl",['ui.bootstrap','ngTable']).controller('homeCtrls',homeController);
    homeController.$inject = ['$scope','$log','homeService','NgTableParams','$window'];
    function homeController($scope,log,homeService,NgTableParams,window){
    	var vm = this;
    	 homeService.fetchAvailableCars().then(
     			function(success) {
     				log.info(success);
     				vm.customConfigParams = createUsingFullOptions(success);
         		},function(error) {
     				log.error(error);
         		}
         		
     	 );
        function createUsingFullOptions(data) {
          var initialParams = {count:5};
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