<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />

<parameter name="selectedAcasa" value="select" />
<parameter name="selectedActiuni" value="select" />
<parameter name="selectedFirme" value="select" />
<parameter name="selectedProduse" value="select" />
<parameter name="selectedTeme" value="select" />
<parameter name="selectedAdmin" value="current" />

<parameter name="selectedAdminSettings" value="sub_show" />

</head>
<body>

	<content tag="heading">
	<h1>Setari</h1>
	</content>

	<h2>Calea unde se salveaza fisierele</h2>
	
	${rootFilesPath.valoare}
	<p>&nbsp;</p>
	<div>
		<g:link action='edit'>
			<input type="button" value="${message(code: 'default.button.edit.label', default: 'Editeaza')}" />
		</g:link>
	</div>

</body>
</html>