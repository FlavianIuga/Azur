<table border="0" cellpadding="0" cellspacing="0" id="id-form">

	<g:each in="${fieldNameList}" status="i" var="fieldName">

		<tr class="fieldcontain ${hasErrors(bean: instance, field: 'fieldName', 'error')} ">

			<th valign="top"><g:message code="field.fieldName.label" default="${fieldName}" /></th>
			<td><g:if
					test="${(fieldList[i].toString().contains("data") || fieldList[i].toString().contains("termen")) && !fieldList[i].toString().contains("termenLimita") }">

					<g:datePicker class="inp-form" name="${fieldList[i]}" precision="day" value="${instance."${fieldList[i]}"}" default="none" noSelection="['':'']" years="${2000..2030}" />

<!--
					<g:if test="${fieldList[i].toString().contains("dataComandaClient") || fieldList[i].toString().contains("dataFacturaClient")}">
						<g:datePicker class="inp-form" name="${fieldList[i]}" precision="day" value="${instance."${fieldList[i]}"}" default="none" noSelection="['':'']" years="${2000..2030}" />
					</g:if>

					<g:if test="${!fieldList[i].toString().contains("dataComandaClient") && !fieldList[i].toString().contains("dataFacturaClient") }">
						<g:datePicker class="inp-form" name="${fieldList[i]}" precision="day" value="${instance."${fieldList[i]}"}" noSelection="['':'']" />
					</g:if>
-->

				</g:if> <g:if test="${fieldList[i].toString().contains("termenLimita")}">
					<g:field type="number" class="inp-form" name="${fieldList[i]}" value="" />
					<span>&nbsp;&nbsp;&nbsp;zile</span>
				</g:if> <g:if test="${!(fieldList[i].toString().contains("data") || fieldList[i].toString().contains("termen"))}">

					<g:if test="${fieldList[i].equals('observatii') || fieldList[i].equals('descriere') }">
						<g:textArea type="text" class="observatiiTextField" name="${fieldList[i]}" value="${instance."${fieldList[i]}"}" rows="7" cols="37" />
					</g:if>

					<g:if test="${fieldList[i].toString().equals('tipClient') }">
						<g:select class="styledselect_form_1" id="tipClient" name="tipClient.id" from="${contact.TipClient.list()}" optionKey="id" required=""
							value="${instance?.tipClient?.id}" />
					</g:if>

					<g:if test="${fieldList[i].toString().equals('tipEveniment') }">
						<g:select class="styledselect_form_1" id="tipEveniment" name="tipEveniment.id" from="${produs.TipEveniment.list()}" optionKey="id" required=""
							value="${instance?.tipEveniment?.id}" />
					</g:if>

					<g:if test="${fieldList[i].toString().equals('firma') }">
						<g:select class="styledselect_form_1" id="firma" name="firma.id" noSelection="['null':'']" from="${contact.Firma.list()}" optionKey="id"
							required="" value="${instance?.firma?.id}" />
					</g:if>

					<g:if test="${fieldList[i].toString().equals('etapa') }">
						<g:select class="styledselect_form_1" id="etapa" name="etapa.id" from="${actiune.Etapa.findAllByEnabled(true)}" optionKey="id" required=""
							value="${instance?.etapa?.id}" />

						<!-- 
							onChange="${remoteFunction(
								controller: 'action',
								action: 'ajaxGetDefaultTermenLimita',
								params: '\'id=\' + escape(this.value)',
								onComplete: 'updateTermenLimita(e)')}" />
							-->
					</g:if>

					<g:if test="${fieldList[i].toString().equals('tema') }">
						<g:select class="styledselect_form_1" id="tema" name="tema.id" from="${actiune.Tema.list()}" optionKey="id" required=""
							value="${instance?.tema?.id}" />
					</g:if>

					<g:if test="${fieldList[i].toString().equals('contact') }">
						<g:select class="styledselect_form_1" id="contact" name="contact.id" from="${contact.Contact.findAllByEnabled(true)}" optionKey="id" required=""
							value="${instance?.contact?.id}" />

						<!-- onChange="${remoteFunction(
								controller: 'action',
								action: 'ajaxGetContacts',
								params: '\'id=\' + escape(this.value)',
								onComplete: 'updateContacts(e)')}"
							-->

					</g:if>

					<g:if test="${fieldList[i].toString().equals('enabled')}">
						<span> <g:checkBox name="${fieldList[i]}" value="${instance?."${fieldList[i]}"}" />
						</span>
					</g:if>

					<g:if test="${fieldList[i].toString().equals('admin')}">
						<span> <g:checkBox name="${fieldList[i]}" value="${adminFlag}" />
						</span>
					</g:if>

					<g:if test="${fieldList[i].toString().equals('documentPath') }">
						<span> <input type="file" name="${fieldList[i]}" id="myFile" />
						</span>
					</g:if>

					<g:if test="${fieldList[i].toString().equals('idActiune') }">
						<span> <g:hiddenField type="number" class="inp-form" name="${fieldList[i]}" value="${instance?."${fieldList[i]}"}" /> ${instance?."${fieldList[i]}"}
						</span>
					</g:if>

					<g:if test="${fieldList[i].toString().equals('password') }">
						<g:passwordField class="inp-form" name="${fieldList[i]}" value="" />
					</g:if>

					<g:if
						test="${!(fieldList[i].equals('observatii') || fieldList[i].equals('descriere')) && !fieldList[i].toString().equals('tipClient') && !fieldList[i].toString().equals('tipEveniment') && !fieldList[i].toString().equals('firma') && !fieldList[i].toString().equals('etapa') && !fieldList[i].toString().equals('tema') && !fieldList[i].toString().equals('contact') && !fieldList[i].toString().equals('idActiune') && !fieldList[i].toString().equals('documentPath') && !fieldList[i].toString().equals('password') && !fieldList[i].toString().equals('enabled') && !fieldList[i].toString().equals('admin')}">

						<g:textField class="inp-form" name="${fieldList[i]}" value="${instance?."${fieldList[i]}"}" />

					</g:if>

				</g:if></td>
			<td></td>

		</tr>

	</g:each>
</table>
