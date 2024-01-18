<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName" value="${message(code: 'tema.label', default: 'Tema')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>

<parameter name="selectedAcasa" value="select" />
<parameter name="selectedActiuni" value="select" />
<parameter name="selectedFirme" value="select" />
<parameter name="selectedProduse" value="select" />
<parameter name="selectedTeme" value="current" />
<parameter name="selectedAdmin" value="select" />

<parameter name="selectedTemeList" value="sub_show" />

</head>
<body>

	<content tag="heading">
	<h1>Toate temele</h1>
	</content>

	<tmpl:/templates/list model="[instanceList: instanceList, fieldNameList: fieldNameList, fieldList: fieldList]"/>

</body>
</html>
