$(document).ready(function() {
	$("input[type='text_content']").focus(function() {
		$(this).animate({
			"width" : "650px",
			"height" : "30px"
		})
	}).blur(function() {
		$(this).animate({
			"width" : "650px",
			"height" : "30px"
		})
	});
});