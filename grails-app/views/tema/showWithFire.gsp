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
	<h1>
		<g:message code="default.show.label" args="[entityName]" />
	</h1>
	</content>

	<tmpl:/templatesMessages/error />

	<table id="showCompleteTable">
		<tr>

			<td id="leftColumn">

				<table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
					<thead>
						<tr>

							<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="idFir"
								title="${message(code: 'fir.idFir.label', default: 'Id')}" />

							<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="user"
								title="${message(code: 'fir.user.label', default: 'User')}" />

							<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="firma"
								title="${message(code: 'fir.firma.label', default: 'Firma')}" />

							<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="contact"
								title="${message(code: 'fir.contact.label', default: 'Contact')}" />

							<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="etapa"
								title="${message(code: 'fir.etapa.label', default: 'Etapa')}" />

							<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="termenPornire"
								title="${message(code: 'fir.termenPornire.label', default: 'Termen Pornire')}" />

							<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="termenLimita"
								title="${message(code: 'fir.termenLimita.label', default: 'Termen Limita')}" />

							<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="comentariu"
								title="${message(code: 'fir.comentariu.label', default: 'Comentariu')}" />

						</tr>
					</thead>
					<tbody>
						<g:each in="${firInstanceList}" status="i" var="firInstance">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

								<td><g:link controller="actiune" action="completeShow" id="${firInstance.id}">
										${fieldValue(bean: firInstance, field: "idFir")}
									</g:link></td>

								<td>
									${fieldValue(bean: firInstance, field: "user")}
								</td>

								<td><g:link controller="firma" action="show" id="${firInstance?.firma?.id}">
										${fieldValue(bean: firInstance, field: "firma")}
									</g:link></td>

								<td><g:link controller="contact" action="show" id="${firInstance?.contact?.id}">
										${fieldValue(bean: firInstance, field: "contact")}
									</g:link></td>

								<td>
									${fieldValue(bean: firInstance, field: "etapa")}
								</td>

								<td><g:formatDate date="${firInstance.termenPornire}" /></td>
								<td><g:formatDate date="${firInstance.termenLimita}" /></td>

								<td>
									${fieldValue(bean: firInstance, field: "comentariu")}
								</td>

							</tr>
						</g:each>
					</tbody>
				</table>

			</td>

			<td id="rightColumn"><tmpl:/templates/show /></td>

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

</body>
</html>
