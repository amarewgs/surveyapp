<%@ include file="/WEB-INF/views/include.jsp"%>
<section class="nav nav-page" style="right: 0; left: 0; z-index: 1030; margin-bottom: 0;">
      <div class="container">
        <div class="row">
            <div class="span7">
                <header class="page-header">
                    
                </header>
            </div>
            <div class="page-nav-options" >
                <div class="span12" style="float: right">
                    <ul class="nav nav-tabs" style="float: right">
                        <li ${requestScope['javax.servlet.forward.request_uri'] == '/SurveyApp/admin/index' ? 'class="active"' : ''}>
                            <a href="<spring:url value="/admin/index" htmlEscape="true" />"><i class="icon-home"></i>Home</a>
                        </li>
                         <li ${requestScope['javax.servlet.forward.request_uri'] == '/SurveyApp/admin/campaign/list' ? 'class="active"' : ''}>
                            <a href="<spring:url value="/admin/campaign/list" htmlEscape="true" />">Campaign</a>
                        </li>
                        <li ${requestScope['javax.servlet.forward.request_uri'] == '/SurveyApp/admin/questionnaire/list' ? 'class="active"' : ''}>
                        	<a href="<spring:url value="/admin/questionnaire/list" htmlEscape="true" />">Questionnaire</a>
                        </li>
                       
                        <li ${requestScope['javax.servlet.forward.request_uri'] == '/SurveyApp/admin/question/list' ? 'class="active"' : ''}>
                        	<a href="<spring:url value="/admin/question/list" htmlEscape="true" />">Question</a>
                        </li>
                        <li ${requestScope['javax.servlet.forward.request_uri'] == '/SurveyApp/admin/asset/list' ? 'class="active"' : ''}>
                        	<a href="<spring:url value="/admin/asset/list" htmlEscape="true" />">Target</a>
                        </li>
                         <li ${requestScope['javax.servlet.forward.request_uri'] == '/SurveyApp/users/questionnaireResults' ? 'class="active"' : ''}>
                         	<a href="<spring:url value="/admin/users/questionnaireResults" htmlEscape="true" />">Results</a>
                         </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>