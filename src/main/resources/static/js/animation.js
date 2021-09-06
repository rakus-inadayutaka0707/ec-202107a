'use strict'

$(function() {
	$('#search').on('click', function() {
		$('.searchAnimation').addClass('animate__animated animate__rubberBand')
	})

	$('.deleteItemButton').on('click', function() {
		let orderItemId = $(this).val()
		/*alert($('#orderItemId' + orderItemId))*/
		$('#orderItemId' + orderItemId).addClass(
			'animate__animated animate__bounceOutRight'
		)
	})
})
