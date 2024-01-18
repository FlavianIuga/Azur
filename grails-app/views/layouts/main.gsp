<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title><g:layoutTitle default="Azur" /></title>

<r:require module="application" />

<g:layoutHead />
<r:layoutResources />
</head>
<body>

	<!-- Start: page-top-outer -->
	<div id="page-top-outer">

		<!-- Start: page-top -->
		<div id="page-top">

			<!-- start logo -->
			<div id="logo">
				
				<r:img uri="/images/logo/logo_banner.jpg" width="538px" height="70px" alt='Logo' />
				
			</div>
			<!-- end logo -->


			<div id="top-login">
				<sec:ifNotLoggedIn>

					<form action='${postUrl }' method='POST' id='loginForm'
						class='cssform' autocomplete='off'>
						<table id="logInTable">
							<tr>

								<td><label for='username'><g:message code="Unitizator" default="Utilizator" /></label></td>

								<td><label for='password'><g:message code="Parola" default="Parola" /></label></td>

								<td></td>

							</tr>

							<tr>
								<td><input type='text' class='text_' name='j_username'
									id='username' /></td>

								<td><input type='password' class='text_' name='j_password'
									id='password' /></td>

								<td><g:actionSubmit controller='login' action='auth'
										id="submit"
										value='Logare' /></td>

							</tr>

							<tr>
								<td><input type='checkbox' class='chk'
									name='${rememberMeParameter}' id='remember_me'
									<g:if test='${hasCookie}'>checked='checked'</g:if> /> <label
									for='remember_me'><g:message
											code="Memoreaza" /></label></td>
								<td></td>
								<td></td>
							</tr>

						</table>

					</form>

				</sec:ifNotLoggedIn>
			</div>

			<!--  start top-search -->
			<!--			<div id="top-search">

				<sec:ifAllGranted roles="ROLE_USER">

					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td><g:form url='[controller: "fir", action: "listCompleteSearch"]' id="searchableForm" name="searchableForm" method="get">

									<g:textField name="searchTerm" value="${params.searchTerm}" size="25" class="top-search-inp" />



									<select class="styledselect">
										<option value="">Actiuni</option>
										<option value="">Firme</option>
										<option value="">Contacte</option>
										<option value="">Produse</option>
										<option value="">Teme</option>
									</select>

									<g:actionSubmitImage value="Search" controlller="smartSearch" action="listCompleteSearch"
										src="${resource(dir: 'images', file: 'shared/top_search_btn.gif')}" />


								</g:form> <!--<input type="text" value="Search" onblur="if (this.value=='') { this.value='Search'; }"
							onfocus="if (this.value=='Search') { this.value=''; }" class="top-search-inp" /></td>
						<td><select class="styledselect">
								<option value="">Actiuni</option>
								<option value="">Firme</option>
								<option value="">Contacte</option>
								<option value="">Produse</option>
								<option value="">Teme</option>
						</select>
						
						-->
			</td>

			<td></td>
			</tr>
			</table>

			</sec:ifAllGranted>
		</div>
		<!--  end top-search -->

		-->

		<div class="clear"></div>
	</div>
	<!-- End: page-top -->

	</div>
	<!-- End: page-top-outer -->

	<div class="clear">&nbsp;</div>

	<!--  start nav-outer-repeat................................................................................................. START -->
	<div class="nav-outer-repeat">
		<!--  start nav-outer -->
		<div class="nav-outer">

			<!-- start nav-right -->
			<div id="nav-right">

				<div class="nav-divider">&nbsp;</div>



				<sec:ifAllGranted roles="ROLE_USER">
					<div class="showhide-account">
						<g:loggedInUser />
					</div>

					<div class="nav-divider">&nbsp;</div>
					<a id="logout"
						href="${createLink(controller:'logout', action: 'index') }"> 
						
						Delogare
						<!--<r:img uri="/images/shared/nav/nav_logout.gif" />-->
						
					</a>
					<div class="clear">&nbsp;</div>
				</sec:ifAllGranted>

				<!--  start account-content -->
				<!-- <div class="account-content">
					<div class="account-drop-inner">
						<a href="" id="acc-settings">Setari cont</a>
						<div class="clear">&nbsp;</div>
						<div class="acc-line">&nbsp;</div>
						<a href="" id="acc-details">Detalii utilizator</a>
						<div class="clear">&nbsp;</div>
						<div class="acc-line">&nbsp;</div>
					</div>
				</div>
				 -->
				<!--  end account-content -->

			</div>
			<!-- end nav-right -->


			<!--  start nav -->
			<div class="nav">
				<div class="table">

					<!-- <ul class="${pageProperty(name:'page.selectedAcasa')}">
						<li><a
							href="${createLink(controller: 'acasa', action: 'acasa')}"><b>Acasa</b>
								<!--[if IE 7]><!--></a> <!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]--</li>
					</ul>

					<div class="nav-divider">&nbsp;</div>
					-->

					<ul class="${pageProperty(name:'page.selectedActiuni')}">
						<li><a
							href="${createLink(controller: 'actiune', action: 'cautare')}"><b>Actiuni</b>
								<!--[if IE 7]><!--></a> <!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
							<div class="select_sub show">
								<ul class="sub">
									<!-- <li class="${pageProperty(name:'page.selectedActiuniListComplete')}"><a
										href="${createLink(controller: 'actiune', action: 'listComplete')}">Toate
											actiunile</a></li> -->
									<li class="${pageProperty(name:'page.selectedActiuniCautare')}"><a
										href="${createLink(controller: 'actiune', action: 'cautare')}">Actiuni</a></li>

									<!-- <li class="${pageProperty(name:'page.selectedActiuniList')}"><a href="${createLink(controller: 'actiune', action: 'list')}">Toate
											operatiile</a></li>-->
									<li class="${pageProperty(name:'page.selectedActiuniCreate')}"><a
										href="${createLink(controller: 'actiune', action: 'create')}">Adauga
											actiune</a></li>

								</ul>
							</div> <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
					</ul>

					<div class="nav-divider">&nbsp;</div>

					<ul class="${pageProperty(name:'page.selectedTeme')}">
						<li><a
							href="${createLink(controller: 'tema', action: 'cautare')}"><b>Teme</b>
								<!--[if IE 7]><!--></a> <!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
							<div class="select_sub show">
								<ul class="sub">
									<!-- <li class="${pageProperty(name:'page.selectedTemeList')}"><a
										href="${createLink(controller: 'tema', action: 'list')}">Toate
											temele</a></li>
									-->
									<li class="${pageProperty(name:'page.selectedTemeCautare')}"><a
										href="${createLink(controller: 'tema', action: 'cautare')}">Teme</a></li>
									<li class="${pageProperty(name:'page.selectedTemeCreate')}"><a
										href="${createLink(controller: 'tema', action: 'create')}">Adauga
											tema</a></li>


								</ul>
							</div> <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
					</ul>

					<div class="nav-divider">&nbsp;</div>

					<ul class="${pageProperty(name:'page.selectedFirme')}">
						<li><a
							href="${createLink(controller: 'firma', action: 'cautare')}"><b>Firme</b>
								<!--[if IE 7]><!--></a> <!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
							<div class="select_sub show">
								<ul class="sub">
									<!-- <li class="${pageProperty(name:'page.selectedFirmeList')}"><a
										href="${createLink(controller: 'firma', action: 'list')}">Toate
											firmele</a></li> -->
									<li class="${pageProperty(name:'page.selectedFirmeCautare')}"><a
										href="${createLink(controller: 'firma', action: 'cautare')}">Firme</a></li>
									<li class="${pageProperty(name:'page.selectedFirmeCreate')}"><a
										href="${createLink(controller: 'firma', action: 'create')}">Adauga
											firma</a></li>

									<!-- <li class="${pageProperty(name:'page.selectedContacteList')}"><a
										href="${createLink(controller: 'contact', action: 'list')}">Toate
											contactele</a></li>-->
									<li
										class="${pageProperty(name:'page.selectedContacteCautare')}"><a
										href="${createLink(controller: 'contact', action: 'cautare')}">Contacte</a></li>
									<li class="${pageProperty(name:'page.selectedContacteCreate')}"><a
										href="${createLink(controller: 'contact', action: 'create')}">Adauga
											contact</a></li>


								</ul>
							</div> <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
					</ul>

					<div class="nav-divider">&nbsp;</div>

					<ul class="${pageProperty(name:'page.selectedProduse')}">
						<li><a
							href="${createLink(controller: 'produs', action: 'cautare')}"><b>Produse</b>
								<!--[if IE 7]><!--></a> <!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
							<div class="select_sub show">
								<ul class="sub">
									<!-- <li class="${pageProperty(name:'page.selectedProduseList')}"><a
										href="${createLink(controller: 'produs', action: 'list')}">Toate produsele</a></li> -->
									<li class="${pageProperty(name:'page.selectedProduseCautare')}"><a
										href="${createLink(controller: 'produs', action: 'cautare')}">Produse</a></li>
									<li class="${pageProperty(name:'page.selectedProduseCreate')}"><a
										href="${createLink(controller: 'produs', action: 'create')}">Adauga
											produs</a></li>
									<li class="${pageProperty(name:'page.selectedProduseStoc')}"><a
										href="${createLink(controller: 'produs', action: 'stoc')}">Stoc</a></li>
								</ul>
							</div> <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
					</ul>

					<sec:ifAllGranted roles="ROLE_ADMIN">

						<div class="nav-divider">&nbsp;</div>

						<ul class="${pageProperty(name:'page.selectedAdmin')}">
							<li><a
								href="${createLink(controller: 'settings', action: 'index')}"><b>Admin</b>
									<!--[if IE 7]><!--></a> <!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
								<div class="select_sub show">
									<ul class="sub">
										<li class="${pageProperty(name:'page.selectedAdminSettings')}"><a
											href="${createLink(controller: 'settings', action: 'index')}">Setari</a></li>

										<!-- <li
											class="${pageProperty(name:'page.selectedAdminAdhivaList')}"><a
											href="${createLink(controller: 'arhiva', action: 'list')}">Arhiva</a></li>  -->
										<li
											class="${pageProperty(name:'page.selectedAdminArhivaCautare')}"><a
											href="${createLink(controller: 'arhiva', action: 'cautare')}">Arhiva</a></li>
										<!-- 
										<li
											class="${pageProperty(name:'page.selectedAdminArhivaCreate')}"><a
											href="${createLink(controller: 'arhiva', action: 'create')}">Adauga
												arhiva</a></li>
 										-->

										<!-- <li class="${pageProperty(name:'page.selectedAdminEtapaList')}"><a href="/GEA/etapa/list">Etape</a></li> -->
										<li
											class="${pageProperty(name:'page.selectedAdminEtapaCautare')}"><a
											href="${createLink(controller: 'etapa', action: 'cautare')}">Etape</a></li>
										<!-- <li class="${pageProperty(name:'page.selectedAdminEtapaCreate')}"><a
											href="${createLink(controller: 'etapa', action: 'create')}">Adauga etapa</a></li> -->

										<!-- <li class="${pageProperty(name:'page.selectedAdminTipuriClientiList')}"><a href="/GEA/tipClient/list">Tipuri clienti</a></li>-->
										<li
											class="${pageProperty(name:'page.selectedAdminTipuriClientiCautare')}"><a
											href="${createLink(controller: 'tipClient', action: 'cautare')}">Tipuri
												Clienti</a></li>
										<!-- <li class="${pageProperty(name:'page.selectedAdminTipuriClientiCreate')}"><a
											href="${createLink(controller: 'tipClient', action: 'create')}">Adauga Tip Client</a></li> -->


										<!-- <li class="${pageProperty(name:'page.selectedAdminUserList')}"><a href="/GEA/user/list">Useri</a></li>-->
										<li
											class="${pageProperty(name:'page.selectedAdminUserCautare')}"><a
											href="${createLink(controller: 'user', action: 'cautare')}">Useri</a></li>
										<!-- <li class="${pageProperty(name:'page.selectedAdminUserCreate')}"><a
											href="${createLink(controller: 'user', action: 'create')}">Adauga user</a></li> -->

										<!-- <li class="${pageProperty(name:'page.selectedAdminTipuriEvenimentList')}"><a href="/GEA/tipEveniment/list">Tipuri clienti</a></li>-->
										<li
											class="${pageProperty(name:'page.selectedAdminTipuriEvenimentCautare')}"><a
											href="${createLink(controller: 'tipEveniment', action: 'cautare')}">Tipuri
												Ev</a></li>
										<!-- <li class="${pageProperty(name:'page.selectedAdminTipuriEvenimentCreate')}"><a
											href="${createLink(controller: 'tipEveniment', action: 'create')}">Adauga Tip Ev</a></li>-->

									</ul>
								</div> <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
						</ul>

					</sec:ifAllGranted>

					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
			<!--  start nav -->

		</div>
		<div class="clear"></div>
		<!--  start nav-outer -->
	</div>
	<!--  start nav-outer-repeat................................................... END -->

	<div class="clear"></div>

	<!-- start content-outer ........................................................................................................................START -->
	<div id="content-outer">
		<!-- start content -->
		<div id="content">

			<!--  start page-heading -->
			<table id="app-bar">
				<tr>
					<td id="app-bar-left-column">
						<div id="page-heading">
							<g:pageProperty name="page.heading" />
						</div>
					</td>

					<td id="app-bar-right-column">
						<div id="page-upper-right-options">
							<g:pageProperty name="page.upperRightOptions" />
						</div>
					</td>
				</tr>
			</table>

			<!-- end page-heading -->

			<table border="0" width="100%" cellpadding="0" cellspacing="0"
				id="content-table">
				<tr>
					<th rowspan="3" class="sized"><r:img
							uri="images/shared/side_shadowleft.jpg" /></th>
					<th class="topleft"></th>
					<td id="tbl-border-top">&nbsp;</td>
					<th class="topright"></th>
					<th rowspan="3" class="sized"><r:img
							uri="images/shared/side_shadowright.jpg" /></th>
				</tr>
				<tr>
					<td id="tbl-border-left"></td>
					<td>
						<!--  start content-table-inner ...................................................................... START -->
						<div id="content-table-inner">

							<!--  start table-content  -->
							<div id="table-content">

								<!--  start product-table ..................................................................................... -->
								<!-- <form id="mainform" action=""> -->

								<g:layoutBody />

								<!--  end product-table................................... -->
								<!-- </form> -->
							</div>
							<!--  end content-table  -->



						</div> <!--  end content-table-inner ............................................END  -->
					</td>
					<td id="tbl-border-right"></td>
				</tr>
				<tr>
					<th class="sized bottomleft"></th>
					<td id="tbl-border-bottom">&nbsp;</td>
					<th class="sized bottomright"></th>
				</tr>
			</table>
			<div class="clear">&nbsp;</div>

		</div>
		<!--  end content -->
		<div class="clear">&nbsp;</div>
	</div>
	<!--  end content-outer........................................................END -->

	<div class="clear">&nbsp;</div>

	<tmpl:/templates/footer />

	<iframe id="toPrint"
		style="height: 0px; width: 0px; position: absolute"></iframe>

	<r:layoutResources />
</body>
</html>