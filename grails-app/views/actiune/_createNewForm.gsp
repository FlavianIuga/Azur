<g:applyLayout name="form">

	<table border="0" cellpadding="0" cellspacing="0" id="id-form">

		<tr class="fieldcontain ${hasErrors(bean: firInstance, field: 'idActiune', 'error')} ">
			<th valign="top"><g:message code="actiune.idActiune.label" default="Id" /></th>
			<td><g:textField class="inp-form" name="idActiune" value="${firInstance?.idActiune}" /></td>
			<td></td>
		</tr>

		<tr class="fieldcontain ${hasErrors(bean: firInstance, field: 'firma', 'error')} required">
			<th valign="top"><g:message code="actiune.firma.label" default="Firma" /></th>
			<td><g:select class="styledselect_form_1" id="firma" name="firma.id" from="${gea.Firma.list()}" optionKey="id"
					required="" value="${firInstance?.firma?.id}" /></td>
			<td></td>
		</tr>

		<tr class="fieldcontain ${hasErrors(bean: firInstance, field: 'tema', 'error')} required">
			<th valign="top"><g:message code="actiune.tema.label" default="Tema" /></th>
			<td><g:select class="styledselect_form_1" id="tema" name="tema.id" from="${gea.Tema.list()}" optionKey="id"
					required="" value="${firInstance?.tema?.id}" /></td>
			<td></td>
		</tr>

		<tr class="fieldcontain ${hasErrors(bean: firInstance, field: 'contact', 'error')} required">
			<th valign="top"><g:message code="actiune.contact.label" default="Contact" /> <span class="required-indicator">*</span></th>
			<td><g:select class="styledselect_form_1" id="contact" name="contact.id" from="${gea.Contact.list()}"
					optionKey="id" required="" value="${firInstance?.contact?.id}" /></td>
			<td></td>
		</tr>

		<tr class="fieldcontain ${hasErrors(bean: firInstance, field: 'etapa', 'error')} required">
			<th valign="top"><g:message code="actiune.etapa.label" default="Etapa" /> <span class="required-indicator">*</span></th>
			<td><g:select class="styledselect_form_1" id="etapa" name="etapa.id" from="${gea.Etapa.list()}" optionKey="id"
					required="" value="${firInstance?.etapa?.id}" /></td>
			<td></td>
		</tr>

		<tr class="fieldcontain ${hasErrors(bean: firInstance, field: 'termenLimita', 'error')} ">
			<th valign="top"><g:message code="actiune.termenLimita.label" default="Termen Limita" /></th>
			<td><g:datePicker class="inp-form" name="termenLimita" precision="day" value="${firInstance?.termenLimita}" />
			</td>
			<td></td>
		</tr>

		<tr class="fieldcontain ${hasErrors(bean: firInstance, field: 'document', 'error')} ">
			<th valign="top"><g:message code="actiune.document.label" default="Document" /></th>
			<td>
					<input type="file" name="myFile" />
			</td>
			<td></td>
		</tr>

		<tr class="fieldcontain ${hasErrors(bean: firInstance, field: 'comentariu', 'error')} ">

			<th valign="top"><g:message code="fir.comentariu.label" default="Comentariu" /></th>
			<td><g:textField class="inp-form" name="comentariu" value="${firInstance?.comentariu}" /></td>
			<td></td>
		</tr>

	</table>

</g:applyLayout>