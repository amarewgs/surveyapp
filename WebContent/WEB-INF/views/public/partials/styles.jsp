<%@ include file="/WEB-INF/views/include.jsp"%>
<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<c:url value="/libs/css/customize-template.css" var="customTemplate" />
<link rel="stylesheet" href="${customTemplate}" type="text/css" media="screen, projection">

<c:url value="/libs/css/styles.css" var="pagerStyles" />
<link rel="stylesheet" href="${pagerStyles}" type="text/css" media="screen, projection">

<c:url value="/libs/css/jquery.treeview.css" var="jquerytreeviewcss"></c:url>
<link rel="stylesheet" type="text/css" href="${jquerytreeviewcss }" media="screen, projection"> 

<!--<c:url value="/libs/css/bootstrap.min.css" var="urlBootstrap" />
<link rel="stylesheet" href="${urlBootstrap}" type="text/css"media="screen, projection">-->

<c:url value="/libs/css/style.css" var="stylecss" />
<link rel="stylesheet" href="${stylecss}" type="text/css"media="screen, projection">

<c:url value="/libs/css/jquery.treetable.css" var="jquerytreetablecss"></c:url>
<link rel="stylesheet" type="text/css" href="${jquerytreetablecss }" media="screen, projection">

<c:url value="/libs/css/jquery.treetable.theme.default.css" var="jquerytreetabledefaultcss"></c:url>
<link rel="stylesheet" type="text/css" href="${jquerytreetabledefaultcss }" media="screen, projection">

<c:url value="/libs/css/jquery.dataTables.css" var="dataTablesCss"></c:url>
<link rel="stylesheet" type="text/css" href="${dataTablesCss }" media="screen, projection">

<c:url value="/libs/js/jquery-1.7.2.min.js" var="jquery" />
<script type="text/javascript" src="${jquery}"></script>

<c:url value="/libs/js/bootstrap.min.js" var="bootstrap" />
<script type="text/javascript" src="${bootstrap}"></script>

<c:url value="/libs/js/quickpager.jquery.js" var="quickpager" />
<script type="text/javascript" src="${quickpager}"></script>

<c:url value="/libs/js/jquery.dataTables.js" var="dataTables"/>
<script type="text/javascript"  src="${dataTables}"></script>

<c:url value="/libs/js/jquery.treetable.js" var="jquerytreetable"/>
<script type="text/javascript"  src="${jquerytreetable}"></script>

<c:url value="/libs/js/jquery.treeview.js" var="jquerytreeview"/>
<script type="text/javascript"  src="${jquerytreeview}"></script>

<c:url value="/libs/js/jquery.treeview.edit.js" var="jquerytreeviewedit"/>
<script type="text/javascript"  src="${jquerytreeviewedit}"></script> 

<c:url value="/libs/js/jquery.treeview.async.js" var="jquerytreeviewasync"/>
<script type="text/javascript"  src="${jquerytreeviewasync}"></script>

<style><!--
body {
	padding-top: 60px;
}
a.cancel {
	display: inline-block;
	height: 18px;
	padding: 4px;
	margin-bottom: 9px;
	font-size: 13px;
	line-height: 18px;
	color: #555;
	background-color: #fff;
	border: 1px solid #ccc;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	vertical-align: middle;
}
a.cancel:hover{
text-decoration: none;
}
form.login {
	border: 2px solid silver;
	padding: 4px;
	width: 400px;
	border-radius: 5px;
	margin: auto;
}

.container .page {
	width: 1000px;
}

/* .body-content .page .container{
	width: 1000px !important;
} */
.error {
	margin: auto;
	width: 400px;
	color: red;
}
form{
	display: inline-block;
}

.errorblock {
	color: #000;
	border: 1.5px solid #ff0000;
	padding: 4px;
	margin: 16px 0 16px;
	border-radius: 3px;
	width: 90%;
}
/*
div .brand {
	margin-left: auto !important;
	width: 900px !important;
	margin-right: auto !important;
	display: block !important;
	float: none !important;
}
.span8 {
	width: 100% !important;
}*/
.container-signin {
	width: 400px;
	margin: auto;
}
#pass-strength-result.good {
	background-color:#FFEC8B;
	border-color:#FFCC00 !important;
}
#pass-strength-result {
	background-color:#EEEEEE;
	border-color:#DDDDDD !important;
	padding: 3px;
	margin: 0;
	width: 206px;
	border-radius: 4pc;
	text-align: center;
	font-family: verdana;
}
#pass-strength-result.bad {
	background-color:#FFB78C;
	border-color:#FF853C !important;
}
#pass-strength-result.strong {
	background-color:#C3FF88;
	border-color:#8DFF1C !important;
}
#pass-strength-result.short {
	background-color:#FFA0A0;
	border-color:#F04040 !important;
}

form{
	display: inline-block;
}
#preview-question-name {
	width: 100%; margin-left: 0;
	margin-bottom: 2px;
	font-size: 12px;
	font-weight: bold;
	font-family: verdana;
}
/* .navbar-inner .container {width:1000px;}
.navbar-right {
float: right !important;
} */

.navbar-right {
float: right !important;
}

.simplePagerNav {
	clear: both;
	display: inline-block;
	width: 90%;
	float: none;
	padding: 4px;
	text-align: center;
}
ul.simplePagerNav li{
	display: inline-block;
	float: none; 
	padding: 10px;
	margin-bottom: 10px;
	font-family: georgia;
	font-size: 14px;
	width: 40px;
}
label {
	font-size: 11px;
}
.span13{
	margin: 0 auto;
	float: none;
}
#mainTreeMenu {
	position: fixed;
	height: 92%; 
	overflow: auto; 
	width: 270px;
	border-radius: 2px;
	/* border: 2px solid #d5d5d5; */
	margin-bottom: 20px;
	overflow-x: hidden;
} 

#menuTreeview{
	font-size: 12px;
	margin-top: 40px;
}
#menuTreeview li a:hover{
	text-decoration: none;
}
#menuTreeview li span {
	/* vertical-align: middle;
	display: inline; */
}

.container.page {
  padding-top: 5px;
  /* margin-top: 30px; */
}
.nav{
	margin-bottom: 10px;
}
/* 
.nav-page{
	height: auto;
}
.navbar-fixed-top{
	top: auto;
}
.nav-page .nav.nav-tabs{
	padding-top: 4px;
} */
#body-content {
	padding-top: 0px;
}
--></style>