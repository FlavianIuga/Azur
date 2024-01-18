<table id="showtable" class="property-list firma">

	<g:each in="${fieldList}" status="i" var="field">
		<g:if test="${instance}">
			<tr>
				<th class="fieldcontain"><span id="${fieldNameList[i]}-label" class="property-label"><g:message
							code="instance.instance.label" default="${fieldNameList[i]}" /></span></th>

				<td class="fieldcontain"><span class="showHeading" aria-labelledby="${fieldNameList[i]}-label"> <g:if
							test="${field=='firma'}">
							<g:link controller="firma" action="show" id="${instance?.firma?.id }">
								<g:fieldValue bean="${instance}" field="${field}" />
							</g:link>

						</g:if> <g:if test="${field=='dataProductie'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.dataProductie}" />
						</g:if> <g:if test="${field=='dataComandaFurnizor'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.dataComandaFurnizor}" />
						</g:if> <g:if test="${field=='dataFacturaFurnizor'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.dataFacturaFurnizor}" />
						</g:if> <g:if test="${field=='dataComandaClient'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.dataComandaClient}" />
						</g:if> <g:if test="${field=='dataFacturaClient'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.dataFacturaClient}" />
						</g:if> <g:if test="${field=='termenPornire'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.termenPornire}" />
						</g:if> <g:if test="${field=='termenLimita'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.termenLimita}" />
						</g:if> <g:if test="${field=='dataCreare'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.dataCreare}" />
						</g:if> <g:if test="${field=='dataPornire'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.dataPornire}" />
						</g:if> <g:if test="${field=='idActiune'}">
							<g:link controller="actiune" action="completeShow" id="${instance?.id }">
								<g:fieldValue bean="${instance}" field="${field}" />
							</g:link>
						</g:if> <g:if test="${field=='email'}">
							<a href="mailto:${instance.email}"> <g:fieldValue bean="${instance}" field="${field}" />
							</a>

						</g:if>
						
						 <g:if test="${field == 'enabled'}">
						 	<g:if test="${fieldValue(bean: instance, field: field) == 'true'}">
								Da
							</g:if>
							
							<g:if test="${fieldValue(bean: instance, field: field) == 'false'}">
								Nu
							</g:if>
							
						</g:if>
						
						 	<g:if test="${field=='observatii'}">
						 		${instance?."${field}"} <br/><g:link controller="produs" action="show" id="${obsLinkId}">${obsLink}</g:link> 
						 	</g:if>
						 	
						 	<g:if test="${field=='password'}">
						 		
						 	</g:if>
						 	
						 	<g:if test="${field=='admin'}">
						 		${adminFlag }
						 	</g:if>
						 	
						<g:if
							test="${field != 'firma' && field != 'idActiune' && field != 'email' && field!='dataProductie' && field!='dataProductie' && field!='dataComandaFurnizor' && field!='dataFacturaFurnizor' && field!='dataComandaClient' && field!='dataFacturaClient' && field!='termenPornire' && field!='termenLimita' && field!='dataCreare' && field!='dataPornire' && field!='password' && field!='observatii' && field!='admin' && field!='enabled'}">
							<g:fieldValue bean="${instance}" field="${field}" />
						</g:if>


				</span></td>
			</tr>
		</g:if>

	</g:each>

</table>