<div id="pagi">

	<g:if test="${params.action!='completeShow' }">

		<table class="paginateButtons">
			<tr>
				<td><g:paginate total="${total?:10}" next="Inainte" prev="Inapoi" /></td>
				<td id="numberOfRows">Numar de randuri <a href="<g:createLink action="cautare" params="[numberOfRows: '5']" />">5</a> <a
					href="<g:createLink action="cautare" params="[numberOfRows: '10']" />">10</a> <a
					href="<g:createLink action="cautare" params="[numberOfRows: '20']" />">20</a> <a
					href="<g:createLink action="cautare" params="[numberOfRows: '50']" />">50</a>

				</td>
			</tr>

		</table>
	</g:if>

</div>