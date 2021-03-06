(function(){
	 "use strict";
   angular.module('car-app.directives').
     directive('datePicker', datePicker);

		  function datePicker() {
		    var result = {
		    		restrict :'AE',
		    		link : createDatePicker,
		    		replace: false,
		    		require: 'ngModel',
		    	    scope:{
		    	    	reserve:'='
		    	    }
		    };
		    function createDatePicker(scope, el, attr,ngModel) {
		     var value = null;
		     $(el).attr("readonly",""); 
		      $(el).datepicker({
		    	  maxDate: "1w",
		    	   minDate: new Date(),
		    	   defaultDate: 1, 
		           onSelect: function(dateText) {
		        	   scope.$apply(function() {
		        		   var pickUpDate = scope.$parent.reserveForm.pickUpDate.$viewValue;
		        		   if(pickUpDate !== dateText){
		        			   ngModel.$setViewValue(dateText);
		        			   var returnDate = scope.$parent.reserveForm.returnDate.$viewValue;
		        			   if(pickUpDate !== undefined && returnDate !== undefined){
		        				   var diffDays = carUtil.calculateDateDifference(pickUpDate,returnDate); 		
		        				   var totalPrice = diffDays * scope.$parent.reserve.objectCar.car.perDayPrice;
		        				   scope.$parent.reserve.item['totalPrice']=totalPrice;
		        				   scope.$parent.reserve.item['car']=scope.$parent.reserve.objectCar.car;
			        		   }
		        		   }else{
		        			   ngModel.$setViewValue(null);
			        		   console.log("return date must not be equal to pick up date");
			        		}
		                 });
		        }
		      });
		      
		   
		    }
		 return result;
	}
})();