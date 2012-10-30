$(function(){
	$(".details_button").css("cursor","pointer");
	$(".details").css("display","none");
	$(".details_button").click(function(){
		$(".details").slideToggle();
	});
});
