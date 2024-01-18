<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName" value="${message(code: 'actiune.label', default: 'Actiune')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>

<parameter name="selectedAcasa" value="select" />
<parameter name="selectedActiuni" value="current" />
<parameter name="selectedFirme" value="select" />
<parameter name="selectedProduse" value="select" />
<parameter name="selectedTeme" value="select" />
<parameter name="selectedAdmin" value="select" />

</head>
<body>

	<content tag="heading">
	<h1>Toate operatiile pentru aceasta actiune</h1>
	</content>

	<content tag="upperRightOptions"> <g:link action="arhiveaza" id="${actiuneInstance?.id}">
		<r:img class="optionIcon" uri="/images/icons/onebit_11.png" width="24px" height="24px" alt='Arhivare'
			onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Sunteti sigur ca vreti sa arhivati ?')}');" />
	</g:link> <a onClick="javascript:printi('twoColumns')" href="javascript:;"><r:img class="optionIcon" uri="/images/icons/onebit_39.png" width="24px"
			height="24px" alt='Printare' /></a> </content>

	<table id="twoColumns">
		<tr>

			<td id="leftColumn"><tmpl:/templatesMessages/error /> <tmpl:/templates/list instanceList="${instanceList}"
				instancePropertiesNames="${instancePropertiesNames}" instancePropertiesList="${instancePropertiesList}" />

				<div class="dont-print-that">

					<g:if test="${params.edit==null }">
						<h2>Adauga o etapa noua</h2>
					</g:if>

					<g:if test="${params.edit!=null }">
						<h2>Editeaza aceasta etapa</h2>
					</g:if>

					<g:form controller="actiune" enctype="multipart/form-data">
						<g:hiddenField name="id" value="${actiuneInstance?.id}" />
						<g:hiddenField name="edit" value="${params.edit}" />
						<g:hiddenField name="idActiune" value="${actiuneInstance?.idActiune }" />
								
								<tmpl:/templates/form instance="${newEtapaInstance}" fieldNameList="${fieldNameListForEtapa}" fieldList="${fieldListForEtapa}" />
								
								<g:if test="${params.edit==null }">
									<g:actionSubmit class="create" action="save" id="${newEtapaInstance?.id}"
									value="${message(code: 'default.button.create.label', default: 'Adauga')}" />
								</g:if>
								
								<g:if test="${params.edit!=null }">
									<g:actionSubmit class="create" action="save" id="${newEtapaInstance?.id}"
									value="${message(code: 'default.button.edit.label', default: 'Editeaza')}" />
								</g:if>
								
						<g:if test="${params.edit!=null }">
							<g:link action="completeShow" id="${actiuneInstance.id }">
								<input type="button" value="${message(code: 'default.button.cancel.label', default: 'Cancel')}" />
							</g:link>
						</g:if>
					</g:form>
				</div>

			</td>

			<td id="rightColumn">
				<table id="actiuneInfo" class="property-list firma">

					<g:if test="${actiuneInstance?.id}">
						<tr>
							<th><span id="idActiune-label" class="property-label"><g:message code="actiune.idActiune.label" default="Id" /></span></th>

							<td><span class="showHeading" aria-labelledby="idActiune-label"><g:fieldValue bean="${actiuneInstance}" field="idActiune" /></span></td>
						</tr>
					</g:if>


					<tr>
						<th><span id="subiect-label" class="property-label"><g:message code="actiune.subiect.label" default="Subiect" /></span></th>

						<td><g:if test="${actiuneInstance?.subiect}">
								<span class="showHeading" aria-labelledby="subiect-label"><g:fieldValue bean="${actiuneInstance}" field="subiect" /></span>
							</g:if></td>
					</tr>



					<tr>
						<th><span id="firma-label" class="property-label"><g:message code="actiune.firma.label" default="Firma" /></span></th>

						<td><g:if test="${actiuneInstance?.firma}">
								<g:link controller="firma" action="show" id="${actiuneInstance?.firma?.id}">
									<span class="property-value" aria-labelledby="firma-label"><g:fieldValue bean="${actiuneInstance}" field="firma" /></span>
								</g:link>
							</g:if></td>
					</tr>


					<tr>
						<th><span id="tema-label" class="property-label"><g:message code="actiune.tema.label" default="Tema" /></span></th>

						<td><g:if test="${actiuneInstance?.tema}">
								<g:link controller="tema" action="show" id="${actiuneInstance?.tema?.id}">
									<span class="property-value" aria-labelledby="tema-label"><g:fieldValue bean="${actiuneInstance}" field="tema" /></span>
								</g:link>
							</g:if></td>
					</tr>



					<tr>
						<th><span id="tema-label" class="property-label"><g:message code="actiune.tema.label" default="Termen" /></span></th>
						<td><g:if test="${actiuneInstance?.tema}">
								<span class="property-value" aria-labelledby="tema-label">
									${terLimita} zile
								</span>
							</g:if></td>
					</tr>


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
