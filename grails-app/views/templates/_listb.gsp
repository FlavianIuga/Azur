<table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
	<thead>
		<tr>

			<g:each in="${fieldNameListForList}" status="i" var="instanceFieldName">
			
				<g:if test="${fieldListForList[i] =='firma'}">
					<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="${fieldListForList[i] }.nume" title="${instanceFieldName}" />
				</g:if>
				
				<g:if test="${fieldListForList[i] =='contact'}">
					<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="${fieldListForList[i] }.nume" title="${instanceFieldName}" />
				</g:if>
				
				<g:if test="${fieldListForList[i] =='tema'}">
					<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="${fieldListForList[i] }.nume" title="${instanceFieldName}" />
				</g:if>
				
				<g:if test="${fieldListForList[i] =='etapa'}">
					<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="${fieldListForList[i] }.nume" title="${instanceFieldName}" />
				</g:if>
				
				<g:if test="${fieldListForList[i] =='user'}">
					<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="${fieldListForList[i] }.nume" title="${instanceFieldName}" />
				</g:if>
				
				<g:if test="${fieldListForList[i] =='tipClient'}">
					<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="${fieldListForList[i] }.tip" title="${instanceFieldName}" />
				</g:if>
				
				<g:if test="${fieldListForList[i] =='tipEveniment'}">
					<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="${fieldListForList[i] }.tipEveniment" title="${instanceFieldName}" />
				</g:if>
				
				<g:if test="${fieldListForList[i] !='firma' && fieldListForList[i] !='contact' && fieldListForList[i] !='tema' && fieldListForList[i] !='etapa' && fieldListForList[i] !='user' && fieldListForList[i] !='tipClient' && fieldListForList[i] !='tipEveniment'}">
					<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="${fieldListForList[i] }"
						title="${instanceFieldName}" />
				</g:if>
				
			</g:each>
			
			<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="bifa"
						title="Operatii" />

		</tr>
	</thead>
	<tbody>
		<g:each in="${instanceList}" status="i" var="instance">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

				<g:each in="${fieldListForList}" status="j" var="instanceField">
					<td><g:if test="${instanceField == 'nume'}">
							<g:link action="show" id="${instance?.id }">
								${fieldValue(bean: instance, field: instanceField)}
							</g:link>

						</g:if> <g:if test="${instanceField == 'firma'}">
							<g:link controller="firma" action="show" id="${instance?.firma?.id }">
								${fieldValue(bean: instance, field: instanceField)}
							</g:link>

						</g:if> <g:if test="${instanceField == 'contact'}">
							<g:link controller="contact" action="show" id="${instance?.contact?.id }">
								${fieldValue(bean: instance, field: instanceField)}
							</g:link>

						</g:if> <g:if test="${instanceField == 'tema'}">
							<g:link controller="tema" action="show" id="${instance?.tema?.id }">
								${fieldValue(bean: instance, field: instanceField)}
							</g:link>

						</g:if> <g:if test="${instanceField == 'produs'}">
							<g:link controller="produs" action="show" id="${instance?.produs?.id }">
								${fieldValue(bean: instance, field: instanceField)}
							</g:link>

						</g:if> <g:if test="${instanceField == 'idActiune'}">
							<g:link controller="actiune" action="completeShow" id="${instance?.id }">
								${fieldValue(bean: instance, field: instanceField)}
							</g:link>

						</g:if> 
						
						<g:if test="${instanceField == 'tipEveniment'}">
							${fieldValue(bean: instance, field: "tipEveniment.tipEv")}
						</g:if>
						
						<g:if test="${instanceField == 'garantieFurnizorRamasa'}">
								${garantieFurnizorRamasa[i]}
						</g:if> 
						
						<g:if test="${instanceField == 'garantieClientRamasa'}">
								${garantieClientRamasa[i]}
						</g:if> 
						
						<g:if test="${instanceField == 'documentPath'}">
							<g:if test="${instance?.documentPath != "" }">
								<a href="file:///${fieldValue(bean: instance, field: instanceField)}">Document</a>
							</g:if>

						</g:if> <g:if test="${instanceField == 'dataProductie'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.dataProductie}" />
						</g:if> <g:if test="${instanceField == 'dataComandaFurnizor'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.dataComandaFurnizor}" />
						</g:if> <g:if test="${instanceField == 'dataFacturaFurnizor'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.dataFacturaFurnizor}" />
						</g:if> <g:if test="${instanceField == 'dataComandaClient'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.dataComandaClient}" />
						</g:if> <g:if test="${instanceField == 'dataFacturaClient'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.dataFacturaClient}" />
						</g:if> <g:if test="${instanceField == 'termenPornire'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.termenPornire}" />
						</g:if> <g:if test="${instanceField == 'termenLimita'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.termenLimita}" />
						</g:if> <g:if test="${instanceField == 'dataCreare'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.dataCreare}" />
						</g:if> <g:if test="${instanceField == 'dataPornire'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.dataPornire}" />
						</g:if> <g:if test="${instanceField == 'dataEveniment'}">
							<g:formatDate format="MMMMMMMM dd, yyyy" date="${instance?.dataEveniment}" />
						</g:if> <g:if test="${instanceField == 'SN'}">
							<g:link controller="produs" action="show" id="${instance?.id }">
								${fieldValue(bean: instance, field: instanceField)}
							</g:link>

						</g:if> <g:if
							test="${instanceField != 'firma' && instanceField != 'contact' && instanceField != 'tema' && instanceField != 'produs' && instanceField != 'idActiune' && instanceField!='documentPath' && instanceField!='SN' && instanceField!='nume' && instanceField!='dataProductie' && instanceField!='dataProductie' && instanceField!='dataComandaFurnizor' && instanceField!='dataFacturaFurnizor' && instanceField!='dataComandaClient' && instanceField!='dataFacturaClient' && instanceField!='termenPornire' && instanceField!='termenLimita' && instanceField!='dataCreare' && instanceField!='dataPornire' && instanceField!='dataEveniment' && instanceField!='garantieFurnizorRamasa' && instanceField!='garantieClientRamasa' && instanceField!='tipEveniment'}">
							${fieldValue(bean: instance, field: instanceField)}
						</g:if></td>
					
				</g:each>

				<td>
					<g:link class="icon-1 info-tooltip" title="Editare" controller="produs" action="show" id="${produs.Produs.get(Long.parseLong("${instance?.produs?.id}"))?.id}" params="[edit: "${instance?.id}"]" ></g:link>
					<g:link class="icon-remove" title="Stergere" controller="produs" action="show" id="${produs.Produs.get(Long.parseLong("${instance?.produs?.id}"))?.id}" params="[removee: "${instance?.id}"]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"></g:link>
				</td>
			</tr>
			
		</g:each>
		
	</tbody>
</table>