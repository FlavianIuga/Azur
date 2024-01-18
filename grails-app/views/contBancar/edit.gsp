<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'contBancar.label', default: 'ContBancar')}" />
<title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>
<body>

	<content tag="heading">
	<h1>
		<g:message code="default.edit.label" args="[entityName]" />
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
