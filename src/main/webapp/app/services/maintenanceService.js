(function (){
	 "use strict";
	 
	 angular.module('carApp.maintenanceService',[]).service('maintenanceService',maintenanceService);
	 
	 maintenanceService.$inject = ['$http','$q','$window'];
	 
	 function maintenanceService(http,q,window){
		 
		 function showClientsReserved() {
			 var method = constants.GET;
			 console.log("method ",method);
			 var response = http({
	                method: method,
	                url:window.baseUrl+'reserve/viewAllReservation',
	                data: '',
	                headers: {
	                    'Content-Type': 'application/json'
	                }
	            }).then(
	                function(response) {
	                    return response.data;
	                }, 
	                function(errResponse) {
	                    return $q.reject(errResponse);
	                });
			 
			 return response;
		 }
		 
		 var service = {
		     showClientsReserved:showClientsReserved
		 };
		 return service;
		 
	 }
	 
})();