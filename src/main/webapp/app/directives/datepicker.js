(function(){
   angular.module('carapp.datePicker',[]).
     directive('datePicker', datePicker);

		  function datePicker() {
		    var picker = {
		    		restrict :'AE',
		    		link : date,
		    		replace: false
		    };
		    function date(scope, el, attr) {
		      $(el).datepicker({
		    	    minDate: new Date(),
		    	   defaultDate: 1, 
		           onSelect: function(dateText) {
		          console.log(dateText);
		        }
		      });
		    $(el).attr( "readonly","" ); 
		    }
		 return picker;
	}
})();