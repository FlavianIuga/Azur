<table border="0" cellpadding="0" cellspacing="0" id="id-form">

	<g:if test="${contactInstance?.enabled}">

		<p>&nbsp;</p>
		<p>&nbsp;</p>

		<g:if test="${contactInstance?.nume}">

			<p>
			<h2>
				<g:link controller="contact" action="show"
					id="${contactInstance?.id}">
					<span class="showHeading" aria-labelledby="nume-label"><g:fieldValue
							bean="${contactInstance}" field="nume" /></span>
				</g:link>
				<span class="contactSmallFunctie">( ${contactInstance?.functie } )</span>
			</h2>
				
			</p>
		</g:if>

		<g:if test="${contactInstance?.telefonFix}">
			<tr>
				<th class="fieldcontain"><span id="telefonFix-label"
					class="property-label"><g:message
							code="contact.telefonFix.label" default="Telefon Fix" /></span></th>

				<td class="fieldcontain"><span class="property-value"
					aria-labelledby="telefonFix-label"><g:fieldValue
							bean="${contactInstance}" field="telefonFix" /></span></td>
			</tr>
		</g:if>

		<g:if test="${contactInstance?.telefonMobil}">
			<tr>
				<th class="fieldcontain"><span id="telefonMobil-label"
					class="property-label"><g:message
							code="contact.telefonMobil.label" default="Telefon Mobil" /></span></th>

				<td class="fieldcontain"><span class="property-value"
					aria-labelledby="telefonMobil-label"><g:fieldValue
							bean="${contactInstance}" field="telefonMobil" /></span></td>
			</tr>
		</g:if>

		<g:if test="${contactInstance?.email}">
			<tr>
				<th class="fieldcontain"><span id="email-label"
					class="property-label"><g:message code="contact.email.label"
							default="Email" /></span></th>

				<td class="fieldcontain"><span class="property-value"
					aria-labelledby="email-label"><a
						href="mailto:${contactInstance.email }"><g:fieldValue
								bean="${contactInstance}" field="email" /></a></span></td>
			</tr>
		</g:if>

	</g:if>

</table>