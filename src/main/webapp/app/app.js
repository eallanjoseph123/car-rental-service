(function () {
    "use strict";
        angular.module('car-app',
        [ 'car-app.router',
          'car-app.services',
          'car-app.controller',
	      'car-app.directives'
        ]).
        
        
        config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }])
        .controller('mainController',mainController);
        
        mainController.$inject = ['$window','$scope','$log'];
        
        function mainController(window,scope,log){
        	var vm = this;
        	
        	var year = new Date().getFullYear();
        	vm.modalApp = {
        		header:"Car App",
        		footer:"All rights reserved "+ year
        	};
        	window.baseUrl = window.location.href;
        	
        	log.info("main controller ");
        	
        }

})();