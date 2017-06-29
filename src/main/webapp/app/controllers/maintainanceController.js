(function (){
	 "use strict";
	 
	 angular.module('carApp.maintainanceCtrl',[]).controller('maintainanceCtrl',maintainanceCtrl);
	 
	 maintainanceCtrl.$inject = ['$scope'];
	 function maintainanceCtrl(scope){
		 var vm = this;
		 vm.cop = "may test";
	 }
	 
})();