<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />

</head>
<body>

	<content tag="heading">
	<h1>Conturi Bancare</h1>
	</content>

	<content tag="upperRightOptions">
		<a onClick="javascript:printi('leftColumn')" href="javascript:;"><r:img class="optionIcon" uri="/images/icons/onebit_39.png" width="24px" height="24px" alt='Printare' /></a>
		<a onclick="javascript:ShowHide('HiddenDiv')" href="javascript:;"><r:img class="optionIcon" uri="/images/icons/onebit_02.png" width="24px" height="24px" alt='Cautare' /></a>
	</content>

	<table id="twoColumns">
		<tr>

			<td id="leftColumn"><tmpl:/templates/list model="[instanceList:
				instanceList, fieldNameList: fieldNameList, fieldList: fieldList]"
				/></td>

			<td class="mid" id="HiddenDiv" style="DISPLAY: none">
				<div id="rightColumn"><tmpl:/templates/search /></div>
			</td>
		</tr>
	</table>

	<script language="JavaScript">
		function ShowHide(divId) {
			if (document.getElementById(divId).style.display == 'none') {
				document.getElementById(divId).style.display = 'block';
			} else {
				document.getElementById(divId).style.display = 'none';
			}
		}

		function printi(divId){
			var content = document.getElementById(divId);
			var pri = document.getElementById("toPrint").contentWindow;
			pri.document.open();
			pri.document.write(content.innerHTML);
			pri.document.close();
			pri.focus();
			pri.print();
		}
		
	</script>

</body>
</html>