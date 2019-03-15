<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<!-- <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>  -->
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Dashboard</title>
<!-- Files to be loaded -->

<script type="text/javascript"
	src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>

<!-- Include CSS files -->
<%@ include file="jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="jsp-includes/js_files.jsp"%>

<!-- <script type="text/javascript">

function openWindow(){
		
	window.open('bus?bus_id=', 'popup_name','height=' + 600 + 
			',width=' + 900+ ',resizable=no,scrollbars=yes,toolbar=no,menubar=no,location=no');
}

</script> -->
</head>

<body>
			<br></br>
			<!--=== Feeds (with Tabs) ===-->
					<div class="col-md-6" style="height: 100px;">
						<div class="widget" >
							<div class="widget-header">
								<h4><i class="icon-reorder"></i> <font color="sienna" size="3">
								
								  <c:choose>
								  	<c:when test="${status=='outofservice' }">
								  	Buses ${status }
								  	
								  	</c:when>
								  <c:otherwise> ${status } notifications</c:otherwise>
								  
								  </c:choose>
								
								 </font>  </h4>
								
							</div>
							<div class="widget-content" >
								<div class="tabbable tabbable-custom">
									<ul class="nav nav-tabs">
										
									</ul>
									<div class="tab-content">
										<div class="tab-pane active" id="tab_feed_1">
											<div class="scroller" data-height="290px" data-always-visible="1" data-rail-visible="0">
												<ul class="feeds clearfix">
											  	
											  		<c:if test="${status == 'outofservice' }">
											  		
											  			 <table  class="table table-striped table-bordered table-hover" >
																			<thead>
																			<tr><th>S.No</th><th>Bus ID</th><th>DriverName</th></tr>
																			
																			</thead>
																		
																			<tbody role="alert" aria-live="polite" aria-relevant="all">
																			
																			<c:forEach items="${dailyNotifications }" var="note">
																			
																			
																				<tr>
																						<td width="10"><c:set value="${x+1}" var="x"></c:set> 
																						<c:out value="${x }"/></td>
																						<td>${note.bus_licence_number }</td>
																						<td>${note.getDriver().getDriver_name() }</td>
																				</tr>
																			</c:forEach>
																			</tbody>
																			</table>
											  		
											  		
											  		</c:if>
											  		<c:if test="${status != 'outofservice' }">
											  		
											  			<c:if test="${Notifications eq null }">
																	
																		<div class="alert alert-warning fade in">
																			
																			<strong>Info!</strong> &nbsp;&nbsp;&nbsp; no notifications found
																		</div>
														</c:if>
														<c:forEach items="${Notifications}" var="note">
																
																	<li>
																	
																		<div class="col1">
																			<div class="content">
																				<div class="content-col1">
																					<c:if test="${status == 'ontime' }">
																						<div class="label label-success">
																							<i class="icon-bell"></i>
																						</div>
																						
																					</c:if>
																					<c:if test="${status == 'late' }">
																						<div class="label label-warning">
																							<i class="icon-bell"></i>
																						</div>
																					</c:if>
																					<c:if test="${status == 'verylate' }">
																						<div class="label label-danger">
																							<i class="icon-bell"></i>
																						</div>
																					</c:if>
																					
																					
																					
																				</div>
																				 
																				 <div class="content-col2">
																						<div class="desc">${note.notification }</div>
																					</div>
																				 
																					
																				
																			</div>
																		</div> <!-- /.col1 -->
																		<div class="col2">
																			<div class="date">
																				${note.time }
																			</div>
																		</div> <!-- /.col2 -->
																	
																	</li>
																</c:forEach>
											  		
											  		</c:if>
															
														
													
												</ul> <!-- /.feeds -->
											</div> <!-- /.scroller -->
										</div> <!-- /#tab_feed_1 -->

										
									</div> <!-- /.tab-content -->
								</div> <!-- /.tabbable tabbable-custom-->
							</div> <!-- /.widget-content -->
						</div> <!-- /.widget .box -->
					</div> <!-- /.col-md-6 -->
					<!-- /Feeds (with Tabs) -->

</body>
</html>
