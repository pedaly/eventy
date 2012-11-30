$(function() {
	$(".details_button").css("cursor", "pointer");
	$(".details").css("display", "none");
	$(".details_button").click(function() {
		console.log("CLICK!");
		$(".details").slideToggle();
	});

});

function DoNav(theUrl) {
	document.location.href = theUrl;
}

function ChangeColor(tableRow, highLight) {
	if (highLight) {
		$(tableRow).addClass("overTableRow");
	} else {
		$(tableRow).removeClass("overTableRow");
	}
}