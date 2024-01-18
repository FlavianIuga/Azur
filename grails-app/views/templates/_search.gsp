<g:applyLayout name="search">

	<g:form>
		<div class="right">

			<g:each in="${fieldListForList}" status="i" var="field">
				<h5>
					${fieldNameListForList[i] }
				</h5>

				<g:if test="${field.contains('dataCreare')}">
					<g:if test="${dataCreareInceput != null && dataCreareFinal != null}">
						<g:if test="!${params."${field}".equals("")}">

							<h5>De la:</h5>
							<g:datePicker name="dataCreareInceput" value="${dataCreareInceput}" precision="day" />

							<h5>Pana la:</h5>
							<g:datePicker name="dataCreareFinal" value="${dataCreareFinal}" precision="day" />

						</g:if>
					</g:if>
					<g:if test="${dataCreareInceput == null || dataCreareFinal == null}">
						<h5>De la:</h5>
						<g:datePicker name="dataCreareInceput" value="${dataCreareInceput}" precision="day" default="${new Date().parse("dd mm yyyy", "01 01 2000")}" noSelection="['':'']" />

						<h5>Pana la:</h5>
						<g:datePicker name="dataCreareFinal" value="${dataCreareFinal}" precision="day" noSelection="['':'']" />
					</g:if>
				</g:if>

				<g:if test="${field.contains('dataComandaClient')}">
					<g:if test="${dataComandaClientInceput != null && dataComandaClientFinal != null}">
						<g:if test="!${params."${field}".equals("")}">

							<h5>De la:</h5>
							<g:datePicker name="dataComandaClientInceput" value="${dataComandaClientInceput}" precision="day" />

							<h5>Pana la:</h5>
							<g:datePicker name="dataComandaClientFinal" value="${dataComandaClientFinal}" precision="day" />

						</g:if>
					</g:if>
					<g:if test="${dataComandaClientInceput == null || dataComandaClientFinal == null}">
						<h5>De la:</h5>
						<g:datePicker name="dataComandaClientInceput" value="${dataComandaClientInceput}" precision="day" default="${new Date().parse("dd mm yyyy", "01 01 2000")}" noSelection="['':'']" />

						<h5>Pana la:</h5>
						<g:datePicker name="dataComandaClientFinal" value="${dataComandaClientFinal}" precision="day" noSelection="['':'']" />
					</g:if>
				</g:if>

				<g:if test="${field.contains('dataProductie')}">
					<g:if test="${dataProductieInceput != null && dataProductieFinal != null}">
						<g:if test="!${params."${field}".equals("")}">

							<h5>De la:</h5>
							<g:datePicker name="dataProductieInceput" value="${dataProductieInceput}" precision="day" />

							<h5>Pana la:</h5>
							<g:datePicker name="dataProductieFinal" value="${dataProductieFinal}" precision="day" />

						</g:if>
					</g:if>
					<g:if test="${dataProductieInceput == null || dataProductieFinal == null}">
						<h5>De la:</h5>
						<g:datePicker name="dataProductieInceput" value="${dataProductieInceput}" precision="day" default="${new Date().parse("dd mm yyyy", "01 01 2000")}" noSelection="['':'']" />

						<h5>Pana la:</h5>
						<g:datePicker name="dataProductieFinal" value="${dataProductieFinal}" precision="day" noSelection="['':'']" />
					</g:if>
				</g:if>

				<g:if test="${field.contains('termenPornire')}">
					<g:if test="${termenPornireInceput != null && termenPornireFinal != null}">
						<g:if test="!${params."${field}".equals("")}">

							<h5>De la:</h5>
							<g:datePicker name="termenPornireInceput" value="${termenPornireInceput}" precision="day" />

							<h5>Pana la:</h5>
							<g:datePicker name="termenPornireFinal" value="${termenPornireFinal}" precision="day" />

						</g:if>
					</g:if>
					<g:if test="${termenPornireInceput == null || termenPornireFinal == null}">
						<h5>De la:</h5>
						<g:datePicker name="termenPornireInceput" value="${termenPornireInceput}" precision="day" default="${new Date().parse("dd mm yyyy", "01 01 2000")}" noSelection="['':'']" />

						<h5>Pana la:</h5>
						<g:datePicker name="termenPornireFinal" value="${termenPornireFinal}" precision="day" noSelection="['':'']" />
					</g:if>
				</g:if>

				<g:if
					test="${field != 'enabled' && !field.contains('dataCreare')&& !field.contains('dataComandaClient') && !field.contains('dataProductie') && !field.contains('termenPornire')}">

					<g:if test="${params."${field}" != null}">
						<g:if test="!${params."${field}".equals("")}">

							<g:textField size="25" class="top-search-inp" name="${field}" value="${params."${field}"}" />

						</g:if>
					</g:if>

					<g:if test="${params."${field}" == null}">

						<g:textField size="25" class="top-search-inp" name="${field}" value="" />

					</g:if>
				</g:if>

				<g:if
					test="${field == 'enabled' && !field.contains('dataCreare')&& !field.contains('dataComandaClient') && !field.contains('dataProductie') && !field.contains('termenPornire')}">

					Activ: <g:checkBox name="activCheckBox" value="${activ}" /> &nbsp;&nbsp;Inactiv: <g:checkBox name="inactivCheckBox" value="${inactiv}" />

				</g:if>

			</g:each>

			<g:if test="${params.controller=='actiune' || params.controller=='arhiva'}">
				<h5>&nbsp;</h5>
				<h5>
					Toate operatiile &nbsp;&nbsp;&nbsp;
					<g:checkBox name="toateOperatiile" value="${toateOperatiile}" checked="${toateOperatiile }" />
				</h5>

			</g:if>

		</div>

		<g:actionSubmit class="searchButton" value="Cauta" action="cautare" />

		<h4>
			<g:link action="cautare">Resetare</g:link>
		</h4>

	</g:form>

</g:applyLayout>