(function (){
	 "use strict";
	angular.module("carApp.homeService",[])
		   .factory('homeService',homeService);
			homeService.$inject = ['$http','$q','$window'];
	
	function homeService($http,$q,window){
		var service = {
				fetchAvailableCars:fetchAvailableCars
		};
		
		return service;
		
		function fetchAvailableCars(){
			return $http.get(window.baseUrl+'home/showAvailableCars')
            .then(
            		function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting cars');
                        return $q.reject(errResponse);
                    }
            );
		};
		
		
	}
	 
})();