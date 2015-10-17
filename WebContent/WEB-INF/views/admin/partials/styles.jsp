<%@ include file="/WEB-INF/views/include.jsp"%>
<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<c:url value="/libs/css/customize-template.css" var="customTemplate" />
<link rel="stylesheet" href="${customTemplate}" type="text/css" media="screen, projection">

<c:url value="/libs/css/jquery.dataTables.css" var="dataTablesCss"></c:url>
<link rel="stylesheet" type="text/css" href="${dataTablesCss }" media="screen, projection">

<c:url value="/libs/css/jquery-ui.min.css" var="jqueryuicss"></c:url>
<link rel="stylesheet" type="text/css" href="${jqueryuicss }" media="screen, projection">

<c:url value="/libs/css/jquery.treeview.css" var="jquerytreeviewcss"></c:url>
<link rel="stylesheet" type="text/css" href="${jquerytreeviewcss }" media="screen, projection"> 

<c:url value="/libs/css/jquery.treetable.css" var="jquerytreetablecss"></c:url>
<link rel="stylesheet" type="text/css" href="${jquerytreetablecss }" media="screen, projection">

<c:url value="/libs/css/jquery.treetable.theme.default.css" var="jquerytreetabledefaultcss"></c:url>
<link rel="stylesheet" type="text/css" href="${jquerytreetabledefaultcss }" media="screen, projection">

<c:url value="/libs/js/jquery-1.7.2.min.js" var="jquery" />
<script type="text/javascript" src="${jquery}"></script>

<c:url value="/libs/js/bootstrap.min.js" var="bootstrap" />
<script type="text/javascript" src="${bootstrap}"></script>

<c:url value="/libs/js/jquery.dataTables.js" var="dataTables"/>
<script type="text/javascript"  src="${dataTables}"></script>

<c:url value="/libs/js/jquery.treeview.js" var="jquerytreeview"/>
<script type="text/javascript"  src="${jquerytreeview}"></script>

<c:url value="/libs/js/jquery.treeview.edit.js" var="jquerytreeviewedit"/>
<script type="text/javascript"  src="${jquerytreeviewedit}"></script> 

<c:url value="/libs/js/jquery.treeview.async.js" var="jquerytreeviewasync"/>
<script type="text/javascript"  src="${jquerytreeviewasync}"></script>

<c:url value="/libs/js/jquery.treetable.js" var="jquerytreetable"/>
<script type="text/javascript"  src="${jquerytreetable}"></script>

<c:url value="/libs/js/nicEdit.js" var="nicEdit"/>
<script src="${nicEdit}" type="text/javascript"></script>

<c:url value="/libs/js/jquery-ui.min.js" var="jqueryui"/>
<script type="text/javascript"  src="${jqueryui}"></script>

<c:url value="/libs/js/jquery.cookie.js" var="jquerycookie"/>
<script type="text/javascript"  src="${jquerycookie}"></script>

<style>
/* body {
	padding-top: 60px;
} */
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
/* .navbar-inner .container {width:1000px;} */
.navbar-right {
float: right !important;
}
/* .navbar .navbar-inner {
    text-align: center;
}
.navbar .navbar-inner .nav {
    float: none;
    display:inline-block;
} */
/* .navbar-fixed-top .navbar-inner {
	margin-left: auto !important;
	width: 900px !important;
	margin-right: auto !important;
	display: block !important;
	float: none !important;
} */
.container {
	/* width: 1100px !important; */
}
.error {
	margin: auto;
	color: red;
	display: block;
	font-size: 13px;
	font-style: italic;
}
.success {
	margin: auto;
	color: green;
	display: block;
	font-size: 13px;
	font-style: italic;
	margin: 10px;
	border: 1px solid;
	padding: 4px;
	border-radius: 4px;
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
	font-size: 13px;
	font-style: italic;
}
.container .page {
	width: 1000px;
}
/* .span8 {
	width: 100% !important;
} */
.controls a i {
	color: silver; text-decoration: none; vertical-align: text-bottom;
}

#preview-question-name {
	width: 100%; margin-left: 0;
	margin-bottom: 6px;
	font-size: 15px;
	font-weight: bold;
	font-family: verdana;
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
.span13 {
	margin: 0 auto;
	float: none;
}
@media (max-width: 980px) {
    .form-horizontal .control-group > label {
        float: none;
        width: auto;
        padding-top: 0;
        text-align: left;
    }
    .form-horizontal .controls {
        margin-left: 0;
    }
    .form-horizontal .control-list {
        padding-top: 0;
    }
    /* .form-horizontal .form-actions {
        padding-left: 10px;
        padding-right: 10px;
    } */
}
.form-horizontal .control-group {
margin-bottom: 10px;
}
.form-horizontal .control-label {
width: 130px;
}
.form-horizontal .controls {
margin-left: 140px;
}
 #mainTreeMenu {
	position: fixed;
	height: 88%; 
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

.nav-page{
	height: auto;
}
.navbar-fixed-top{
	top: auto;
}
.nav-page .nav.nav-tabs{
	padding-top: 4px;
}
</style>