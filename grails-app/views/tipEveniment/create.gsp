<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'tipEveniment.label', default: 'TipEveniment')}" />
<title><g:message code="default.create.label"
		args="[entityName]" /></title>
		
<parameter name="selectedAcasa" value="select" />
<parameter name="selectedActiuni" value="select" />
<parameter name="selectedFirme" value="select" />
<parameter name="selectedProduse" value="select" />
<parameter name="selectedTeme" value="select" />
<parameter name="selectedAdmin" value="current" />

<parameter name="selectedAdminTipuriEvenimentCreate" value="sub_show" />
		
</head>
<body>

	<content tag="heading">
	<h1>
		<g:message code="default.create.label" args="[entityName]" />
	</h1>
	</content>

	<tmpl:/templatesMessages/error />

	<g:hasErrors bean="${instance}">
		<ul class="errors" role="alert">
			<g:eachError bean="${instance}" var="error">
				<li
					<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
						error="${error}" /></li>
			</g:eachError>
		</ul>
	</g:hasErrors>
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
