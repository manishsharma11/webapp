<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Header -->
	<header class="header navbar navbar-fixed-top" role="banner">
		<!-- Top Navigation Bar -->
		<div class="container">

			<!-- Only visible on smartphones, menu toggle -->
			<ul class="nav navbar-nav">
				<li class="nav-toggle"><a href="javascript:void(0);" title=""><i class="icon-reorder"></i></a></li>
			</ul>

			<!-- Logo -->
			<a class="navbar-brand" href="#">
				
				<strong>Admin</strong>Panel
			</a>
			<!-- /logo -->

			<!-- Sidebar Toggler -->
			<a href="#" class="toggle-sidebar bs-tooltip" data-placement="bottom" data-original-title="Toggle navigation">
				<i class="icon-reorder"></i>
			</a>
			<!-- /Sidebar Toggler -->

			

			<!-- Top Right Menu -->
			<ul class="nav navbar-nav navbar-right">
				
	

				<!-- User Login Dropdown -->
				<li class="dropdown user">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<!--<img alt="" src="assets/img/avatar1_small.jpg" />-->
						<i class="icon-male"></i>
						<span class="username">${login_name}</span>
						<i class="icon-caret-down small"></i>
					</a>
					<ul class="dropdown-menu">
						<!-- <li><a href="pages_user_profile.html"><i class="icon-user"></i> My Profile</a></li>
						<li><a href="pages_calendar.html"><i class="icon-calendar"></i> My Calendar</a></li>
						<li><a href="#"><i class="icon-tasks"></i> My Tasks</a></li>
						<li class="divider"></li> -->
						<li><a href="/sts/school_guest/profile" ><i class="icon-user"></i> Profile</a></li>
						<li><a href="/sts/school_guest/preferences" ><i class="icon-user"></i> Preferences</a></li>
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