<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'arhiva.label', default: 'Arhiva')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>

<parameter name="selectedAcasa" value="select" />
<parameter name="selectedActiuni" value="select" />
<parameter name="selectedFirme" value="select" />
<parameter name="selectedProduse" value="select" />
<parameter name="selectedTeme" value="select" />
<parameter name="selectedAdmin" value="current" />


</head>
<body>

	<content tag="heading">
	<h1>
		<g:message code="default.show.label" args="[entityName]" />
	</h1>
	</content>

	<tmpl:/templatesMessages/error />

	<tmpl:/templates/show />

	<table id="showtable" class="property-list firma">

		<g:if test="${arhivaInstance?.user}">
			<tr>
				<th class="fieldcontain"><span id="user-label"
					class="property-label"><g:message code="fir.user.label"
							default="User" /></span></th>

				<td class="fieldcontain"><span class="property-value"
					aria-labelledby="user-label"><g:fieldValue
							bean="${arhivaInstance}" field="user" /></span></td>
			</tr>
		</g:if>

		<g:if test="${arhivaInstance?.firma}">
			<tr>
				<th class="fieldcontain"><span id="firma-label"
					class="property-label"><g:message code="fir.firma.label"
							default="Firma" /></span></th>

				<td class="fieldcontain"><g:link controller="firma"
						action="show" id="${arhivaInstance?.firma?.id}">
						<span class="property-value" aria-labelledby="firma-label"><g:fieldValue
								bean="${arhivaInstance}" field="firma" /></span>
					</g:link></td>
			</tr>
		</g:if>

		<g:if test="${arhivaInstance?.contact}">
			<tr>
				<th class="fieldcontain"><span id="contact-label"
					class="property-label"><g:message code="fir.user.label"
							default="Contact" /></span></th>

				<td class="fieldcontain"><g:link controller="contact"
						action="show" id="${arhivaInstance?.contact?.id}">
						<span class="property-value" aria-labelledby="contact-label"><g:fieldValue
								bean="${arhivaInstance}" field="contact" /></span>
					</g:link></td>
			</tr>
		</g:if>

		<g:if test="${arhivaInstance?.tema}">
			<tr>
				<th class="fieldcontain"><span id="tema-label"
					class="property-label"><g:message code="fir.tema.label"
							default="Tema" /></span></th>

				<td class="fieldcontain"><g:link controller="tema"
						action="show" id="${arhivaInstance?.tema?.id}">
						<span class="property-value" aria-labelledby="tema-label"><g:fieldValue
								bean="${arhivaInstance}" field="tema" /></span>
					</g:link></td>
			</tr>
		</g:if>

		<g:if test="${arhivaInstance?.etapa}">
			<tr>
				<th class="fieldcontain"><span id="etapa-label"
					class="property-label"><g:message code="fir.etapa.label"
							default="Etapa" /></span></th>

				<td class="fieldcontain"><span class="property-value"
					aria-labelledby="etapa-label"><g:fieldValue
							bean="${arhivaInstance}" field="etapa" /></span></td>
			</tr>
		</g:if>

		<g:if test="${arhivaInstance?.termenPornire}">
			<tr>
				<th class="fieldcontain"><span id="termenPornire-label"
					class="property-label"><g:message
							code="fir.termenPornire.label" default="Termen Pornire" /></span></th>

				<td class="fieldcontain"><span class="property-value"
					aria-labelledby="termenPornire-label"><g:fieldValue
							bean="${arhivaInstance}" field="termenPornire" /></span></td>
			</tr>
		</g:if>

		<g:if test="${arhivaInstance?.termenLimita}">
			<tr>
				<th class="fieldcontain"><span id="termenLimita-label"
					class="property-label"><g:message
							code="fir.termenLimita.label" default="Termen Limita" /></span></th>

				<td class="fieldcontain"><span class="property-value"
					aria-labelledby="termenLimita-label"><g:fieldValue
							bean="${arhivaInstance}" field="termenLimita" /></span></td>
			</tr>
		</g:if>

		<g:if test="${arhivaInstance?.comentariu}">
			<tr>
				<th class="fieldcontain"><span id="comentariu-label"
					class="property-label"><g:message
							code="fir.comentariu.label" default="Comentariu" /></span></th>

				<td class="fieldcontain"><span class="property-value"
					aria-labelledby="comentariu-label"><g:fieldValue
							bean="${arhivaInstance}" field="comentariu" /></span></td>
			</tr>
		</g:if>

	</table>

	<g:form>
		<fieldset class="buttons">
			<g:hiddenField name="id" value="${instance?.id}" />

			<g:actionSubmit class="edit" action="edit" id="${instance?.id}"
				value="${message(code: 'default.button.edit.label', default: 'Edit')}" />

			<g:actionSubmit class="delete" action="delete"
				value="${message(code: 'default.button.delete.label', default: 'Delete')}"
				onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
		</fieldset>
	</g:form>
</body>
</html>
