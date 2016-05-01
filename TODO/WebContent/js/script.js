$(document).ready(function() {
	$(".check").hover(function() {
		$(this).parent().siblings().children().css({
			'position' : 'relative',
			'top' : '-14px'
		});
		$(this).html($("<p>Check</p>"));
	}, function() {
		$(this).parent().siblings().children().css({
			'position' : 'relative',
			'top' : '0px'
		});
		$(this).html($(""));
	});
	
	$(".uncheck").hover(function() {
		$(this).parent().siblings().children().css({
			'position' : 'relative',
			'top' : '-14px'
		});
		$(this).html($("<p>Uncheck</p>"));
	}, function() {
		$(this).parent().siblings().children().css({
			'position' : 'relative',
			'top' : '0px'
		});
		$(this).html($(""));
	});
	
	$(".glyphicon-pencil").hover(function() {
		$(this).parent().siblings().children().css({
			'position' : 'relative',
			'top' : '-14px'
		});
		$(this).html($("<p>Edit</p>"));
	}, function() {
		$(this).parent().siblings().children().css({
			'position' : 'relative',
			'top' : '0px'
		});
		$(this).html($(""));
	});
	
	$(".glyphicon-trash").hover(function() {
		$(this).parent().siblings().children().css({
			'position' : 'relative',
			'top' : '-14px'
		});
		$(this).html($("<p>Remove</p>"));
	}, function() {
		$(this).parent().siblings().children().css({
			'position' : 'relative',
			'top' : '0px'
		});
		$(this).html($(""));
	});
	
	$(".glyphicon-plus").hover(function() {
		$(this).parent().siblings().children().css({
			'position' : 'relative',
			'top' : '-14px'
		});
		$(this).append($("<p>Add</p>"));
	}, function() {
		$(this).parent().siblings().children().css({
			'position' : 'relative',
			'top' : '0px'
		});
		$(this).html($(""));
	});
});