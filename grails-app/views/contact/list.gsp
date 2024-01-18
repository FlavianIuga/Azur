<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName" value="${message(code: 'contact.label', default: 'Contact')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>

<parameter name="selectedAcasa" value="select" />
<parameter name="selectedActiuni" value="select" />
<parameter name="selectedFirme" value="current" />
<parameter name="selectedProduse" value="select" />
<parameter name="selectedTeme" value="select" />
<parameter name="selectedAdmin" value="select" />

<parameter name="selectedContacteList" value="sub_show" />

</head>
<body>

	<content tag="heading">
	<h1>Contacte</h1>
	</content>

	<tmpl:/templates/list model="[instanceList: instanceList, fieldNameList: fieldNameList, fieldList: fieldList]"/>

</body>
</html>
