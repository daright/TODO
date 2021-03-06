$(document).ready(function() {
	$(".check").hover(function() {
		$(this).parent().children().css({
			'top' : '-6px'
		});
		$(this).css({
			'left' : '-10px',
			'height' : 'auto'
		});
		$(this).html($("<p>Check</p>"));
	}, function() {
		$(this).parent().children().css({
			'top' : '1px',
			'left' : '0px',
			'height' : '50px'
		});
		$(this).html($(""));
	});

	$(".uncheck").hover(function() {
		$(this).parent().children().css({
			'top' : '-6px'
		});
		$(this).css({
			'left' : '-16px',
			'height' : 'auto'
		});
		$(this).html($("<p>Uncheck</p>"));
	}, function() {
		$(this).parent().children().css({
			'top' : '1px',
			'left' : '0px',
			'height' : '50px'
		});
		$(this).html($(""));
	});

	$(".glyphicon-pencil").hover(function() {
		$(this).parent().children().css({
			'left' : '-5px',
			'top' : '-6px'
		});
		$(this).css({
			'left' : '-5px',
			'height' : 'auto'
		});
		$(this).html($("<p>Edit</p>"));
	}, function() {
		$(this).parent().children().css({
			'top' : '1px',
			'left' : '0px',
			'height' : '50px'
		});
		$(this).html($(""));
	});

	$(".glyphicon-trash").hover(function() {
		$(this).parent().children().css({
			'left' : '-15px',
			'top' : '-6px'
		});
		$(this).css({
			'left' : '-15px',
			'height' : 'auto'
		});
		$(this).html($("<p>Remove</p>"));
	}, function() {
		$(this).parent().children().css({
			'top' : '1px',
			'left' : '0px',
			'height' : '50px'
		});
		$(this).html($(""));
	});

	$(".glyphicon-plus").hover(function() {
		$(this).parent().children().css({
			'left' : '-4px',
			'top' : '-6px'
		});
		$(this).css({
			'left' : '-4px',
			'height' : 'auto'
		});
		$(this).append($("<p>Add</p>"));
	}, function() {
		$(this).parent().children().css({
			'top' : '1px',
			'left' : '0px',
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
		if (e.keyCode == 13) {
			e.preventDefault();
			$(':focus').next().children().first().click();
		}
	});

	$('textarea[name="item"]').keydown(function(e) {
		if (e.keyCode == 13) {
			e.preventDefault();
			$(':focus').next().next().next().children().first().click();
		}
	});
});