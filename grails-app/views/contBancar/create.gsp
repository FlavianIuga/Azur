<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'contBancar.label', default: 'ContBancar')}" />
<title><g:message code="default.create.label"
		args="[entityName]" /></title>
</head>
<body>

	<content tag="heading">
	<h1>
		<g:message code="default.create.label" args="[entityName]" />
	</h1>
	</content>

	<g:if test="${flash.message}">
		<div class="message" role="status">
			${flash.message}
		</div>
	</g:if>
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
