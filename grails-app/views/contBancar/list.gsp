<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'contBancar.label', default: 'ContBancar')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>

	<content tag="heading">
	<h1>Conturi Bancare</h1>
	</content>

	<tmpl:/templates/list model="[instanceList: instanceList,
	fieldNameList: fieldNameList, fieldList: fieldList]"/>

</body>
</html>
