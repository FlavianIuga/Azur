<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'actiune.label', default: 'Actiune')}" />
<title><g:message code="default.edit.label" args="[entityName]" /></title>
<parameter name="selectedAcasa" value="select" />
<parameter name="selectedActiuni" value="current" />
<parameter name="selectedFirme" value="select" />
<parameter name="selectedProduse" value="select" />
<parameter name="selectedTeme" value="select" />
<parameter name="selectedAdmin" value="select" />

</head>
<body>

	<content tag="heading">
	<h1>
		<g:message code="default.edit.label" args="[entityName]" />
	</h1>
	</content>

	<tmpl:/templatesMessages/error />

	<g:form method="post">
		<g:hiddenField name="id" value="${instance?.id}" />
		<g:hiddenField name="version" value="${instance?.version}" />
		<fieldset class="form">
			<g:applyLayout name="form">
			<tmpl:/templates/form />
		</g:applyLayout>
		</fieldset>
		<fieldset class="buttons">
			<g:actionSubmit class="save" action="update"
				value="${message(code: 'default.button.update.label', default: 'Update')}" />
			<g:actionSubmit class="delete" action="delete"
				value="${message(code: 'default.button.delete.label', default: 'Delete')}"
				formnovalidate=""
				onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
		</fieldset>
	</g:form>

</body>
</html>
