$(function(){
	$("#back-to-top").click(function(){
		$('html,body').animate({scrollTop:0}, 200);
	});
	var ie6 = (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf("MSIE 6.0") != -1);
	$(window).scroll(function(){
		if(ie6){
			var windowHeight = $(window).height();
			$("#back-to-top").css('top', windowHeight+$(window).scrollTop()-50);
		}
		if ($(window).scrollTop()>100){
			$("#back-to-top").fadeIn(300);
		}else{
			$("#back-to-top").fadeOut(300);
		}
	});
	if(ie6){
		var windowHeight = $(window).height();
		$("#back-to-top").css('top', windowHeight+$(window).scrollTop()-50);
	}
	if ($(window).scrollTop()>100){
		$("#back-to-top").fadeIn(300);
	}else{
		$("#back-to-top").fadeOut(300);
	}
});