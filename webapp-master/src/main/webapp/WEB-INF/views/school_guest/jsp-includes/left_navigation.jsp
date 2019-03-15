<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--=== Navigation ===-->
				
				<ul id="nav">
					<c:if test="${current_page == 'homepage' }">
						<li class="current">
							<a href="/sts/school_guest/homepage">
								<i class="icon-dashboard"></i>
								Dashboard
							</a>
						</li>
					</c:if>
					<c:if test="${current_page != 'homepage' }">
						<li >
							<a href="/sts/school_guest/homepage">
								<i class="icon-dashboard"></i>
								Dashboard
							</a>
						</li>
					</c:if>
					
					<c:if test="${current_page == 'map' }">
						<li class="current">
							<a href="/sts/school_guest/map">
								<i class="icon-map-marker "></i>
								Map
							</a>
						</li>
					</c:if>
					<c:if test="${current_page != 'map' }">
						<li >
							<a href="/sts/school_guest/map">
								<i class="icon-map-marker"></i>
								map
							</a>
						</li>
					</c:if>
					
					<li>
					
					
						<a href="javascript:void(0);">
							<i class="icon-list-alt " ></i>
							Users
							
						</a>
						<ul class="sub-menu">
							<c:if test="${current_page == 'students_list' }">
						<li class="current">
							<a href="/sts/school_guest/persons/students">
								<i class="icon-user"></i>
								Students
							</a>
						</li>
					</c:if>
					<c:if test="${current_page != 'students_list' }">
						<li >
							<a href="/sts/school_guest/persons/students">
								<i class="icon-user"></i>
								Students
							</a>
						</li>
					</c:if>
					<c:if test="${current_page == 'drivers_list' }">
						<li class="current">
							<a href="/sts/school_guest/persons/drivers">
								<i class="icon-user"></i>
								Drivers
							</a>
						</li>
					</c:if>
					<c:if test="${current_page != 'drivers_list' }">
						<li >
							<a href="/sts/school_guest/persons/drivers">
								<i class="icon-user"></i>
								Drivers
							</a>
						</li>
					</c:if>
					<c:if test="${current_page == 'staff_list' }">
						<li class="current">
							<a href="/sts/school_guest/persons/staff">
								<i class="icon-user"></i>
								Staff
							</a>
						</li>
					</c:if>
					<c:if test="${current_page != 'staff_list' }">
						<li >
							<a href="/sts/school_guest/persons/staff">
								<i class="icon-user"></i>
								Staff
							</a>
						</li>
					</c:if>
					
						</ul>
					</li>
					<li>
						
						<a href="javascript:void(0);">
							<i class="icos-wifi-signal" ></i>
							&nbsp;&nbsp;RFIDs
							
						</a>
						<ul class="sub-menu">
						
								<c:if test="${current_page == 'students_rfid_list' }">
								<li class="current">
									<a href="/sts/school_guest/rfid/student">
										<i class="icon-user"></i>
										Students RFID
									</a>
								</li>
							</c:if>
							<c:if test="${current_page != 'students_rfid_list' }">
								<li >
									<a href="/sts/school_guest/rfid/student">
										<i class="icon-user"></i>
										Students RFID
									</a>
								</li>
							</c:if>
							<c:if test="${current_page == 'drivers_rfid_list' }">
								<li class="current">
									<a href="/sts/school_guest/rfid/driver">
										<i class="icon-male"></i>
										Driver RFID
									</a>
								</li>
							</c:if>
							<c:if test="${current_page != 'drivers_rfid_list' }">
								<li >
									<a href="/sts/school_guest/rfid/driver">
										<i class="icon-male"></i>
										Driver RFID
									</a>
								</li>
							</c:if>
							<c:if test="${current_page == 'staff_rfid_list' }">
								<li class="current">
									<a href="/sts/school_guest/rfid/staff">
										<i class=" icon-list-ul"></i>
										Staff RFID
									</a>
								</li>
							</c:if>
							<c:if test="${current_page != 'staff_rfid_list' }">
								<li >
									<a href="/sts/school_guest/rfid/staff">
										<i class=" icon-list-ul"></i>
										Staff RFID
									</a>
								</li>
							</c:if>
									
						</ul>
					</li>
					
					<c:if test="${current_page == 'buses_list' }">
					<!-- buses start -->
					<li class="current">
						
						<a href="/sts/school_guest/bus/buses">
							<img src="<c:url value="/resources/bus.png" /> " />
							&nbsp;&nbsp;Buses
							<!-- <span class="label label-info pull-right">3</span> -->
						</a>
						
					</li>
					</c:if>
					<c:if test="${current_page != 'buses_list' }">
					<!-- buses start -->
					<li>
						
						<a href="/sts/school_guest/bus/buses">
							<img src="<c:url value="/resources/bus.png" /> " />
							&nbsp;&nbsp;Buses
							<!-- <span class="label label-info pull-right">3</span> -->
						</a>
						
					</li>
					<!-- buses ends -->
					</c:if>
					
					
					<c:if test="${current_page == 'routes_list' }">
					<!-- buses start -->
					<li class="current">
						
						<a href="/sts/school_guest/route/routes">
							<i class="icon-road" ></i>
							Routes
							<!-- <span class="label label-info pull-right">3</span> -->
						</a>
						
					</li>
					</c:if>
					<c:if test="${current_page != 'routes_list' }">
					<!-- buses start -->
					<li>
						
						<a href="/sts/school_guest/route/routes">
							<i class="icon-road" ></i>
							Routes
							<!-- <span class="label label-info pull-right">3</span> -->
						</a>
						
					</li>
					<!-- buses ends -->
					</c:if>
					
					
				</ul>
				<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
				<div class="sidebar-title">
					<span>Notifications</span>
				</div>
				<ul class="notifications demo-slide-in" id="add_notifications"> <!-- .demo-slide-in is just for demonstration purposes. You can remove this. -->
					
					
					
				</ul>