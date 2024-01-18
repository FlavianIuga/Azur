<g:if test="${flash.errorMessage }">
	<div id="message-red">
		<table border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td class="red-left">
					${flash.errorMessage}
				</td>
				<td class="red-right"><a class="close-red"><r:img class="optionIcon" uri="/images/table/icon_close_red.gif" width="55px" height="35px" /></a></td>
			</tr>
		</table>
	</div>
</g:if>

<g:if test="${flash.successMessage}">
	<div id="message-green">
		<table border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td class="green-left">
					${flash.successMessage}
				</td>
				<td class="green-right"><a class="close-green"><r:img class="optionIcon" uri="/images/table/icon_close_green.gif" width="55px"
							height="35px" /></a></td>
			</tr>
		</table>
	</div>
</g:if>

<g:if test="${flash.infoMessage}">
	<div id="message-blue">
		<table border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td class="blue-left">
					${flash.infoMessage}
				</td>
				<td class="blue-right"><a class="close-blue"><r:img class="optionIcon" uri="/images/table/icon_close_blue.gif" width="55px" height="35px" /></a></td>
			</tr>
		</table>
	</div>
</g:if>

