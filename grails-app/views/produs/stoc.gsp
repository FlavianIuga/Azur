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

<parameter name="selectedProduseStoc" value="sub_show" />

</head>
<body>

	<content tag="heading">
	<h1>Raport stoc</h1>
	</content>

	<table id="twoColumns">
		<tr>

			<td id="leftColumn">

				<table border="0" width="100%" cellpadding="0" cellspacing="0" id="product-table">
					<thead>
						<tr>
							<g:if test="${codProdusList.size()>0}">
								<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="codProdus" title="Cod Produs" />	
							</g:if>

							<g:if test="${codProdusList.size()==0}">
								<th class="table-header-repeat line-left minwidth-1"><a>Cod Produs</a></td>
							</g:if>
							
							<g:if test="${stocListVandute.size()>0}">
								<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="stocListVandute" title="Vanzare Total" />
							</g:if>
							
							<g:if test="${stocListVandute.size()==0}">
								<th class="table-header-repeat line-left minwidth-1"><a>Vanzare Total</a></td>
							</g:if>
							
							<g:if test="${stocListSum.size()>0}">
								<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="stocListSum" title="In Stoc" />
							</g:if>
							
							<g:if test="${stocListSum.size()==0}">
								<th class="table-header-repeat line-left minwidth-1"><a>In Stoc</a></td>
							</g:if>
							
							<g:if test="${stocListVandute.size()>0}">
								<g:sortableColumn class="table-header-repeat line-left minwidth-1" property="stocListVandute" title="Vandute Perioada" />
							</g:if>
							
							<g:if test="${stocListVandute.size()==0}">
								<th class="table-header-repeat line-left minwidth-1"><a>Vandute Perioada</a></td>
							</g:if>

						</tr>
					</thead>
					<tbody>
	
						<g:each in="${codProdusList}" status="i" var="codProdus">
							<tr>
								<td>
									${codProdus.codProdus}
								</td>
								<td>
									${stocListVandute[i]}
								</td>
								<td>
									${stocListSum[i]}
								</td>
								<td>
									${stocListPerioadaTimp[i]}
								</td>
							</tr>
						</g:each>
						
					</tbody>

				</table>

			</td>

			<td id="rightColumn">
			
			<div id="stocInfo">
				<h2>Perioada de vanzare</h2>
				
				<g:form>
					
					<h4 class="stocTermen">De la:</h4>
					<g:datePicker name="stocTermenInceput" precision="day" value="${stocTermenInceput}" default="none" noSelection="['':'']" />

					
					<h4 class="stocTermen">Pana la:</h4>
					<g:datePicker name="stocTermenFinal" precision="day" value="${stocTermenFinal}" noSelection="['':'']"/>
					<h4>&nbsp;</h4>
					<g:actionSubmit action="stoc" value="Cauta" />
					
				</g:form>
				
			</div>
			</td>
		</tr>
	</table>

</body>
</html>
