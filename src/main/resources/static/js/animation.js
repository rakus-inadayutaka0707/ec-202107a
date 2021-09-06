'use strict'

$(function() {
	$('#search').on('click', function() {
		let code = $('#code').val();
		if(code === ""){
			$('.notSearchAnimation').addClass('animate__animated animate__rubberBand')	
		}else{
			$('.searchAnimation').addClass('animate__animated animate__zoomOutUp')
		}
	})

	$('.deleteItemButton').on('click', function() {
		let orderItemId = $(this).val()
		/*alert($('#orderItemId' + orderItemId))*/
		$('#orderItemId' + orderItemId).addClass(
			'animate__animated animate__bounceOutRight'
		)
	})
})
