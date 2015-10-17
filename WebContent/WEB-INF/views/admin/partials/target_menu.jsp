<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ include file="menu.jsp" %>
<section class="nav" style="right: 0; left: 0; z-index: 1030; margin-bottom: 0;margin-top: 10px;">
      <div class="container">
        <div class="row">
            <div >
                <div class="span12" style="float: right">
                <spring:url value = "" var = "currentUrl" ></spring:url>
                    <ul class="nav nav-tabs" style="float: right;   width: 100%;">
                        <li ${requestScope['javax.servlet.forward.request_uri'] == '/SurveyApp/admin/attribute/list' ? 'class="active"' : ''}>
                      		<a href="<spring:url value="/admin/attribute/list" htmlEscape="true" />">Attribute
                      		</a>
                     	</li>
                        <li ${requestScope['javax.servlet.forward.request_uri'] == '/SurveyApp/admin/section/list' ? 'class="active"' : ''}>
                            <a href="<spring:url value="/admin/section/list" htmlEscape="true" />">Section
                        	</a>
                        </li>                                   
                        <li ${requestScope['javax.servlet.forward.request_uri'] == '/SurveyApp/admin/template/list' ? 'class="active"' : ''}>
                            <a href="<spring:url value="/admin/template/list" htmlEscape="true" />">Template</a>
                        </li>
                        <li ${requestScope['javax.servlet.forward.request_uri'] == '/SurveyApp/admin/status/list' ? 'class="active"' : ''}>
                             <a href="<spring:url value="/admin/status/list" htmlEscape="true" />">Status</a>
                        </li>
                        <li ${requestScope['javax.servlet.forward.request_uri'] == '/SurveyApp/admin/assetType/list' ? 'class="active"' : ''}>
                             <a href="<spring:url value="/admin/assetType/list" htmlEscape="true" />">Target Type</a>
                        </li>
                        <li ${requestScope['javax.servlet.forward.request_uri'] == '/SurveyApp/admin/asset/list' ? 'class="active"' : ''}>
                             <a href="<spring:url value="/admin/asset/list" htmlEscape="true" />">Target</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>