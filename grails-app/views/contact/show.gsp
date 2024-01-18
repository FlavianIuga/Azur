<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'contact.label', default: 'Contact')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>

<parameter name="selectedAcasa" value="select" />
<parameter name="selectedActiuni" value="select" />
<parameter name="selectedFirme" value="current" />
<parameter name="selectedProduse" value="select" />
<parameter name="selectedTeme" value="select" />
<parameter name="selectedAdmin" value="select" />

</head>
<body>

	<content tag="heading">
	<h1>
		<g:message code="default.show.label" args="[entityName]" />
	</h1>
	</content>

	<content tag="upperRightOptions">
		<a onClick="javascript:printi('showTable')" href="javascript:;"><r:img class="optionIcon" uri="/images/icons/onebit_39.png" width="24px" height="24px" alt='Printare' /></a>
	</content>

	<tmpl:/templatesMessages/error />

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

	<script language="JavaScript">
		function ShowHide(divId) {
			if (document.getElementById(divId).style.display == 'none') {
				document.getElementById(divId).style.display = 'block';
			} else {
				document.getElementById(divId).style.display = 'none';
			}
		}

		function printi(divId){
			var content = document.getElementById(divId);
			var pri = document.getElementById("toPrint").contentWindow;
			pri.document.open();
			pri.document.write(content.innerHTML);
			pri.document.close();
			pri.focus();
			pri.print();
		}
		
	</script>

</body>
</html>
