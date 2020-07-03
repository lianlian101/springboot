function privilege(){
	$.ajax({
		url: '/privilege/getPrivilege',
		success: function(data){
			$.each($('.privilege'), function(index,value){
				let code = $(value).attr('privilege');
				let count = data.indexOf(code);
				if(count != -1){
					$(value).removeClass('privilege');
				}
			});
		}
	})	
}
$(window).on('load',function(){
    privilege();
});