<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName" value="${message(code: 'actiune.label', default: 'Actiune')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>

<parameter name="selectedAcasa" value="select" />
<parameter name="selectedActiuni" value="select" />
<parameter name="selectedFirme" value="select" />
<parameter name="selectedProduse" value="select" />
<parameter name="selectedTeme" value="select" />
<parameter name="selectedAdmin" value="current" />

</head>
<body>

	<content tag="heading">
	<h1>Toate operatiile pentru aceasta actiune</h1>
	</content>
	
	<content tag="upperRightOptions">
		<g:link action="dezarhiveaza" id="${arhivaInstance?.id}"><r:img class="optionIcon" uri="/images/icons/dezarhiveaza.png" width="24px" height="24px" alt='Dezarhivare' 
			onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Sunteti sigur ca vreti sa dezarhivati ?')}');" /></g:link>
		<a onClick="javascript:printi('twoColumns')" href="javascript:;"><r:img class="optionIcon" uri="/images/icons/onebit_39.png" width="24px" height="24px" alt='Printare' /></a>
	</content>

	<table id="twoColumns">
		<tr>

			<td id="leftColumn"><tmpl:/templates/list instanceList="${instanceList}" instancePropertiesNames="${instancePropertiesNames}"
				instancePropertiesList="${instancePropertiesList}" />

			</td>

			<td id="rightColumn">
				<table id="actiuneInfo" class="property-list firma">

					<g:if test="${actiuneInstance?.id}">
						<tr>
							<th><span id="idActiune-label" class="property-label"><g:message code="fir.idActiune.label" default="Id" /></span></th>

							<td><span class="showHeading" aria-labelledby="idActiune-label"><g:fieldValue bean="${actiuneInstance}" field="idActiune" /></span></td>
						</tr>
					</g:if>

					<g:if test="${actiuneInstance?.firma}">
						<tr>
							<th><span id="firma-label" class="property-label"><g:message code="actiune.firma.label" default="Firma" /></span></th>

							<td><g:link controller="firma" action="show" id="${actiuneInstance?.firma?.id}">
									<span class="property-value" aria-labelledby="firma-label"><g:fieldValue bean="${actiuneInstance}" field="firma" /></span>
								</g:link></td>
						</tr>
					</g:if>

					<g:if test="${actiuneInstance?.tema}">
						<tr>
							<th><span id="tema-label" class="property-label"><g:message code="actiune.tema.label" default="Tema" /></span></th>

							<td><g:link controller="tema" action="show" id="${actiuneInstance?.tema?.id}">
									<span class="property-value" aria-labelledby="tema-label"><g:fieldValue bean="${actiuneInstance}" field="tema" /></span>
								</g:link></td>
						</tr>
					</g:if>

					<g:if test="${actiuneInstance?.tema}">
						<tr>
							<th><span id="tema-label" class="property-label"><g:message code="actiune.tema.label" default="Termen" /></span></th>
							<td><span class="property-value" aria-labelledby="tema-label">${terLimita} zile</span></td>
						</tr>
					</g:if>

				</table>
			</td>

		</tr>

	</table>
	<!-- 
	<g:form>
		<fieldset class="buttons">
			<g:hiddenField name="id" value="${actiuneInstance?.id}" />

			<g:actionSubmit class="edit" action="createNewEtapa" id="${actiuneInstance?.id}"
				value="${message(code: 'default.button.adauga.label', default: 'Adauga')}" />

		</fieldset>
	</g:form>
 -->

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
