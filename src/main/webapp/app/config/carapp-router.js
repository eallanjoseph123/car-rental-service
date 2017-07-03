(function () {
    "use strict";
    var app = angular.module('car-app.router',['ui.router']);

        app.config(function($stateProvider, $urlRouterProvider,$locationProvider) {

            $urlRouterProvider.otherwise('/carApp');

            $stateProvider
            	.state('home', {
                    url: '/',
                    templateUrl: 'app/views/home.html',
                    controller :  'homeCtrl as home'
                })
            	.state('about', {
                    url: '/about',
                    templateUrl: 'app/views/about.html',
                    controller :  'aboutCtrl as about'
                })
                .state('reserve', {
                    url: '/reserve',
                    templateUrl: 'app/views/reservation.html',
                    controller :  'reserveCtrl as reserve'
                }).state('login', {
                    url: '/login',
                    templateUrl: 'app/views/maintanance.html',
                    controller :  'maintenanceCtrl as maintain'
                });            
            //for removable of # in url
            $locationProvider.html5Mode(true);
        });
})();