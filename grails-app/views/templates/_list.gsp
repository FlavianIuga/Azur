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

				<g:if test="${fieldListForList[i] =='garantieClientRamasa'}">
					<th class="table-header-repeat line-left minwidth-1"><a>Gr. Client</a></th>
				</g:if>

				<g:if test="${fieldListForList[i] =='garantieFurnizorRamasa'}">
					<th class="table-header-repeat line-left minwidth-1"><a>Gr. Furnizor</a></th>
				</g:if>

				<g:if
					test="${fieldListForList[i] !='firma' && fieldListForList[i] !='contact' && fieldListForList[i] !='tema' && fieldListForList[i] !='etapa' && fieldListForList[i] !='user' && fieldListForList[i] !='tipEveniment' && fieldListForList[i] !='tipClient' && fieldListForList[i] !='garantieClientRamasa' && fieldListForList[i] !='garantieFurnizorRamasa'}">
					<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="${fieldListForList[i] }" title="${instanceFieldName}" />
				</g:if>

			</g:each>

<!-- 
			<g:if test="${instanceList?.size()!=0}">
				<g:if test="${instanceList?.get(0)?.getClass().toString().equals("class actiune.Actiune") && !fieldListForList[6].toString().equals("tema") }">
					<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="bifa" title="Operatii" />
				</g:if>
			</g:if>
 -->
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

						</g:if>

						 <g:if test="${instanceField == 'denumire'}">
							<g:link controller="produs" action="show" id="${instance?.id }">
								${fieldValue(bean: instance, field: instanceField)}
							</g:link>

						</g:if>
						
						
						
						 <g:if test="${instanceField == 'tema'}">
							<g:link controller="tema" action="show" id="${instance?.tema?.id }">
								${fieldValue(bean: instance, field: instanceField)}
							</g:link>

						</g:if> <g:if test="${instanceField == 'produs'}">
							<g:link controller="produs" action="show" id="${instance?.produs?.id }">
								${fieldValue(bean: instance, field: instanceField)}
							</g:link>

						</g:if> <g:if test="${instanceField == 'idActiune' || instanceField == 'subiect'}">

							<g:if test="${params.controller=='actiune' || params.controller=='tema'}">
								<g:link controller="actiune" action="completeShow" id="${instance?.id }">
									${fieldValue(bean: instance, field: instanceField)}
								</g:link>
							</g:if>

							<g:if test="${params.controller=='arhiva'}">
								<g:link controller="arhiva" action="completeShow" id="${instance?.id }">
									${fieldValue(bean: instance, field: instanceField)}
								</g:link>
							</g:if>

						</g:if> <g:if test="${instanceField == 'tipEv'}">
							<g:link controller="tipEveniment" action="show" id="${instance?.id }">
								${fieldValue(bean: instance, field: instanceField)}
							</g:link>
						</g:if> 
						
						 <g:if test="${instanceField == 'tip'}">
							<g:link controller="tipClient" action="show" id="${instance?.id }">
								${fieldValue(bean: instance, field: instanceField)}
							</g:link>
						</g:if> 
						
						<g:if test="${instanceField == 'garantieFurnizorRamasa'}">
							${garantieFurnizorRamasa[i]}
						</g:if> <g:if test="${instanceField == 'garantieClientRamasa'}">
							${garantieClientRamasa[i]}


						</g:if> <g:if test="${instanceField == 'enabled'}">
							<g:if test="${fieldValue(bean: instance, field: instanceField) == 'true'}">
								Da
							</g:if>
							
							<g:if test="${fieldValue(bean: instance, field: instanceField) == 'false'}">
								Nu
							</g:if>
							
						</g:if>
						
						 <g:if test="${instanceField == 'documentPath'}">
							<g:if test="${instance?.documentPath != "" }">
								<a href="file:///${fieldValue(bean: instance, field: instanceField)}">Document</a>
							</g:if>
						
						</g:if> <g:if test="${instanceField == 'dataProductie'}">
							<g:formatDate format="dd.MM.yyyy" date="${instance?.dataProductie}" />
						</g:if> <g:if test="${instanceField == 'dataComandaFurnizor'}">
							<g:formatDate format="dd.MM.yyyy" date="${instance?.dataComandaFurnizor}" />
						</g:if> <g:if test="${instanceField == 'dataFacturaFurnizor'}">
							<g:formatDate format="dd.MM.yyyy" date="${instance?.dataFacturaFurnizor}" />
						</g:if> <g:if test="${instanceField == 'dataComandaClient'}">
							<g:formatDate format="dd.MM.yyyy" date="${instance?.dataComandaClient}" />
						</g:if> <g:if test="${instanceField == 'dataFacturaClient'}">
							<g:formatDate format="dd.MM.yyyy" date="${instance?.dataFacturaClient}" />
						</g:if> <g:if test="${instanceField == 'termenPornire'}">
							<g:formatDate format="dd.MM.yyyy" date="${instance?.termenPornire}" />
						</g:if> <g:if test="${instanceField == 'termenLimita' && fieldListForList[0]!='idActiune'}">
							<g:formatDate format="dd.MM.yyyy" date="${instance?.termenLimita}" />
						</g:if> <g:if test="${instanceField == 'dataCreare'}">
							<g:formatDate format="dd.MM.yyyy" date="${instance?.dataCreare}" />
						</g:if> <g:if test="${instanceField == 'dataPornire'}">
							<g:formatDate format="dd.MM.yyyy" date="${instance?.dataPornire}" />
						</g:if> <g:if test="${instanceField == 'dataEveniment'}">
							<g:formatDate format="dd.MM.yyyy" date="${instance?.dataEveniment}" />
						</g:if> <g:if test="${instanceField == 'dataCertificatGarantie'}">
							<g:formatDate format="dd.MM.yyyy" date="${instance?.dataCertificatGarantie}" />
						</g:if>
						 <g:if test="${instanceField == 'SN'}">
							<g:link controller="produs" action="show" id="${instance?.id }">
								${fieldValue(bean: instance, field: instanceField)}
							</g:link>

						</g:if> <g:if test="${instanceField == 'termenLimita' && fieldListForList[0] == 'idActiune' && termenLimitaList!=null}">

							<g:if test="${termenLimitaList[i] != null}">
								${termenLimitaList[i]}&nbsp;zile
							</g:if>

						</g:if> 
						
							<g:if test="${instanceField == 'admin'}">
								
								${adminList[i]}
								
							</g:if>
						
						<g:if
							test="${instanceField != 'firma' && instanceField != 'contact' && instanceField != 'tema' && instanceField != 'produs' && instanceField != 'idActiune' && instanceField != 'subiect' && instanceField!='documentPath' && instanceField!='SN' && instanceField!='nume' && instanceField!='dataProductie' && instanceField!='dataProductie' && instanceField!='dataComandaFurnizor' && instanceField!='dataFacturaFurnizor' && instanceField!='dataComandaClient' && instanceField!='dataFacturaClient' &&instanceField!='dataCertificatGarantie' && instanceField!='termenPornire' && instanceField!='termenLimita' && instanceField!='dataCreare' && instanceField!='dataPornire' && instanceField!='dataEveniment' && instanceField!='garantieFurnizorRamasa' && instanceField!='garantieClientRamasa' && instanceField!='tip' && instanceField!='tipEv' && instanceField!='admin' && instanceField!='enabled' && instanceField!='denumire'}">
							${fieldValue(bean: instance, field: instanceField)}
						</g:if></td>

				</g:each>

<!-- 
				<g:if test="${instanceList?.get(0)?.getClass().toString().equals("class actiune.Actiune") && !fieldListForList[6].toString().equals("tema") }">
					<td><g:link class="icon-1 info-tooltip" title="Editare" controller="actiune" action="completeShow"
							id="${actiune.Actiune.get(Long.parseLong("${instance?.id}"))?.id}" params="[edit: "${instance?.id}"]" ></g:link></td>
				</g:if>
 -->

			</tr>
		</g:each>

	</tbody>

</table>

<tmpl:/templates/paginate />
