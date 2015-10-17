<%@ include file="/WEB-INF/views/include.jsp"%>
<html lang="es">
<head>
<meta charset="utf-8">
<title><fmt:message key="app.title" /></title>
<%@ include file="../partials/styles.jsp"%>
<%@ page session="false"%>
<%
	response.setHeader("pragma", "no-cache");
	response.setHeader("Cache-control",
			"no-cache, no-store, must-revalidate");
	response.setHeader("Expires", "0");
%>
</head>
<body>
		<%@ include file="../partials/banner.jsp"%>
<div id="body-container">
   <div id="body-content">	
    <%@ include file="../partials/target_menu.jsp" %>	
		<section class="page container">
			<div class="row">
			<%@ include file="../partials/treemenu.jsp" %>
				<div class="span12" style="margin: 0 auto;">
					<div class="box" style="display:inline-block; width: 100%">
					<spring:url var = "action" value='/admin/asset/save' />
						<form:form style="width: 100%" method="post" action="${action}" class="form form-horizontal" commandName="assetForm" id="statusForm">
						<div class="box-header">
							<i class="icon-bookmark"></i>
							<h5>Target Form</h5>
							<!-- <div style="float: right;">
								<a href="#" class="btn btn-small btn-save"> <i
									class="btn-icon-only icon-ok"></i>
								</a>
							</div> -->
						</div>
						<div class="box-content">
						
							<div class="container span11">
							<div style="width: 100%; display: inline-block;">
								<form:errors path="*" cssClass="errorblock span5" element="div" />
							</div>
								<div id="assetDiv">
									<form:input id="assetId" type="hidden" path="assetId" name="assetId" value="${assetId }"></form:input>
										
										<div class="control-group ">
											<label class="control-label">Name</label>
											<div class="controls">
												<form:input  id="name" name="name" path="name" class="span3" type="text" value="${name}" autocomplete="false"></form:input>
												<form:errors path="name" cssClass="error"/>
											</div>
										</div>
										<div class="control-group ">
											<label class="control-label">Description</label>
											<div class="controls">
											<form:textarea path="description" name="description" id="description" value="${description }" class="span5"></form:textarea>
											<form:errors path="description" cssClass="error"/>
											</div>
										</div>
										<div class="control-group ">
											<label class="control-label">
												Is Active?</label>
											<div class="controls">
												<input type="checkbox" style="width: 40px;" id="isActive" class="span5"
													value="is active" />
													<input type="hidden" id="active" value="${assetForm.active }" name="active">
											</div>
										</div>
										
										<div class="control-group ">
											<label class="control-label">
												Target Type</label>
											<div class="controls">
											<input type="hidden" id="assetType" name="" value="${assetForm.targetType.id }"/>
												<select name="targetType.id" id="targetType" class="span3">
													<option value="">target type</option>
													<c:forEach items="${assetTypes}" var="assetType">
														<option value="${assetType.id}">
															<c:out value="${assetType.name}"></c:out>
														</option>
													</c:forEach>
												</select> <form:errors path="targetType" cssClass="error"/>
											</div>
										</div>										
										
										<div class="control-group ">
											<label class="control-label">
												Status</label>
											<div class="controls">
												<input type="hidden" id="status" name="status.id" value="${assetForm.status.id }"/>
												<%-- <c:forEach items="${statuses}" var="status">
													<div style="margin: 4px; display: inline-block;">
													<input ${asset.status.id == status.id ? 'checked' : '' } type="radio" id="status${status.id }" name="statusRadio" value="${status.id }" onclick="setStatus(this)"/>
													<span>${status.name }</span>
													</div>
												</c:forEach> --%>
												<select id="assetStatus" name="assetStatus" class="span3">
													<option selected="selected">select status</option></select>
													<form:errors path="status" cssClass="error"/>
											</div>
										</div>									
										
								</div>
								
								<div style="width:90%;">
									<footer id="submit-actions" class="form-actions">
										<button id="submit-button" type="submit"
											class="btn btn-primary" name="action" value="CONFIRM">Save</button>
										<button type="button"
											onclick="window.location='<spring:url value="/admin/asset/list" htmlEscape="true" />'" class="btn"
											name="action" value="CANCEL">Cancel</button>
									</footer>
								</div>
							</div>
							
						</div>
						</form:form>
					</div>
					
				</div>
				
			</div>
		</section>
	</div>
	</div>
	<script type="text/javascript">
	
	
		$('.date').datepicker();
	
		$('#isActive').on('click', function(){
			if($(this).is(":checked")){
				$('#active').val('true');
			} else {
				$('#active').val('false');
			}
		});
		
		function setStatus(obj) {
			
			$('#status').val($(obj).val());
		}
		
		if($('#active').val() == 'true')
			$('#isActive').attr('checked', $('#active').val());
		
		$('#targetType').on('change', function(e){
			$('#sections').remove();
			getAssetTypeTemplate();
			getTargetTypeStatus();
		});
		
		$('#assetStatus').on('change', function(e){
			$('#status').val($(this).val());
		});
		
		function getAssetTypeTemplate() {
            var assetTypeId = $('#targetType').val();
			var assetId = $('#assetId').val();
			if(assetTypeId != '') {
	            $.ajax({
	                type: 'GET',
	                url: '<spring:url value="/admin/asset/template" htmlEscape="true" />/' + assetTypeId + (assetId != '' ? ('/' + assetId) : '/' + '-1'),
	                contentType: "application/json; charset=utf-8",
	                dataType: "json",
	                //data:{'assetTypeId': assetTypeId},
	                success: function(data) {
						if(data) {
							console.log(JSON.stringify(data.sectionAttributes));
							var sections = $('<div id="sections"></div>');
							var sectionsCount = 0;
							$.each(data.sectionAttributes, function(index) {
								
								var sectionName = $('<legend>' + this.name+'</legend>');
								var section = this;
								$.each(this.attributes, function(index) {
									var input = "";
									if(this.fieldType == 'BOOLEAN'){
										input = $('<input type="hidden" name="assetAttributes['+ sectionsCount +'].value" id="' + this.label.replace(/\s/g, '') + '" value="' + (this.value !== null || this.value !== '' ? this.value : 'false') + '"><input  id="chk' + this.label.replace(/\s/g, '') + '" class="span3" type="checkbox" ' + (this.value !== null && this.value == 'true' ? 'checked="checked"' : '') + ' value="' + (this.value == null || this.value == '' ? 'false' : this.value) + '" onclick="setcheckboxes(this);" />');	
									}else if (this.fieldType == 'TEXT') {
										input = $('<input  id="name" name="assetAttributes['+ sectionsCount +'].value" class="span3" type="text" value="' + (this.value == null ? '' : this.value) + '" />');		
									} else if(this.fieldType == 'RADIO') {
										
										//CONVERT COMMA SEPARATED VALUES TO ARRAY AND CREATE RADIO OPTIONS
										var options = this.value.split(',');
										input = $(getRadioOptions('assetAttributes['+ sectionsCount +'].value', options));
									} else if(this.fieldType == 'MULTI_SELECT') {
										
										//CREATE MULTI-SELECT OPTIONS
										var options = this.value.split(',');
										input = $(getCheckboxes('assetAttributes['+ sectionsCount +'].value', options));
										
									} else if(this.fieldType == 'DROPDOWN') {
										var options = this.value.split(',');
										
										input = $('<select id="name" name="assetAttributes['+ sectionsCount +'].value">' + getDropDownList('assetAttributes['+ sectionsCount +'].value', "name", options).html() + '</select>');
										
										//CREATE DROPDOWN OPTIONS
									} else if(this.fieldType == 'MULTI_LINE') {
										
										input = $('<textarea  id="name" name="assetAttributes['+ sectionsCount +'].value" class="span3" type="text" >' + (this.value == null ? '' : this.value) + '</textarea>');
										
									} else if(this.fieldType == 'DATE') {
										
										input = $('<input type="text" id="' + this.label.replace(/\s/g, '') + '" class="date" />');
										
										
									}
									var controlGroup = $('<div class="control-group "><label class="control-label" style="margin-right:4px;">' + this.label + ':</label>' +										
											'<div class="controls"> <input  id="attribute' + sectionsCount + '" name="assetAttributes['+ sectionsCount +'].attribute.id" class="span3" type="hidden" value="' + this.attributeId + '" />' +
											'<input  id="section' + sectionsCount + '" name="assetAttributes['+ sectionsCount +'].section.id" class="span3" type="hidden" value="' + section.sectionId + '" />' +
											'</div></div>');
									controlGroup.append(input);
									sectionName.append(controlGroup);
									sectionsCount++;
									
									if(this.fieldType == 'DATE') input.datepicker();
								});
								sections.append(sectionName);
								
							});
							$('#assetDiv').append(sections);
						}
	                },
	                error: function(e) {
	                    //alert("Error" + e);
	                }
	            });
			}
        } 
		
		function getDropDownList(name, id, optionList) {
		    var combo = $("<select></select>").attr("id", id).attr("name", name);

		    $.each(optionList, function (i, el) {
		        combo.append("<option>" + el + "</option>");
		    });

		    return combo;
		}
		
		function getCheckboxes(name, optionList) {
			
			var checkboxes = '';
			 $.each(optionList, function (i, el) {
		        checkboxes += '<p style="margin: 0; padding: 0; font-weight: 100; line-height: 24px;"> <input type="hidden" name="' + name + '" id="' + el.replace(/\s/g, '') + i + '">' + 
		        '<input type="checkbox" id="chk' + el.replace(/\s/g, '') + i + '" value="' + el + '" onclick="setcheckboxes(this);"/><span style="margin-left: 4px;">' + el + '</span></p>';
		    });

			 return checkboxes;
		}
		
		function setcheckboxes(obj) {
			console.log('setcheckboxes clicked');
			var id = $(obj).attr('id').replace('chk', '');
			if($(obj).is(":checked")){
				$('#' + id).val($(obj).val());
			} else {
				$('#' + id).val('');
			}
		}
		
		function getRadioOptions(name, optionList) {
			
			var radios = '';
			 $.each(optionList, function (i, el) {
		        radios += '<p style="margin: 0; padding: 0; font-weight: 100; line-height: 24px;"><input type="radio" name="colors" value="' + el + '"/><span style="margin-left: 4px;">' + el + '</span></p>';
		    });

			 return radios;
		}
		
		function getTargetTypeStatus() {
			var assetTypeId = $('#targetType').val();
			$('#assetStatus').empty();
			 $("#assetStatus").append('<option value="">select status</option>');
			 if(assetTypeId != '') {
				 $.ajax({
		                type: 'GET',
		                url: '<spring:url value="/admin/asset/status" htmlEscape="true" />/' + assetTypeId,
		                contentType: "application/json; charset=utf-8",
		                dataType: "json",
		                //data:{'assetTypeId': assetTypeId},
		                success: function(data) {
		                	console.log(JSON.stringify(data));
				            $.each(data, function(i,item){
				                // Create and append the new options into the select list
				                $("#assetStatus").append('<option value='+item.statusId+'>'+item.name+'</option>');
				                
				                $('#assetStatus').val($('#status').val());
				            });
		                }
			        });
			 }
		}
		$(document).ready(function() {
			
		$('#targetType').val($('#assetType').val());//.trigger('change');

		$('#targetType').trigger('change');
		});
		</script>
</body>
</html>