<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--=== Navigation ===-->

<%
	String appName = request.getContextPath();
%>

<head>

</head>

<ul id="nav" class="dropdown">
	<c:if test="${current_page == 'my_profile' }">
		<li class="current"><a href="<%=appName%>/webapp/commuter/my_profile">
				<img alt=""
				src="<c:url value="/resources/icons_webapp/my_profile.png" /> " width="20"> My Profile
		</a></li>
	</c:if>
	<c:if test="${current_page != 'my_profile' }">
		<li><a href="<%=appName%>/webapp/commuter/my_profile""> <img alt=""
				src="<c:url value="/resources/icons_webapp/my_profile.png" /> " width="20"> My Profile
		</a></li>
	</c:if>
	<c:if test="${current_page == 'book_now' }">
		<li class="current"><a
			href="<%=appName%>/webapp/booking/bookingPage"><img alt=""
				src="<c:url value="/resources/icons_webapp/book_now.png" /> " width="20"> Book Now </a></li>
	</c:if>
	<c:if test="${current_page != 'book_now' }">
		<li><a href="<%=appName%>/webapp/booking/bookingPage"><img alt=""
				src="<c:url value="/resources/icons_webapp/book_now.png" /> " width="20"> Book Now </a></li>
	</c:if>
	<c:if test="${current_page == 'my_bookings' }">
		<li class="current"><a href="<%=appName%>/webapp/booking/my_bookings">
				<img alt=""
				src="<c:url value="/resources/icons_webapp/my_bookings.png" /> " width="20"> My Bookings
		</a></li>
	</c:if>
	<c:if test="${current_page != 'my_bookings' }">
		<li><a href="<%=appName%>/webapp/booking/my_bookings"> <img alt=""
				src="<c:url value="/resources/icons_webapp/my_bookings.png" /> " width="20"> My Bookings
		</a></li>
	</c:if>
	<c:if test="${current_page == 'my_wallet' }">
		<!-- buses start -->
		<li class="current"><a
			href="<%=appName%>/webapp/transaction/my_wallet"><img alt=""
				src="<c:url value="/resources/icons_webapp/my_wallet.png" /> " width="20"> My Wallet <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
	<c:if test="${current_page != 'my_wallet' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/webapp/transaction/my_wallet"><img alt=""
				src="<c:url value="/resources/icons_webapp/my_wallet.png" /> " width="20"> My Wallet <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- buses ends -->
	</c:if>
	
	<c:if test="${current_page == 'my_transactions' }">
		<li class="current"><a
			href="<%=appName%>/webapp/transaction/my_transactions"> <img alt=""
				src="<c:url value="/resources/icons_webapp/my_transactions.png" /> " width="20">
				My Transactions
		</a></li>
	</c:if>
	<c:if test="${current_page != 'my_transactions' }">
		<li><a href="<%=appName%>/webapp/transaction/my_transactions"> <img
				alt="" src="<c:url value="/resources/icons_webapp/my_transactions.png" /> "
				width="20"> My Transactions
		</a></li>
	</c:if>
	<c:if test="${current_page == 'routes' }">
		<!-- buses start -->
		<li class="current"><a href="<%=appName%>/webapp/stops/findRoutes">
				<img src="<c:url value="/resources/icons_webapp/routes.png" /> " width="20" />
				&nbsp;&nbsp;Routes <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
	<c:if test="${current_page != 'routes' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/webapp/stops/findRoutes"> <img
				src="<c:url value="/resources/icons_webapp/routes.png" /> " width="20" />
				&nbsp;&nbsp;Routes <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- buses ends -->
	</c:if>
	
		<c:if test="${current_page == 'suggest_route' }">
		<!-- buses start -->
		<li class="current"><a href="<%=appName%>/webapp/route/show_suggest_route">
				<img src="<c:url value="/resources/icons_webapp/suggest_route.png" /> " width="20" />
				&nbsp;&nbsp;Suggest Routes <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
		<c:if test="${current_page != 'suggest_route' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/webapp/route/show_suggest_route"> <img
				src="<c:url value="/resources/icons_webapp/suggest_route.png" /> " width="20" />
				&nbsp;&nbsp;Suggest Routes <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- buses ends -->
	</c:if>
	<c:if test="${current_page == 'refer_&_earn' }">
		<li class="current"><a
			href="<%=appName%>/webapp/referral/generate"><img alt=""
				src="<c:url value="/resources/icons_webapp/refer_earn.png" /> " width="20"> Refer & Earn </a></li>
	</c:if>
	<c:if test="${current_page != 'refer_&_earn' }">
		<li><a href="<%=appName%>/webapp/referral/generate"> <img alt=""
				src="<c:url value="/resources/icons_webapp/refer_earn.png" /> " width="20"> Refer & Earn
		</a></li>
	</c:if>
	<c:if test="${current_page == 'notifications' }">
		<!-- buses start -->
		<li class="current"><a
			href="<%=appName%>/webapp/notifications"> <img alt=""
				src="<c:url value="/resources/icons_webapp/notifications.png" /> " width="30">
				Notifications <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
	<c:if test="${current_page != 'notifications' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/webapp/notifications"> <img
				alt="" src="<c:url value="/resources/icons_webapp/notifications.png" /> " width="30">
				Notifications <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- buses ends -->
	</c:if>
	<c:if test="${current_page == 'feedback' }">
		<!-- buses start -->
		<li class="current"><a
			href="<%=appName%>/webapp/feedback/show"> <img alt=""
				src="<c:url value="/resources/icons_webapp/feedback.png" /> " width="20"> Feedback <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
	<c:if test="${current_page != 'feedback' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/webapp/feedback/show"> <img alt=""
				src="<c:url value="/resources/icons_webapp/feedback.png" /> " width="20"> Feedback <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- buses ends -->
	</c:if>
	<c:if test="${current_page == 'faq' }">
		<!-- buses start -->
		<li class="current"><a
			href="<%=appName%>/webapp/faq"> <img alt=""
				src="<c:url value="/resources/icons_webapp/faq.png" /> " width="20">
				FAQ<!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
	<c:if test="${current_page != 'faq' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/webapp/faq"> <img
				alt="" src="<c:url value="/resources/icons_webapp/faq.png" /> "
				width="20"> FAQ<!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- buses ends -->
	</c:if>
	
	<c:if test="${current_page == 'about_us' }">
		<!-- buses start -->
		<li class="current"><a
			href="<%=appName%>/webapp/general/about_us"> <img alt=""
				src="<c:url value="/resources/icons_webapp/about_us.png" /> " width="20">
				About US <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
	<c:if test="${current_page != 'about_us' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/webapp/general/about_us"> <img
				alt="" src="<c:url value="/resources/icons_webapp/about_us.png" /> "
				width="20"> About US <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- buses ends -->
	</c:if>
	</ul>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
