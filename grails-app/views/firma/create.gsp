<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'firma.label', default: 'Firma')}" />
<title><g:message code="default.create.label"
		args="[entityName]" /></title>

<parameter name="selectedAcasa" value="select" />
<parameter name="selectedActiuni" value="select" />
<parameter name="selectedFirme" value="current" />
<parameter name="selectedProduse" value="select" />
<parameter name="selectedTeme" value="select" />
<parameter name="selectedAdmin" value="select" />

<parameter name="selectedFirmeCreate" value="sub_show" />

</head>
<body>

	<content tag="heading">
	<h1>
		<g:message code="default.create.label" args="[entityName]" />
	</h1>
	</content>

	<tmpl:/templatesMessages/error />

	<g:form action="save">
		<fieldset class="form">
			<g:applyLayout name="form">
			<tmpl:/templates/form />
		</g:applyLayout>
		</fieldset>
		<fieldset class="buttons">
			<g:submitButton name="create" class="save"
				value="${message(code: 'default.button.create.label', default: 'Create')}" />
		</fieldset>
	</g:form>

</body>
</html>
