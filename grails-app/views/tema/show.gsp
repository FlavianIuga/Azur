<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName" value="${message(code: 'tema.label', default: 'Tema')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>

<parameter name="selectedAcasa" value="select" />
<parameter name="selectedActiuni" value="select" />
<parameter name="selectedFirme" value="select" />
<parameter name="selectedProduse" value="select" />
<parameter name="selectedTeme" value="current" />
<parameter name="selectedAdmin" value="select" />

</head>
<body>

	<content tag="heading">
	<h1>Toate actiunile din aceasta tema</h1>
	</content>

	<content tag="upperRightOptions"> <a onClick="javascript:printi('twoColumns')" href="javascript:;"><r:img class="optionIcon"
			uri="/images/icons/onebit_39.png" width="24px" height="24px" alt='Printare' /></a> </content>

	<tmpl:/templatesMessages/error />

	<table id="twoColumns">
		<tr>

			<td id="leftColumn"><tmpl:/templates/list instanceList="${instanceList}" fieldListForList="${fieldListForList}"
				fieldNameListForList="${fieldNameListForList}"/>
<!-- 				
				<g:each in="${instanceList}" status="i" var="field">
				a${i }: ${field }
				
					count: ${field.properties.count }
				
					<g:each in="${field.properties}" status="j" var="ff">
						<p/>
						${ff }
					</g:each>				
				<p />
				</g:each>
				
				<p />
				<g:each in="${fieldListForList}" status="i" var="field">
				${i }: ${field }
				
				</g:each>
				 
 -->
			</td>

			<td id="rightColumn"><tmpl:/templates/show instance="${instance}" fieldList="${fieldList}" fieldNameList="${fieldNameList}" /></td>
		</tr>
	</table>

	<g:form>
		<fieldset class="buttons">
			<g:hiddenField name="id" value="${instance?.id}" />

			<g:actionSubmit class="edit" action="edit" id="${instance?.id}" value="${message(code: 'default.button.edit.label', default: 'Edit')}" />

			<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"
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
