<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
</head>
<body>

	<div class="main-container">
		<div class="white-bg">

			<div id="midd-container-inner">
				<form id="bus-tic" class="ng-pristine ng-valid">
					<ul class="mt20">
						<li><label ng-class="startLocation ? 'visible' : ''"
							class="non-visible">From (Origin)</label> <input
							id="mobileNumber" placeholder="From (Origin)" type="text"
							ng-model="startLocation" autocomplete="off" name-only="name-only"
							typeahead="city.name for city in cities | filter:{name:$viewValue}:startsWith  | limitTo:8"
							focus-me="true"
							typeahead-on-select="afterSelection({focusSecond : true})"
							class="form-control changeplaceholder showdropdown mb10 ng-pristine ng-valid ng-touched"
							tabindex="0" aria-invalid="false">
						<ul class="dropdown-menu"
								ng-style="{display: isOpen()&amp;&amp;'block' || 'none', top: position.top+'px', left: position.left+'px'}"
								typeahead-popup="" matches="matches" active="activeIdx"
								select="select(activeIdx)" query="query" position="position"
								style="display: none;">
								<!-- ngRepeat: match in matches -->
							</ul>
							<div class="tic-error pad-lr"></div></li>
						<li><label ng-class="destinationLocation ? 'visible' : ''"
							class="non-visible">To (Destination)</label> <input
							id="mobileNumber" placeholder="To (Destination)" type="text"
							ng-model="destinationLocation" autocomplete="off"
							name-only="name-only"
							typeahead="city.name for city in cities | filter:{name:$viewValue}:startsWith  | limitTo:8"
							focus-me=""
							typeahead-on-select="afterSelection({focusThird : true})"
							class="form-control changeplaceholder showdropdown mb10 ng-pristine ng-untouched ng-valid"
							tabindex="0" aria-invalid="false">
						<ul class="dropdown-menu"
								ng-style="{display: isOpen()&amp;&amp;'block' || 'none', top: position.top+'px', left: position.left+'px'}"
								typeahead-popup="" matches="matches" active="activeIdx"
								select="select(activeIdx)" query="query" position="position"
								style="display: none;">
								<!-- ngRepeat: match in matches -->
							</ul>
							<div class="tic-error pad-lr"></div></li>
						<li>
							<div class="fl dateDiv">
								<label ng-class="dateTravel ? 'fixed-label-tic' : ''"
									class="ml2 fixed-label-tic">Select Date of Journey</label> <input
									pick-a-date="date" ng-model="dateTravel"
									pick-a-date-options="dateOptions" focus-me=""
									class="cal form-control border-bo-no recharge-date ng-pristine ng-untouched ng-valid picker__input"
									type="text" readonly="" id="P1742604446" aria-haspopup="true"
									aria-expanded="false" aria-readonly="false"
									aria-owns="P1742604446_root P1742604446_submit" tabindex="0"
									aria-invalid="false">
								<div class="picker" id="P1742604446_root" aria-hidden="true">
									<div class="picker__holder">
										<div class="picker__frame">
											<div class="picker__wrap">
												<div class="picker__box">
													<div class="picker__header">
														<div class="picker__nav--prev picker__nav--disabled"
															data-nav="-1"></div>
														<div class="picker__nav--next" data-nav="1"></div>
														<div class="picker__month">February</div>
														<div class="picker__year">2016</div>
													</div>
													<table class="picker__table">
														<thead>
															<tr>
																<th class="picker__weekday">Sun</th>
																<th class="picker__weekday">Mon</th>
																<th class="picker__weekday">Tue</th>
																<th class="picker__weekday">Wed</th>
																<th class="picker__weekday">Thu</th>
																<th class="picker__weekday">Fri</th>
																<th class="picker__weekday">Sat</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td><div
																		class="picker__day picker__day--outfocus picker__day--disabled"
																		data-pick="1454178600000" role="button"
																		aria-controls="P1742604446" aria-disabled="true">31</div></td>
																<td><div
																		class="picker__day picker__day--infocus picker__day--disabled"
																		data-pick="1454265000000" role="button"
																		aria-controls="P1742604446" aria-disabled="true">1</div></td>
																<td><div
																		class="picker__day picker__day--infocus picker__day--disabled"
																		data-pick="1454351400000" role="button"
																		aria-controls="P1742604446" aria-disabled="true">2</div></td>
																<td><div
																		class="picker__day picker__day--infocus picker__day--disabled"
																		data-pick="1454437800000" role="button"
																		aria-controls="P1742604446" aria-disabled="true">3</div></td>
																<td><div
																		class="picker__day picker__day--infocus picker__day--disabled"
																		data-pick="1454524200000" role="button"
																		aria-controls="P1742604446" aria-disabled="true">4</div></td>
																<td><div
																		class="picker__day picker__day--infocus picker__day--disabled"
																		data-pick="1454610600000" role="button"
																		aria-controls="P1742604446" aria-disabled="true">5</div></td>
																<td><div
																		class="picker__day picker__day--infocus picker__day--today picker__day--highlighted"
																		data-pick="1454697000000" role="button"
																		aria-controls="P1742604446"
																		aria-activedescendant="true">6</div></td>
															</tr>
															<tr>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1454783400000" role="button"
																		aria-controls="P1742604446">7</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1454869800000" role="button"
																		aria-controls="P1742604446">8</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1454956200000" role="button"
																		aria-controls="P1742604446">9</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1455042600000" role="button"
																		aria-controls="P1742604446">10</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1455129000000" role="button"
																		aria-controls="P1742604446">11</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1455215400000" role="button"
																		aria-controls="P1742604446">12</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1455301800000" role="button"
																		aria-controls="P1742604446">13</div></td>
															</tr>
															<tr>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1455388200000" role="button"
																		aria-controls="P1742604446">14</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1455474600000" role="button"
																		aria-controls="P1742604446">15</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1455561000000" role="button"
																		aria-controls="P1742604446">16</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1455647400000" role="button"
																		aria-controls="P1742604446">17</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1455733800000" role="button"
																		aria-controls="P1742604446">18</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1455820200000" role="button"
																		aria-controls="P1742604446">19</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1455906600000" role="button"
																		aria-controls="P1742604446">20</div></td>
															</tr>
															<tr>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1455993000000" role="button"
																		aria-controls="P1742604446">21</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1456079400000" role="button"
																		aria-controls="P1742604446">22</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1456165800000" role="button"
																		aria-controls="P1742604446">23</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1456252200000" role="button"
																		aria-controls="P1742604446">24</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1456338600000" role="button"
																		aria-controls="P1742604446">25</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1456425000000" role="button"
																		aria-controls="P1742604446">26</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1456511400000" role="button"
																		aria-controls="P1742604446">27</div></td>
															</tr>
															<tr>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1456597800000" role="button"
																		aria-controls="P1742604446">28</div></td>
																<td><div class="picker__day picker__day--infocus"
																		data-pick="1456684200000" role="button"
																		aria-controls="P1742604446">29</div></td>
																<td><div class="picker__day picker__day--outfocus"
																		data-pick="1456770600000" role="button"
																		aria-controls="P1742604446">1</div></td>
																<td><div class="picker__day picker__day--outfocus"
																		data-pick="1456857000000" role="button"
																		aria-controls="P1742604446">2</div></td>
																<td><div class="picker__day picker__day--outfocus"
																		data-pick="1456943400000" role="button"
																		aria-controls="P1742604446">3</div></td>
																<td><div class="picker__day picker__day--outfocus"
																		data-pick="1457029800000" role="button"
																		aria-controls="P1742604446">4</div></td>
																<td><div class="picker__day picker__day--outfocus"
																		data-pick="1457116200000" role="button"
																		aria-controls="P1742604446">5</div></td>
															</tr>
															<tr>
																<td><div class="picker__day picker__day--outfocus"
																		data-pick="1457202600000" role="button"
																		aria-controls="P1742604446">6</div></td>
																<td><div class="picker__day picker__day--outfocus"
																		data-pick="1457289000000" role="button"
																		aria-controls="P1742604446">7</div></td>
																<td><div class="picker__day picker__day--outfocus"
																		data-pick="1457375400000" role="button"
																		aria-controls="P1742604446">8</div></td>
																<td><div class="picker__day picker__day--outfocus"
																		data-pick="1457461800000" role="button"
																		aria-controls="P1742604446">9</div></td>
																<td><div class="picker__day picker__day--outfocus"
																		data-pick="1457548200000" role="button"
																		aria-controls="P1742604446">10</div></td>
																<td><div class="picker__day picker__day--outfocus"
																		data-pick="1457634600000" role="button"
																		aria-controls="P1742604446">11</div></td>
																<td><div class="picker__day picker__day--outfocus"
																		data-pick="1457721000000" role="button"
																		aria-controls="P1742604446">12</div></td>
															</tr>
														</tbody>
													</table>
													<div class="picker__footer">
														<button class="picker__button--today" type="button"
															data-pick="1454697000000" disabled="">Today</button>
														<button class="picker__button--clear" type="button"
															data-clear="1" disabled="">Clear</button>
													</div>
												</div>
											</div>
										</div>
										<div class="picker__backdrop">
											<input style="display: none">
										</div>
									</div>
								</div>
								<input type="hidden" name="_submit" id="P1742604446_submit">
							</div>
							<div class="fr mr20 mt10">
								<button ng-class="{&quot;active&quot;: todayDateActive}"
									ng-click="setDateToday()"
									class="btn btn-secondary btn-small active" tabindex="0">Today</button>
								<button ng-class="{&quot;active&quot;: tommorowDateActive}"
									ng-click="setDateTomorrow()"
									class="btn btn-secondary btn-small" tabindex="0">Tomorrow</button>
							</div>
							<div class="tic-error"></div>
						</li>
						<div class="line clear"></div>
						<li class="line"><label class="pos-in mt10">Seats
								Required</label>
							<div id="navcontainer">
								<ul>
									<!-- ngRepeat: n in [] | range:1:6 -->
									<li ng-repeat="n in [] | range:1:6"><a
										ng-click="setNumberVariables(n);"
										ng-class="{active : numberPassengers == $index + 1}"
										class="padded active" tabindex="0">1</a></li>
									<!-- end ngRepeat: n in [] | range:1:6 -->
									<li ng-repeat="n in [] | range:1:6"><a
										ng-click="setNumberVariables(n);"
										ng-class="{active : numberPassengers == $index + 1}"
										class="padded" tabindex="0">2</a></li>
									<!-- end ngRepeat: n in [] | range:1:6 -->
									<li ng-repeat="n in [] | range:1:6"><a
										ng-click="setNumberVariables(n);"
										ng-class="{active : numberPassengers == $index + 1}"
										class="padded" tabindex="0">3</a></li>
									<!-- end ngRepeat: n in [] | range:1:6 -->
									<li ng-repeat="n in [] | range:1:6"><a
										ng-click="setNumberVariables(n);"
										ng-class="{active : numberPassengers == $index + 1}"
										class="padded" tabindex="0">4</a></li>
									<!-- end ngRepeat: n in [] | range:1:6 -->
									<li ng-repeat="n in [] | range:1:6"><a
										ng-click="setNumberVariables(n);"
										ng-class="{active : numberPassengers == $index + 1}"
										class="padded" tabindex="0">5</a></li>
									<!-- end ngRepeat: n in [] | range:1:6 -->
								</ul>
							</div></li>
						
					</ul>
					<div class="mt20">
						<button data-style="fill" data-horizontal=""
							ng-click="searchBuses()"
							class="proceedButton btn-4 btn-4c icon-arrow-right btn btn-primary btn-lg btn-block width320 proceedButtonAnimate"
							tabindex="0">
							Search Buses<span class="progress"><span
								ng-style="{width : ( widthProgress + '%' ) }"
								class="progress-inner"></span></span>
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>