<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
				<div class="sidebar-title">
					<span>Notifications</span>
				</div>
				<ul class="notifications demo-slide-in"> <!-- .demo-slide-in is just for demonstration purposes. You can remove this. -->
					<li style="display: none;"> <!-- style-attr is here only for fading in this notification after a specific time. Remove this. -->
						<div class="col-left">
							<span class="label label-danger"><i class="icon-warning-sign"></i></span>
						</div>
						<div class="col-right with-margin">
							<a href="#">
								<span class="message">Bus No : Bus_100 <strong>#512</strong> Delayed due to traffic.</span>
								<span class="time">few seconds ago</span>
							</a>
						</div>
					</li>
					
					<li>
						<div class="col-left">
							<span class="label label-success"><i class="icon-plus"></i></span>
						</div>
						<div class="col-right with-margin">
							<span class="message"><strong>John</strong>'s account was created</span>
							<span class="time">10 Minutes ago</span>
						</div>
					</li>
				</ul>