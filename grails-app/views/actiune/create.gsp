<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'actiune.label', default: 'Actiune')}" />
<title><g:message code="default.create.label"
		args="[entityName]" /></title>

<r:require module="application" />
<r:layoutResources />

<parameter name="selectedAcasa" value="select" />
<parameter name="selectedActiuni" value="current" />
<parameter name="selectedFirme" value="select" />
<parameter name="selectedProduse" value="select" />
<parameter name="selectedTeme" value="select" />
<parameter name="selectedAdmin" value="select" />

<parameter name="selectedActiuniCreate" value="sub_show" />

</head>
<body>
<!-- 
	<g:javascript>
	
		function updateContact(e) {
		
			if (contacte) {
				var rselect = document.getElementById('city')
		
				var l = rselect.length
		
				while (l > 0) {
					l-- rselect.remove(l)
				}
		
				for (var i=0; i < cities.length; i++) {
					var city = cities[i]
					var opt = document.createElement('option');
					opt.text = city.name
					opt.value = city.id
					try {
						rselect.add(opt, null)
					} catch(ex) {
						rselect.add(opt)
					}
				}
			}
		}

	</g:javascript>
-->
	<content tag="heading">
	<h1>
		<g:message code="default.create.label" args="[entityName]" />
	</h1>
	</content>

	<tmpl:/templatesMessages/error />

	<g:form action="save" enctype="multipart/form-data">
		<g:hiddenField name="id" value="${instance?.id}" />

		<fieldset class="form">
			<g:applyLayout name="form">
			<tmpl:/templates/form />
		</g:applyLayout>
		</fieldset>

		<fieldset class="buttons">

			<g:submitButton name="create" class="save"
				value="${message(code: 'default.button.create.label', default: 'Create')}" />

		</fieldset>
	</g:form>

	<r:layoutResources />
</body>
</html>
