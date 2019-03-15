<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Header -->
<%
	String appName1 = request.getContextPath();
%>
	<header class="header navbar navbar-fixed-top" role="banner">
		<!-- Top Navigation Bar -->
		<div class="container" style="
    background-color: #FFFFFF;
">

			<!-- Only visible on smartphones, menu toggle -->
			<ul class="nav navbar-nav">
				<li class="nav-toggle"><a href="javascript:void(0);" title="">
				<i class="icon-reorder"></i>
				</a></li>
					
				<li class="nav-toggle" style="border:0px;width:100px" >
					<a href="http://www.easycommute.co">
					   <img src="<c:url value="/resources/icons_webapp/easycommute_name.png"/>" width="100px"/>
					</a>
				</li>	
			</ul>

			<!-- Logo -->
			<a class="navbar-brand" href="/sts/webapp/booking/bookingPage">
				<strong><c:out value="${schoolname}"></c:out></strong><img alt=""
				src="<c:url value="/resources/icons_webapp/easycommute_name.png" /> " width="190">
			</a>
			<!-- /logo -->

			<!-- Sidebar Toggler -->
			<a href="#" class="toggle-sidebar bs-tooltip" data-placement="bottom" data-original-title="Toggle navigation">
				<i class="icon-reorder"></i>
			</a>
			<!-- /Sidebar Toggler -->

			<!-- Top Right Menu -->
			<ul class="nav navbar-nav navbar-right">
				
				<!-- playStore-->
				<li class="dropdown user" style="width:110px;margin:0px">
					<a href="https://play.google.com/store/apps/details?id=easy.commute.shuttle.app&utm_source=web_login">
					<img src="<c:url value="/resources/icons_webapp/google-play-badge.jpg" />" style="max-width:100%;"/></a>
				</li>
				<!-- /playStore -->	
	

				<!-- User Login Dropdown -->
				<li class="dropdown user">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<!--<img alt="" src="assets/img/avatar1_small.jpg" />-->
						<i class="icon-male" style="color:black"></i>
						<%-- <span class="username">${login_name}</span> --%>
						<c:if test="${login_name=='admin' }">
						<span class="username">Admin</span>
						</c:if>
						<c:if test="${login_name=='teacher' }">
						<span class="username">Teacher</span>
						</c:if>
						<c:if test="${login_name=='parent' }">
						<span class="username">Parent</span>
						</c:if>
						 
						<i class="icon-caret-down small" style="color:black"></i>
					</a>
					<ul class="dropdown-menu">
						<li><a href="<%=appName1%>/webapp/commuter/my_profile" ><i class="icon-user"></i> My Profile</a></li>
						<li><a href="<c:url value="/j_spring_security_logout" />" ><i class="icon-key"></i> Log Out</a></li>
					</ul>
				</li>
				<!-- /user login dropdown -->
			</ul>
			<!-- /Top Right Menu -->
		</div>
		<!-- /top navigation bar -->

		<!--=== Project Switcher ===-->
		<div id="project-switcher" class="container project-switcher">
			<div id="scrollbar">
				<div class="handle"></div>
			</div>

			
		</div> <!-- /#project-switcher -->
	</header> <!-- /.header -->