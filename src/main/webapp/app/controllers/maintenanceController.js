(function (){
	 "use strict";
	 
	 angular.module('carApp.maintenanceCtrl',['ui.bootstrap','ngTable']).controller('maintenanceCtrl',maintainanceCtrl);
	 
	 maintainanceCtrl.$inject = ['$scope','$log','maintenanceService','NgTableParams'];
	 
	 function maintainanceCtrl(scope,log,maintenanceService,NgTableParams){
		 
		 var vm = this;
		 
		 var successData;
		
		
		 maintenanceService.showClientsReserved().then(
				 function success(data){
					 log.info("[success] response ",data);
					 vm.customConfigParams = createUsingFullOptions(data);
				 }
				 ,function error(data){
					 log.info("[error] response ",data);
				 });
		
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