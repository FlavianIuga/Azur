modules = {
	application {
		dependsOn 'jquery'

		resource url:'js/application.js'
		resource url:'js/jquery/Copy of custom_jquery.js'
		resource url:'js/jquery/custom_jquery.js'
		resource url:'js/jquery/jquery.bind.js'
		resource url:'js/jquery/jquery.datePicker.js'
		resource url:'js/jquery/jquery.dimensions.js'
		resource url:'js/jquery/jquery.filestyle.js'
		resource url:'js/jquery/jquery.pngFix.pack.js'
		resource url:'js/jquery/jquery.selectbox-0.5_style_2.js'
		resource url:'js/jquery/jquery.selectbox-0.5_style_3.js'
		resource url:'js/jquery/jquery.selectbox-0.5.js'
		resource url:'js/jquery/jquery.tooltip.js'
		resource url:'js/jquery/jquery.usermode.js'
		resource url:'js/jquery/jquery-1.4.1.min.js'
		resource url:'js/jquery/ui.checkbox.js'
		resource url:'js/jquery/ui.core.js'

		resource url:'css/datePicker.css'
		resource url:'css/pro_dropline.css'
		resource url:'css/pro_dropline_ie.css'
		resource url:'css/screen.css'
		resource url:'css/print.css', attrs:[media:'print']

		resource url:'images/shared/nav/nav_myaccount.gif', attrs:[width:93, height:14, alt:'']
		resource url:'images/shared/nav/nav_logout.gif', attrs:[width:64, height:14, alt:'']
		resource url:'images/shared/top_search_btn.gif', attr:[width:65, height:29, alt:'']
		resource url:'images/shared/side_shadowleft.jpg', attr:[width:20, height:300, alt:'']
		resource url:'images/shared/side_shadowright.jpg', attr:[width:20, height:300, alt:'']
		resource url:'images/forms/header_related_act.gif', attr:[width:271, height:43, alt:'']
		resource url:'images/table/icon_close_red.gif', attr:[width:55, height:35, alt:'']
		resource url:'images/forms/selectie.png', attr:[width:185, height:43, alt:'']
		
		resource url:'images/logo/logo_banner.jpg', attr:[width:538, height:70, alt:'']
		
		//myscripts
		resource url:'js/myscripts/cautare.js'
		
		// icons
		resource url:'images/icons/onebit_02.png', attr:[width:24, height:24, alt:'Cautare']
		resource url:'images/icons/onebit_39.png', attr:[width:24, height:24, alt:'Printare']
		resource url:'images/icons/onebit_11.png', attr:[width:24, height:24, alt:'Printare']
		resource url:'images/icons/delete.png', attr:[width:24, height:24, alt:'Stergere']
}
}