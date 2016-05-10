$(document).ready(function() {
	$(".check").hover(function() {
		$(this).parent().children().css({
			'top' : '-6px'
		});
		$(this).css({
			'height' : 'auto'
		});
		$(this).html($("<p>Check</p>"));
	}, function() {
		$(this).parent().children().css({
			'top' : '1px',
			'height' : '50px'
		});
		$(this).html($(""));
	});

	$(".uncheck").hover(function() {
		$(this).parent().children().css({
			'top' : '-6px'
		});
		$(this).css({
			'height' : 'auto'
		});
		$(this).html($("<p>Uncheck</p>"));
	}, function() {
		$(this).parent().children().css({
			'top' : '1px',
			'height' : '50px'
		});
		$(this).html($(""));
	});

	$(".glyphicon-pencil").hover(function() {
		$(this).parent().children().css({
			'top' : '-6px'
		});
		$(this).css({
			'height' : 'auto'
		});
		$(this).html($("<p>Edit</p>"));
	}, function() {
		$(this).parent().children().css({
			'top' : '1px',
			'height' : '50px'
		});
		$(this).html($(""));
	});

	$(".glyphicon-trash").hover(function() {
		$(this).parent().children().css({
			'top' : '-6px'
		});
		$(this).css({
			'height' : 'auto'
		});
		$(this).html($("<p>Remove</p>"));
	}, function() {
		$(this).parent().children().css({
			'top' : '1px',
			'height' : '50px'
		});
		$(this).html($(""));
	});

	$(".glyphicon-plus").hover(function() {
		$(this).parent().children().css({
			'top' : '-6px'
		});
		$(this).css({
			'height' : 'auto'
		});
		$(this).append($("<p>Add</p>"));
	}, function() {
		$(this).parent().children().css({
			'top' : '1px',
			'height' : '50px'
		});
		$(this).html($(""));
	});
	function h(e) {
		$(e).css({
			'height' : 'auto',
			'overflow-y' : 'hidden'
		}).height(e.scrollHeight);
	}
	$('textarea').each(function() {
		h(this);
	}).on('input', function() {
		h(this);
	});

	$('textarea').keydown(function(e) {
	     if(e.keyCode == 13) {
	       e.preventDefault(); // Makes no difference
	        $(':focus').next().children().first().click();
	   }
	});
	function animation() {
		$(".circle").animate({
			'background-color': '#757575',
			'width' : '1000px'
		}, function(){
			$(this).animate({
				backgroundColor : '#656565'
			}, animation())
		});
	}
	animation();
});