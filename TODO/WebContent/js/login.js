$(document).ready(function() {
	function animation(delay, color) {
		//first
		$(".circle").first().animate({
			backgroundColor : color
		}, delay, function() {
			$(this).animate({
				backgroundColor : '#656565'
			}, delay);
			//second
			$(this).next().animate({
				backgroundColor : color
			}, delay, function() {
				$(this).animate({
					backgroundColor : '#656565'
				}, delay);
				//third
				$(this).next().animate({
					backgroundColor : color
				}, delay, function() {
					$(this).next().animate({
						backgroundColor : color
					}, delay, function() {
						$(this).animate({
							backgroundColor : '#656565'
						}, delay);
						$(this).prev().animate({
							backgroundColor : '#656565'
						}, delay);
					});
					$(this).next().next().animate({
						backgroundColor : color
					}, delay, function() {
						$(this).animate({
							backgroundColor : '#656565'
						}, delay);
					});
					setTimeout(animation, 1500, 500, '#31B0D5');
				});
			});
		});
	}
	setTimeout(animation, 1500, 500, '#31B0D5');
});