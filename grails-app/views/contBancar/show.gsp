<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'contBancar.label', default: 'ContBancar')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>

	<content tag="heading">
	<h1>
		<g:message code="default.show.label" args="[entityName]" />
	</h1>
	</content>

	<g:if test="${flash.message}">
		<div class="message" role="status">
			${flash.message}
		</div>
	</g:if>

	<tmpl:/templates/show />

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
