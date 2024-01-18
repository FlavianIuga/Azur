<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName" value="${message(code: 'actiune.label', default: 'Actiune')}" />
<title><g:message code="default.create.label" args="[entityName]" /></title>
<parameter name="selectedAcasa" value="select" />
<parameter name="selectedActiuni" value="current" />
<parameter name="selectedFirme" value="select" />
<parameter name="selectedProduse" value="select" />
<parameter name="selectedTeme" value="select" />
<parameter name="selectedAdmin" value="select" />

</head>
<body>

	<content tag="heading">
		<h1><g:message code="default.create.label" args="[entityName]" /></h1>
	</content>

	<tmpl:/templatesMessages/error />

	<g:hasErrors bean="${instance}">
		<ul class="errors" role="alert">
			<g:eachError bean="${instance}" var="error">
				<li><g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if> <g:message
						error="${error}" /></li>
			</g:eachError>
		</ul>
	</g:hasErrors>

	<table id="showCompleteTable">
		<tr>

			<td id="leftColumn"><tmpl:/templates/list model="[instanceList: instanceList, fieldListForList:
				fieldListForList, fieldNameListForList: fieldNameListForList]"/> <g:form action="save" enctype="multipart/form-data">

					<fieldset class="form">

						<g:applyLayout name="form">
							<tmpl:/templates/form model="[instance: instance, fieldList: fieldList, fieldNameList: fieldNameList]" />
						</g:applyLayout>
					</fieldset>

					<fieldset class="buttons">

						<g:hiddenField name="idActiune" value="${instance?.idActiune }" />

						<g:submitButton name="create" class="save"
							value="${message(code: 'default.button.adauga.label', default: 'Adauga')}" />

					</fieldset>
				</g:form>

			</td>

			<td id="rightColumn">

				<table id="showtable" class="property-list firma">

					<g:if test="${instance?.idActiune}">
						<tr>
							<th><span id="idActiune-label" class="property-label"><g:message code="actiune.idActiune.label"
										default="Id" /></span></th>

							<td><span class="showHeading" aria-labelledby="idActiune-label"><g:fieldValue bean="${instance}"
										field="idActiune" /></span></td>
						</tr>
					</g:if>

					<g:if test="${instance?.firma}">
						<tr>
							<th><span id="firma-label" class="property-label"><g:message code="actiune.firma.label"
										default="Firma" /></span></th>

							<td><g:link controller="firma" action="show" id="${instance?.firma?.id}">
									<span class="property-value" aria-labelledby="firma-label"><g:fieldValue bean="${instance}"
											field="firma" /></span>
								</g:link></td>
						</tr>
					</g:if>

					<g:if test="${instance?.tema}">
						<tr>
							<th><span id="tema-label" class="property-label"><g:message code="actiune.tema.label" default="Tema" /></span></th>

							<td><g:link controller="tema" action="show" id="${instance?.tema?.id}">
									<span class="property-value" aria-labelledby="tema-label"><g:fieldValue bean="${instance}" field="tema" /></span>
								</g:link></td>
						</tr>
					</g:if>

				</table>

			</td>
		</tr>
	</table>

</body>
</html>
