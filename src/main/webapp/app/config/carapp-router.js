/**
 * 
 */

(function () {
    "use strict";
    var app = angular.module('car-app.router',['ui.router']);

        app.config(function($stateProvider, $urlRouterProvider) {

            $urlRouterProvider.otherwise('/');

            $stateProvider
            	.state('home', {
                    url: '/',
                    templateUrl: 'app/views/home.html',
                    controller :  'homeCtrl'
                })
            	.state('about', {
                    url: '/about',
                    templateUrl: 'app/views/about.html'  ,
                    controller :  'aboutCtrl'
                });

        });
})();