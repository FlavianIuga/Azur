<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'firma.label', default: 'Firma')}" />
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
		<a onClick="javascript:printi('twoColumns')" href="javascript:;"><r:img class="optionIcon" uri="/images/icons/onebit_39.png" width="24px" height="24px" alt='Printare' /></a>
	</content>

	<tmpl:/templatesMessages/error />

	<table id="twoColumns">
		<tr>
			<td id="leftColumn"><tmpl:/templates/show /></td>

			<td id="rightColumn"><g:if
					test="${contactInstanceList.size() - inactiveContactsList.size() ==0}">
					<h2>Nu exista contacte active</h2>
				</g:if> <g:render template="/contact/contactSmall" var="contactInstance"
					collection="${contactInstanceList}"></g:render> <br /> <br /> <g:if
					test="${inactiveContactsList.size() > 0}">
					<h2>Contacte inactive:</h2>
					<g:each in="${inactiveContactsList}" status="i"
						var="contactInstance">
						<g:if test="${contactInstance?.nume}">

							<g:link controller="contact" action="show"
								id="${contactInstance?.id}">
								<span class="showHeading" aria-labelledby="nume-label"><g:fieldValue
										bean="${contactInstance}" field="nume" /></span>
							</g:link>

						</g:if>
						<br />
					</g:each>
				</g:if></td>

		</tr>

	</table>

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
