<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />

<parameter name="selectedAcasa" value="select" />
<parameter name="selectedActiuni" value="select" />
<parameter name="selectedFirme" value="current" />
<parameter name="selectedProduse" value="select" />
<parameter name="selectedTeme" value="select" />
<parameter name="selectedAdmin" value="select" />

<parameter name="selectedFirmeList" value="sub_show" />

</head>
<body>

	<content tag="heading">
	<h1>Toate firmele</h1>
	</content>

	<tmpl:/templates/list model="[instanceList: instanceList, fieldNameList: fieldNameList, fieldList: fieldList]"/>

</body>
</html>