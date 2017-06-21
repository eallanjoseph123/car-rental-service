(function () {
    "use strict";
        angular.module('car-app',
        [ 'car-app.router',
          'car-app.services',
          'car-app.controller'
        ]).
        
        
        config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }])
        .controller('mainController',mainController);
        
        mainController.$inject = ['$window','$scope','$log'];
        
        function mainController(window,scope,log){
        	window.baseUrl = window.location.href;
        	log.info("main controller ");
        	
        }

})();