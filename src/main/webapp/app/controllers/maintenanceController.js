(function (){
	 "use strict";
	 
	 angular.module('carApp.maintenanceCtrl',[]).controller('maintenanceCtrl',maintainanceCtrl);
	 
	 maintainanceCtrl.$inject = ['$scope','$log','maintenanceService'];
	 
	 function maintainanceCtrl(scope,log,maintenanceService){
		 
		 var vm = this;
		 
		 maintenanceService.showClientsReserved().then(success,error);
		 
		 function success(data){
			 log.info("[success] response ",data);
		 }
		 
		 function error(data){
			 log.info("[error] response ",data);
		 }
		 
	 }
	 
})();