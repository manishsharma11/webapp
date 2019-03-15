<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--=== Navigation ===-->

<%
	String appName = request.getContextPath();
%>
<!-- hover color to list added by sami -->

<head>

<!-- 
   <style type="text/css">
 
 ul:HOVER ,a:HOVER{
	background-color: green;
}
 
 </style> -->

</head>

<ul id="nav" class="dropdown">
	<c:if test="${current_page == 'homepage' }">
		<li class="current"><a href="<%=appName%>/school_admin/homepage">
				<i class="icon-dashboard"></i> Dashboard
		</a></li>
	</c:if>
	<c:if test="${current_page != 'homepage' }">
		<li><a href="<%=appName%>/school_admin/homepage"> <i
				class="icon-dashboard"></i> Dashboard
		</a></li>
	</c:if>
	<c:if test="${current_page == 'push_note' }">
		<li class="current"><a
			href="<%=appName%>/school_admin/push_note/create"><i
				class="icon-bullhorn"></i> Push Notifications </a></li>
	</c:if>
	<c:if test="${current_page != 'push_note' }">
		<li><a href="<%=appName%>/school_admin/push_note/create"><i
				class="icon-bullhorn"></i> Push Notifications </a></li>
	</c:if>
	<c:if test="${current_page == 'sms_note' }">
		<li class="current"><a
			href="<%=appName%>/school_admin/sms_note/create"><i
				class="icon-bullhorn"></i> SMS Notifications </a></li>
	</c:if>
	<c:if test="${current_page != 'sms_note' }">
		<li><a href="<%=appName%>/school_admin/sms_note/create"><i
				class="icon-bullhorn"></i> SMS Notifications </a></li>
	</c:if>	
	<c:if test="${current_page == 'map' }">
		<li class="current"><a href="<%=appName%>/school_admin/map">
				<i class="icon-map-marker"></i> Map
		</a></li>
	</c:if>
	<c:if test="${current_page != 'map' }">
		<li><a href="<%=appName%>/school_admin/map"> <i
				class="icon-map-marker"></i> map
		</a></li>
	</c:if>
	<c:if test="${current_page == 'commuter' }">
		<!-- buses start -->
		<li class="current"><a
			href="<%=appName%>/school_admin/commuter/list"><i
				class="icon-user"></i> Commuter <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
	<c:if test="${current_page != 'commuter' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/school_admin/commuter/list"><i
				class="icon-user"></i> Commuter <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- buses ends -->
	</c:if>
	<%-- <li><a href="javascript:void(0);"> <img alt=""
			src="<c:url value="/resources/icons/user.jpg" /> " width="20">
			Subscribers --%>

	<%-- </a><ul class="sub-menu">
			<c:if test="${current_page == 'students_list' }">
				<li class="current"><a
					href="<%=appName%>/school_admin/persons/students"> <img alt=""
						src="<c:url value="/resources/icons/student.jpg" /> " width="20">
						Students
				</a></li>
			</c:if>
			<c:if test="${current_page != 'students_list' }">
				<li><a href="<%=appName%>/school_admin/persons/students"> <img
						alt="" src="<c:url value="/resources/icons/student.jpg" /> "
						width="20"> Students
				</a></li>
			</c:if> --%>
	<c:if test="${current_page == 'drivers_list' }">
		<li class="current"><a
			href="<%=appName%>/school_admin/persons/drivers"> <img alt=""
				src="<c:url value="/resources/icons/driver_icon.jpg" /> " width="20">
				Drivers
		</a></li>
	</c:if>
	<c:if test="${current_page != 'drivers_list' }">
		<li><a href="<%=appName%>/school_admin/persons/drivers"> <img
				alt="" src="<c:url value="/resources/icons/driver_icon.jpg" /> "
				width="20"> Drivers
		</a></li>
	</c:if>
	<%-- <c:if test="${current_page == 'staff_list' }">
				<li class="current"><a href="<%=appName%>/school_admin/persons/staff">
						<img alt="" src="<c:url value="/resources/icons/staff.jpg" /> "
						width="20"> Staff
				</a></li>
			</c:if>
			<c:if test="${current_page != 'staff_list' }">
				<li><a href="<%=appName%>/school_admin/persons/staff"> <img alt=""
						src="<c:url value="/resources/icons/staff.jpg" /> " width="20">
						Staff<!-- <i class="icon-angle-right "></i> -->
				</a></li>
			</c:if> --%>
	<!-- </ul></li> -->
	<!--li><a href="javascript:void(0);"> <img alt=""
			src="<c:url value="/resources/icons/rfid.png" /> " width="20">
			&nbsp;&nbsp;RFIDs

	</a>
		<ul class="sub-menu">

			<c:if test="${current_page == 'students_rfid_list' }">
				<li class="current"><a href="<%=appName%>/school_admin/rfid/student">
						<img alt="" src="<c:url value="/resources/icons/student.jpg" /> "
						width="20"> Students RFID
				</a></li>
			</c:if>
			<c:if test="${current_page != 'students_rfid_list' }">
				<li><a href="<%=appName%>/school_admin/rfid/student"> <img alt=""
						src="<c:url value="/resources/icons/student.jpg" /> " width="20">
						Students RFID
				</a></li>
			</c:if>
			<c:if test="${current_page == 'drivers_rfid_list' }">
				<li class="current"><a href="<%=appName%>/school_admin/rfid/driver">
						<img alt=""
						src="<c:url value="/resources/icons/driver_icon.jpg" /> "
						width="20"> Driver RFID
				</a></li>
			</c:if>
			<c:if test="${current_page != 'drivers_rfid_list' }">
				<li><a href="<%=appName%>/school_admin/rfid/driver"> <img alt=""
						src="<c:url value="/resources/icons/driver_icon.jpg" /> "
						width="20"> Driver RFID
				</a></li>
			</c:if>
			<c:if test="${current_page == 'staff_rfid_list' }">
				<li class="current"><a href="<%=appName%>/school_admin/rfid/staff">
						<img alt="" src="<c:url value="/resources/icons/staff.jpg" /> "
						width="20"> Staff RFID
				</a></li>
			</c:if>
			<c:if test="${current_page != 'staff_rfid_list' }">
				<li><a href="<%=appName%>/school_admin/rfid/staff"> <img alt=""
						src="<c:url value="/resources/icons/staff.jpg" /> " width="20">
						Staff RFID
				</a></li>
			</c:if>

		</ul></li-->
	<c:if test="${current_page == 'buses_list' }">
		<!-- buses start -->
		<li class="current"><a href="<%=appName%>/school_admin/bus/buses">
				<img src="<c:url value="/resources/icons/bus.png" /> " width="20" />
				&nbsp;&nbsp;Buses <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
	<c:if test="${current_page != 'buses_list' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/school_admin/bus/buses"> <img
				src="<c:url value="/resources/icons/bus.png" /> " width="20" />
				&nbsp;&nbsp;Buses <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- buses ends -->
	</c:if>
	
		<c:if test="${current_page == 'bookings_list' }">
		<!-- buses start -->
		<li class="current"><a href="<%=appName%>/school_admin/bookings/list">
				<img src="<c:url value="/resources/icons/book_1.jpg" /> " width="20" />
				&nbsp;&nbsp;Bookings <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
		<c:if test="${current_page != 'bookings_list' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/school_admin/bookings/list"> <img
				src="<c:url value="/resources/icons/book_1.jpg" /> " width="20" />
				&nbsp;&nbsp;Bookings <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- buses ends -->
	</c:if>
	<c:if test="${current_page == 'devices' }">
		<li class="current"><a
			href="<%=appName%>/school_admin/devices/list"><i
				class="icon-mobile-phone"></i> devices </a></li>
	</c:if>
	<c:if test="${current_page != 'devices' }">
		<li><a href="<%=appName%>/school_admin/devices/list"> <i
				class="icon-mobile-phone"></i> devices
		</a></li>
	</c:if>
	<c:if test="${current_page == 'stops' }">
		<!-- buses start -->
		<li class="current"><a
			href="<%=appName%>/school_admin/stop/stops"> <img alt=""
				src="<c:url value="/resources/k21059883.jpg" /> " width="30">
				Stops <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
	<c:if test="${current_page != 'stops' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/school_admin/stop/stops"> <img
				alt="" src="<c:url value="/resources/k21059883.jpg" /> " width="30">
				Stops <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- buses ends -->
	</c:if>
	<c:if test="${current_page == 'routes_list' }">
		<!-- buses start -->
		<li class="current"><a
			href="<%=appName%>/school_admin/route/routes"> <i
				class="icon-road"></i> Routes <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
	<c:if test="${current_page != 'routes_list' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/school_admin/route/routes"> <i
				class="icon-road"></i> Routes <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- buses ends -->
	</c:if>
	<c:if test="${current_page == 'trips' }">
		<!-- buses start -->
		<li class="current"><a
			href="<%=appName%>/school_admin/trip/trips"> <img alt=""
				src="<c:url value="/resources/icons/trips.gif" /> " width="20">
				Trips Details<!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
	<c:if test="${current_page != 'trips' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/school_admin/trip/trips"> <img
				alt="" src="<c:url value="/resources/icons/trips.gif" /> "
				width="20"> Trips Details<!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- buses ends -->
	</c:if>
	
	<c:if test="${current_page == 'trips1' }">
		<!-- buses start -->
		<li class="current"><a
			href="<%=appName%>/school_admin/trip/trips1"> <img alt=""
				src="<c:url value="/resources/icons/trips.gif" /> " width="20">
				Trips <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
	<c:if test="${current_page != 'trips1' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/school_admin/trip/trips1"> <img
				alt="" src="<c:url value="/resources/icons/trips.gif" /> "
				width="20"> Trips <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- buses ends -->
	</c:if>
	
	<c:if test="${current_page == 'suggested_routes' }">
		<!-- buses start -->
		<li class="current"><a href="<%=appName%>/school_admin/suggested_routes/list">
				<img src="<c:url value="/resources/icons/sbox.gif" /> " width="20" />
				&nbsp;&nbsp;Suggested Routes <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
		<c:if test="${current_page != 'suggested_routes' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/school_admin/suggested_routes/list"> <img
				src="<c:url value="/resources/icons/sbox.gif" /> " width="20" />
				&nbsp;&nbsp;Suggested Routes <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- buses ends -->
	</c:if>
	
	<c:if test="${current_page == 'feedback' }">
		<!-- buses start -->
		<li class="current"><a href="<%=appName%>/school_admin/feedback/list">
				<img src="<c:url value="/resources/icons/feedback.jpg" /> " width="20" />
				&nbsp;&nbsp;Feedback <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
		<c:if test="${current_page != 'feedback' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/school_admin/feedback/list"> <img
				src="<c:url value="/resources/icons/feedback.jpg" /> " width="20" />
				&nbsp;&nbsp;Feedback <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- buses ends -->
	</c:if>
	
	<c:if test="${current_page == 'recharge_options' }">
		<!-- buses start -->
		<li class="current"><a href="<%=appName%>/school_admin/recharge_options/list">
				<img src="<c:url value="/resources/icons/recharge.png" /> " width="20" />
				&nbsp;&nbsp;Recharge Options <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
		<c:if test="${current_page != 'recharge_options' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/school_admin/recharge_options/list"> <img
				src="<c:url value="/resources/icons/recharge.png" /> " width="20" />
				&nbsp;&nbsp;Recharge Options <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- buses ends -->
	</c:if>
	
	<c:if test="${current_page == 'settings' }">
		<!-- buses start -->
		<li class="current"><a href="<%=appName%>/school_admin/settings">
				<img src="<c:url value="/resources/icons/recharge.png" /> " width="20" />
				&nbsp;&nbsp;Settings <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
		<c:if test="${current_page != 'settings' }">
		<!-- buses start -->
		<li><a href="<%=appName%>/school_admin/settings"> <img
				src="<c:url value="/resources/icons/recharge.png" /> " width="20" />
				&nbsp;&nbsp;Settings <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		
	</c:if>
	
	
	<!-- Reoprts Starts -->
	<c:if test="${current_page == 'reports' }">
		<!-- buses start -->
		<li class="current"><a
			href="<%=appName%>/school_admin/reports/generate"> <img alt=""
				src="<c:url value="/resources/icons/report_check.png" /> "
				width="20"> Reports <!-- <span class="label label-info pull-right">3</span> -->
		</a> <%-- <ul class="sub-menu">

				<c:if test="${current_page == 'students_rfid_list' }">
					<li class="current"><a href="<%=appName%>/school_admin/rfid/student">
							<img alt="" src="<c:url value="/resources/icons/student.jpg" /> "
							width="20"> Students RFID
					</a></li>
				</c:if>
				<c:if test="${current_page != 'students_rfid_list' }">
					<li><a href="<%=appName%>/school_admin/rfid/student"> <img alt=""
							src="<c:url value="/resources/icons/student.jpg" /> " width="20">
							Students RFID
					</a></li>
				</c:if>
				<c:if test="${current_page == 'drivers_rfid_list' }">
					<li class="current"><a href="<%=appName%>/school_admin/rfid/driver">
							<img alt=""
							src="<c:url value="/resources/icons/driver_icon.jpg" /> "
							width="20"> Driver RFID
					</a></li>
				</c:if>
				<c:if test="${current_page != 'drivers_rfid_list' }">
					<li><a href="<%=appName%>/school_admin/rfid/driver"> <img alt=""
							src="<c:url value="/resources/icons/driver_icon.jpg" /> "
							width="20"> Driver RFID
					</a></li>
				</c:if>
				<c:if test="${current_page == 'staff_rfid_list' }">
					<li class="current"><a href="<%=appName%>/school_admin/rfid/staff">
							<img alt="" src="<c:url value="/resources/icons/staff.jpg" /> "
							width="20"> Staff RFID
					</a></li>
				</c:if>
				<c:if test="${current_page != 'staff_rfid_list' }">
					<li><a href="<%=appName%>/school_admin/rfid/staff"> <img alt=""
							src="<c:url value="/resources/icons/staff.jpg" /> " width="20">
							Staff RFID
					</a></li>
				</c:if>

			</ul> --%></li>
	</c:if>
	<c:if test="${current_page != 'reports' }">
		<!-- buses start -->
		<li><a href=""> <img alt=""
				src="<c:url value="/resources/icons/report_check.png" /> "
				width="20"> Reports <!-- <span class="label label-info pull-right">3</span> -->
		</a>
			<ul class="sub-menu">

				<c:if test="${current_page == 'students_rfid_list' }">
					<li class="current"><a
						href="<%=appName%>/school_admin/reports/student"> <img alt=""
							src="<c:url value="/resources/icons/student.jpg" /> " width="20">
							Students Report
					</a></li>
				</c:if>
				<c:if test="${current_page != 'students_rfid_list' }">
					<li><a href="<%=appName%>/school_admin/reports/student"> <img
							alt="" src="<c:url value="/resources/icons/student.jpg" /> "
							width="20"> Students Report
					</a></li>
				</c:if>
				<c:if test="${current_page == 'drivers_rfid_list' }">
					<li class="current"><a
						href="<%=appName%>/school_admin/reports/driver"> <img alt=""
							src="<c:url value="/resources/icons/driver_icon.jpg" /> "
							width="20"> Driver Report
					</a></li>
				</c:if>

				<c:if test="${current_page != 'drivers_rfid_list' }">
					<li><a href="<%=appName%>/school_admin/reports/busreport">
							<img alt="" src="<c:url value="/resources/icons/bus.png"/>"
							width="20">Bus Report
					</a></li>
				</c:if>
				<c:if test="${current_page == 'drivers_rfid_list' }">
					<li class="current"><a
						href="<%=appName%>/school_admin/reports/busreport"> <img
							alt="" src="<c:url value="/resources/icons/bus.png"/>" width="20">Bus
							Report
					</a></li>
				</c:if>

				<c:if test="${current_page != 'drivers_rfid_list' }">
					<li><a href="<%=appName%>/school_admin/reports/driver"> <img
							alt="" src="<c:url value="/resources/icons/driver_icon.jpg" /> "
							width="20"> Driver Report
					</a></li>
				</c:if>
				<c:if test="${current_page == 'staff_rfid_list' }">
					<li class="current"><a
						href="<%=appName%>/school_admin/reports/generate"> <img alt=""
							src="<c:url value="/resources/icons/staff.jpg" /> " width="20">
							Trip Report
					</a></li>
				</c:if>
				<c:if test="${current_page != 'staff_rfid_list' }">
					<li><a href="<%=appName%>/school_admin/reports/generate">
							<img alt="" src="<c:url value="/resources/icons/staff.jpg" /> "
							width="20"> Trip Report
					</a></li>
				</c:if>


			</ul></li>
		<!-- buses ends -->
	</c:if>
	<!-- Email Events -->
	<c:if test="${login_role  == 'ROLE_ADMIN' }">
		<c:if test="${current_page == 'events' }">
			<!-- buses start -->
			<li class="current"><a
				href="<%=appName%>/school_admin/emails/events"> <img alt=""
					src="<c:url value="/resources/icons/write email.png" /> "
					width="20"> Email Events <!-- <span class="label label-info pull-right">3</span> -->
			</a></li>
		</c:if>
		<c:if test="${current_page != 'events' }">
			<!-- event start -->
			<li><a href="<%=appName%>/school_admin/emails/events"> <img
					alt="" src="<c:url value="/resources/icons/write email.png" /> "
					width="20"> Email Events <!-- <span class="label label-info pull-right">3</span> -->
			</a></li>
			<!-- event ends -->
		</c:if>
	</c:if>
	<!-- Sessions -->
	<c:if test="${login_role  == 'ROLE_ADMIN' }">
		<c:if test="${current_page == 'session' }">
			<!-- session start -->
			<li class="current"><a
				href="<%=appName%>/school_admin/session/sessionlist"> <img
					alt="" src="<c:url value="/resources/icons/trips.gif" /> "
					width="20"> School Sessions <!-- <span class="label label-info pull-right">3</span> -->
			</a></li>
		</c:if>
		<c:if test="${current_page != 'session' }">
			<!-- event start -->
			<li><a href="<%=appName%>/school_admin/session/sessionlist">
					<img alt="" src="<c:url value="/resources/icons/trips.gif" /> "
					width="20"> School Sessions <!-- <span class="label label-info pull-right">3</span> -->
			</a></li>
			<!-- event ends -->
		</c:if>
	</c:if>
	<c:if test="${current_page == 'busfareprices_list' }">
		<!-- session start -->
		<li class="current"><a
			href="<%=appName%>/school_admin/busfareprices/busfareprices_list">
				<img alt="" src="<c:url value="/resources/icons/trips.gif" /> "
				width="20"> Buses Fare Prices <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
	</c:if>
	<c:if test="${current_page != 'busfareprices_list' }">
		<!-- event start -->
		<li><a
			href="<%=appName%>/school_admin/busfareprices/busfareprices_list">
				<img alt="" src="<c:url value="/resources/icons/trips.gif" /> "
				width="20"> Buses Fare Prices <!-- <span class="label label-info pull-right">3</span> -->
		</a></li>
		<!-- event ends -->
	</c:if>
	<li></li>


</ul>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <div class="sidebar-title">
	<span>Notifications</span>
</div> -->
<!-- <ul class="notifications demo-slide-in" id="add_notifications">
	.demo-slide-in is just for demonstration purposes. You can remove this.



</ul> -->