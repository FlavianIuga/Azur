<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName" value="${message(code: 'produs.label', default: 'Produs')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>

<parameter name="selectedAcasa" value="select" />
<parameter name="selectedActiuni" value="select" />
<parameter name="selectedFirme" value="select" />
<parameter name="selectedProduse" value="current" />
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

	<table id="twoColumn">
		<tr>
			<td id="leftColumn"><tmpl:/templates/show /></td>

			<td id="rightColumn">

				<table id="showProdusTable">
					<tr class="showProdusTableTh">
						<th>Termen expirare garantie GE IP</th>
						<td class="showProdusTableTd">
							${expirareGarantieFurnizor }
						</td>
					</tr>
					<tr class="showProdusTableTh">
						<th>Garantie ramasa GE IP</th>
						<td class="showProdusTableTd">
							${garantieRamasaFurnizor }
						</td>
					</tr>

					<tr>
						<th>&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
					<tr class="showProdusTableTh">
						<th>Termen expirare garantie client</th>
						<td class="showProdusTableTd">
							${expirareGarantieClient }
						</td>
					</tr>
					<tr class="showProdusTableTh">
						<th>Garantie ramasa client</th>
						<td class="showProdusTableTd">
							${garantieRamasaClient }
						</td>
					</tr>

					<tr>

						<td colspan="2">&nbsp;
							<h2>Evenimente</h2><tmpl:/templates/listb instanceList="${evenimentInstanceList}" fieldNameListForList="${fieldNameListEveniment}"
							fieldListForList="${fieldListEveniment}" />
							
							<div class="dont-print-that">
							
								<g:if test="${params.edit==null }">
									<h2>Adauga un eveniment nou</h2>
								</g:if>
								
								<g:if test="${params.edit!=null }">
									<h2>Editeaza acest eveniment</h2>
								</g:if>
								
							
								<g:form controller="eveniment">
									<g:hiddenField name="id" value="${instance?.id}" />
									<g:hiddenField name="edit" value="${params.edit}" />
									
									<tmpl:/templates/form instance="${evenimentInstance}" fieldNameList="${fieldNameListEveniment}" fieldList="${fieldListEveniment}"/>
									
									<g:actionSubmit action="save" id="${evenimentInstance?.id}" value="${message(code: 'default.button.create.label', default: 'Adauga')}" />
									
									<g:link action="show" id="${instance.id }"> 
										<input type="button" value="${message(code: 'default.button.cancel.label', default: 'Cancel')}" />
									</g:link>
								</g:form>	
							</div>
							
						</td>

					</tr>

				</table>

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
