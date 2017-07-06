var carUtil = (function(){
	
	var calculateDateDifference  = function(date1,date2){
		var pickUpDate = new Date(date1);
		var returnDate = new Date(date2);
		return parseInt((returnDate - pickUpDate) / (1000 * 60 * 60 * 24)); 
	};
	
	var service = {
			calculateDateDifference : calculateDateDifference
	};
	
	return service;
})();