<table id="evenimentTable" class="property-list firma">

	<tr>
		<g:each in="${fieldList}" status="i" var="field">
			<g:if test="${instance}">

				<th><span id="${fieldNameList[i]}-label" class="property-label"><g:message
							code="instance.instance.label" default="${fieldNameList[i]}" /></span></th>

				<td>
					<span class="showHeading" aria-labelledby="${fieldNameList[i]}-label">
				
						<g:if test="${field=='observatie'}">
								${instance?."${field}"}
								<br />
								<g:link controller="produs" action="show" id="${obsLinkId}">
									${obsLink}
								</g:link>
								
						</g:if>
						
						<g:if test="${field!='dataEveniment' && field!='observatie'}">
							<g:fieldValue bean="${instance}" field="${field}" />
						</g:if>

					</span>
				</td>

			</g:if>

		</g:each>
	</tr>

</table>