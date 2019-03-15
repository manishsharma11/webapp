package com.main.sts.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.internal.util.compare.ComparableComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.main.sts.controllers.RoutesController;
import com.main.sts.dao.sql.RouteStopsDao;
import com.main.sts.dto.RouteStopsDTO;
import com.main.sts.dto.StopDTO;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Routes;
import com.main.sts.entities.Stops;

@Service
public class RouteStopsService {

	private static final Logger logger = Logger.getLogger(RouteStopsService.class);

	@Autowired
	private RouteStopsDao routeStopsDao;
	@Autowired
	private RouteService routeService;
	@Autowired
	private StopsService stopsService;

	
	public String addRouteStop(HttpServletRequest request) {

		RouteStops routeStop = new RouteStops();
		routeStop.setRoute_id(Integer.parseInt(request.getParameter("route_id")));
		routeStop.setStop_id(Integer.parseInt(request.getParameter("stop_id")));
		routeStop.setStop_time(request.getParameter("stop_time"));
		routeStop.setStop_number(Integer.parseInt(request.getParameter("stop_number")));

		String ret = null;
		try {
			String result = compareTimes(routeStop);
			if (result.equals("ok")) {
				int count = routeStopsDao.getRouteStops(routeStop.getRoute_id()).size();
				if (count != 0)
					routeStopsDao.incrementStopNumbers(routeStop.getRoute_id(), routeStop.getStop_number());
				routeStopsDao.insertRouteStop(routeStop);
				Routes route = routeService.getRoute(routeStop.getRoute_id());
				Stops stop = stopsService.getStop(Integer.parseInt(request.getParameter("stop_id")));
				int k=Integer.parseInt(stop.getIsAssigned());
				k++;
				stop.setIsAssigned(String.valueOf(k));
				stopsService.updateStop(stop);
				logger.info("Route Stop [ " + stop.getStop_name() + " ] added to route [ " + route.getRoute_name()
						+ " ]");
				ret = "ok";
			} else {

				ret = result;
			}

		} catch (ParseException e) {
			ret = "notok";
			e.printStackTrace();
		}
		return ret;
	}
	
    public List<RouteStops> getAllRouteStops(int route_id) {
        return routeStopsDao.getRouteStops(route_id);
    }

    public List<RouteStops> getAllRouteStops(int route_id, boolean onlyEnabledRouteStops) {
        return routeStopsDao.getRouteStops(route_id, onlyEnabledRouteStops);
    }

    public List<RouteStops> getAllRouteStops() {
        return routeStopsDao.getAllRouteStops();
    }

    public List<RouteStops> getAllEnabledRouteStops() {
        return routeStopsDao.getAllRouteStops(true);
    }

    public List<RouteStops> getAllRouteStopsForRoute(Collection<Integer> selectedRoutes) {
        return routeStopsDao.getAllRouteStopsForRoute(selectedRoutes);
    }

    public List<RouteStops> getAllRouteStopsForAStop(Integer stop_id, boolean orderedByStopsPosition) {
        return routeStopsDao.getAllRouteStopsForAStop(stop_id, orderedByStopsPosition);
    }

    public List<RouteStops> getAllEnabledRouteStopsForAStop(Integer stop_id) {
        return routeStopsDao.getAllEnabledRouteStopsForAStop(stop_id);
    }
    
    public RouteStops getRouteStopsForAStopId(int route_id, int stop_id) {
        return routeStopsDao.getRouteStopsForAStopId(route_id, stop_id);
    }

	public String compareTimes(RouteStops routeStop) throws ParseException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) == false) {
			System.out.println("inside if");
			return "redirect:/j_spring_security_logout";
		}

		String ret = null;
		/*
		 * RouteStopsEntity
		 * previousStop=routeStopsDaoImpl.getRouteStopByStopNumberAndRouteId
		 * (routeStopsEntity.getStop_number()-1,
		 * routeStopsEntity.getRoute_id()); RouteStopsEntity
		 * nextStop=routeStopsDaoImpl
		 * .getRouteStopByStopNumberAndRouteId(routeStopsEntity
		 * .getStop_number(), routeStopsEntity.getRoute_id());
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("HH:ss");
		int count = routeStopsDao.getRouteStops(routeStop.getRoute_id()).size();
		if (count == 0) {
			ret = "ok";
		} else if ((count + 1) == (routeStop.getStop_number())) {

			RouteStops previousStop = routeStopsDao.getRouteStop((int) count, routeStop.getRoute_id());
			Date current_time = sdf.parse(routeStop.getStop_time());
			Date preStop_time = sdf.parse(previousStop.getStop_time());
			if (current_time.equals(preStop_time) || current_time.before(preStop_time)) {
				ret = "code_1";
			} else {
				ret = "ok";
			}
		} else {
			// System.out.println(" stops not null");

			Date current_time = sdf.parse(routeStop.getStop_time());
			RouteStops previousStop = routeStopsDao.getRouteStop(routeStop.getStop_number() - 1,
					routeStop.getRoute_id());
			RouteStops nextStop = routeStopsDao.getRouteStop(routeStop.getStop_number(), routeStop.getRoute_id());
			if (previousStop == null) {
				Date nextStop_time = sdf.parse(nextStop.getStop_time());
				if (current_time.equals(nextStop_time) || current_time.after(nextStop_time)) {
					ret = "code_1";
				} else
					ret = "ok";
			} else if (nextStop == null) {
				Date previousStop_time = sdf.parse(previousStop.getStop_time());
				if (current_time.equals(previousStop_time) || current_time.before(previousStop_time)) {
					ret = "code_1";
				} else
					ret = "ok";
			} else {
				Date previousStop_time = sdf.parse(previousStop.getStop_time());
				Date nextStop_time = sdf.parse(nextStop.getStop_time());
				// System.out.println(previousStop);
				// System.out.println(nextStop);
				if (current_time.equals(previousStop_time) || current_time.before(previousStop_time)
						|| current_time.equals(nextStop_time) || current_time.after(nextStop_time)) {
					ret = "code_1";
				}

				else {
					ret = "ok";
				}
			}

		}
		return ret;
	}

	public String updateRouteStop(String data) throws ParseException {
		// System.out.println(data);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:ss");
		String arr[] = data.split("-");// 0-old num ,1-new num, 2-old time,3-new
										// time ,4-stop, id 5-route_id //
		int current_stop_number = Integer.parseInt(arr[0]);
		int new_stop_number = Integer.parseInt(arr[1]);
		String current_stop_time = arr[2];
		String new_stop_time = arr[3];
		// int stop_id = Integer.parseInt(arr[4]);
		int route_id = Integer.parseInt(arr[5]);

		String ret = null;
		int count = routeStopsDao.getRouteStops(route_id).size();
		if (current_stop_number == new_stop_number) {
			// update only time
			// System.out.println("update only time");
			RouteStops previousStop = routeStopsDao.getRouteStop(current_stop_number - 1, route_id);
			RouteStops nextStop = routeStopsDao.getRouteStop(current_stop_number + 1, route_id);
			Date current_time = sdf.parse(new_stop_time);
			if (previousStop == null) {
				Date next_time = sdf.parse(nextStop.getStop_time());
				if (current_time.equals(next_time) || current_time.after(next_time)) {
					ret = "code_1_1";
				} else {
					ret = "ok";
				}
			} else if (nextStop == null) {
				Date previous_time = sdf.parse(previousStop.getStop_time());
				if (current_time.equals(previous_time) || current_time.before(previous_time)) {
					ret = "code_1_1";
				} else {
					ret = "ok";
				}
			} else {
				Date next_time = sdf.parse(nextStop.getStop_time());
				Date previous_time = sdf.parse(previousStop.getStop_time());
				if (current_time.equals(previous_time) || current_time.before(previous_time)
						|| current_time.equals(next_time) || current_time.after(next_time)) {
					ret = "code_1_1";
				} else {
					ret = "ok";
				}
			}

			RouteStops routeStop = routeStopsDao.getRouteStop(current_stop_number, route_id);
			routeStop.setStop_time(new_stop_time);
			routeStopsDao.updateEntity(routeStop);
		} else if (new_stop_number == 1) {
			RouteStops firstStop = routeStopsDao.getRouteStop(1, route_id);
			Date current_time = sdf.parse(new_stop_time);
			Date first_stop_time = sdf.parse(firstStop.getStop_time());
			if (current_time.equals(first_stop_time) || current_time.after(first_stop_time)) {
				ret = "code_1_1";
			} else {
				/*
				 * firstStop.setStop_number(2);
				 * routeStopsDao.updateEntity(firstStop);
				 */
				RouteStops routeStop = routeStopsDao.getRouteStop(current_stop_number, route_id);
				routeStop.setStop_number(0);
				routeStop.setStop_time(new_stop_time);
				routeStopsDao.updateEntity(routeStop);
				routeStopsDao.decrementStopNumbers(route_id, current_stop_number);
				routeStopsDao.incrementStopNumbers(route_id, 0);
				routeStop.setStop_number(1);
				routeStopsDao.updateEntity(routeStop);
				ret = "ok";
			}
		} else if (new_stop_number == count) {
			RouteStops lastStop = routeStopsDao.getRouteStop(count, route_id);
			Date current_time = sdf.parse(new_stop_time);
			Date first_stop_time = sdf.parse(lastStop.getStop_time());
			if (current_time.equals(first_stop_time) || current_time.before(first_stop_time)) {
				ret = "code_1_1";
			} else {

				RouteStops routeStop = routeStopsDao.getRouteStop(current_stop_number, route_id);
				routeStop.setStop_number(0);
				routeStop.setStop_time(new_stop_time);
				routeStopsDao.updateEntity(routeStop);
				routeStopsDao.decrementStopNumbers(route_id, current_stop_number);
				// routeStopsDao.incrementStopNumbers(route_id, 0);
				routeStop.setStop_number(count);
				routeStopsDao.updateEntity(routeStop);
				ret = "ok";

			}
		} else {
			if (current_stop_number > new_stop_number) {

				// stop number decrement
				// System.out.println("Stop decrement");
				RouteStops previousStop = routeStopsDao.getRouteStop(new_stop_number - 1, route_id);
				RouteStops nextStop = routeStopsDao.getRouteStop(new_stop_number, route_id);
				Date previous_time = sdf.parse(previousStop.getStop_time());
				Date next_time = sdf.parse(nextStop.getStop_time());
				Date current_time = sdf.parse(new_stop_time);
				if (current_time.equals(previous_time) || current_time.before(previous_time)
						|| current_time.equals(next_time) || current_time.after(next_time)) {
					ret = "code_1_1";
				} else {
					RouteStops routeStop = routeStopsDao.getRouteStop(current_stop_number, route_id);
					routeStop.setStop_number(0);
					routeStop.setStop_time(new_stop_time);
					routeStopsDao.updateEntity(routeStop);
					routeStopsDao.decrementStopNumbers(route_id, current_stop_number);
					routeStopsDao.incrementStopNumbers(route_id, new_stop_number);
					routeStop.setStop_number(new_stop_number);
					routeStopsDao.updateEntity(routeStop);
					ret = "ok";
				}
			} else if (current_stop_number < new_stop_number) {
				// stop number increment
				// System.out.println("Stop increment");

				RouteStops previousStop = routeStopsDao.getRouteStop(new_stop_number, route_id);
				RouteStops nextStop = routeStopsDao.getRouteStop(new_stop_number + 1, route_id);
				Date previous_time = sdf.parse(previousStop.getStop_time());
				Date next_time = sdf.parse(nextStop.getStop_time());
				Date current_time = sdf.parse(new_stop_time);
				if (current_time.equals(previous_time) || current_time.before(previous_time)
						|| current_time.equals(next_time) || current_time.after(next_time)) {
					ret = "code_1_1";
				} else {
					RouteStops routeStop = routeStopsDao.getRouteStop(current_stop_number, route_id);
					routeStop.setStop_number(0);
					routeStop.setStop_time(new_stop_time);
					routeStopsDao.updateEntity(routeStop);
					routeStopsDao.decrementStopNumbersBelow(route_id, new_stop_number + 1);
					// routeStopsDao.incrementStopNumbers(route_id,
					// new_stop_number);
					routeStop.setStop_number(new_stop_number);
					routeStopsDao.updateEntity(routeStop);
					ret = "ok";
				}
			}

		}
		// System.out.println(ret);
		if (ret.equals("ok")) {
			RouteStops routeStop = routeStopsDao.getRouteStop(current_stop_number, route_id);
			Stops stop = routeStop.getStop();
			if (current_stop_number != new_stop_number)
				logger.info("Route Stop  [ " + stop.getStop_name() + " ] stop number updated from [ "
						+ current_stop_number + " ] to [ " + new_stop_number + " ] and time updated from [ "
						+ current_stop_time + " ] to [ " + new_stop_time + " ] ");
			else
				logger.info("Route Stop [ " + stop.getStop_name() + " ] time updated from [ " + current_stop_time
						+ " ] to [ " + new_stop_time + " ] ");
		}

		return ret;
	}

	public List<RouteStops> getStopsByRouteId(int id) {
		return routeStopsDao.getRouteStops(id);
	}

	public void removeRouteStop(HttpServletRequest request) {

		int id = Integer.parseInt(request.getParameter("id"));
		// System.out.println(id);
		// int stop_number =
		// Integer.parseInt(request.getParameter("stop_number"));
		int route_id = Integer.parseInt(request.getParameter("route_id"));
		Routes route = routeService.getRoute(route_id);
		RouteStops routeStop = routeStopsDao.getRouteStop(id);
		Stops stop=stopsService.getStop(routeStop.getStop_id());
		int k=Integer.parseInt(stop.getIsAssigned());
		k--;
		stop.setIsAssigned(String.valueOf(k));
		stopsService.updateStop(stop);
		// System.out.println(routeStop);
		routeStopsDao.decrementStopNumbers(routeStop.getRoute_id(), routeStop.getStop_number());
		routeStopsDao.removeRouteStop(routeStop);
		logger.info("Route Stop [ " + routeStop.getStop().getStop_name() + " ] removed from route [ "
				+ route.getRoute_name() + " ]");

	}

    public List<Stops> getStopDetails(int route_id) {
        return routeStopsDao.getStopDetails(route_id);
    }

    public RouteStops getRouteStop(int id) {
        return routeStopsDao.getRouteStop(id);
    }

    public RouteStops getRouteStopByRouteIdAndStopNo(int id, int stop_no) {
        return routeStopsDao.getRouteStop(stop_no, id);
    }

    public List<Stops> findAll() {
        return getAllStops();
    }

    public List<Stops> getAllStops() {
        return routeStopsDao.getAllStopsList();
    }

    public Set<RouteStops> getAllAvailableFromStops() {
        return getAllAvailableFromStops(true, true);
    }

    public Set<RouteStops> getAllAvailableFromStops(int route_id, Comparator<RouteStops> comparator) {
        // Getting all stops for those all Ids
        List<RouteStops> allRouteStops = getStopsByRouteId(route_id);

        if (allRouteStops == null) {
            throw new IllegalArgumentException("All route stops should not be null");
        }
        
        if (comparator != null) {
            // Sorting based on the stops position so JNTU is first he wil come
            // on top, like Ameerpet
            Collections.sort(allRouteStops, comparator);
        }
        
        // it will get all unique stop names
        Set<RouteStops> routeStops = new LinkedHashSet<RouteStops>();
        Set<Integer> stops = new HashSet<Integer>();
        for (RouteStops rs : allRouteStops) {
            //System.out.println("rs.getId():"+rs.getId());
            if (!stops.contains(rs.getStop_id())) {
                routeStops.add(rs);
                stops.add(rs.getStop_id());
            }
        }
        return routeStops;
    }
    
    public Set<RouteStops> getAllAvailableFromStopsSortedByPosition(int route_id) {
        return getAllAvailableFromStops(route_id, new StopNumberComparator());
    }
    
    public Set<RouteStops> getAllAvailableFromStops(int route_id) {
        return getAllAvailableFromStops(route_id, new StopNameAlphabeticComparator());
    }
    
    public Set<RouteStops> getAllAvailableFromStops(boolean onlyEnabledRoutes, boolean onlyEnabledStops) {
        // Getting only enabled routes
        List<Routes> routes = routeService.getAllRoutes(onlyEnabledRoutes);
        // Getting all Ids
        Set<Integer> routeIds = new HashSet<Integer>();
        for (Routes r : routes) {
            routeIds.add(r.getId());
        }
        
        //List<Integer> routeIdsList = new ArrayList<Integer>();
        //routeIdsList.addAll(routeIds);
        
        // Getting all stops for those all Ids
        List<RouteStops> allRouteStops = getAllRouteStopsForRoute(routeIds);

        if (allRouteStops == null) {
            throw new IllegalArgumentException("All route stops should not be null");
        }
//        // Sorting based on the stops position so JNTU is first he wil come on top, like Ameerpet
//        Collections.sort(allRouteStops, new Comparator<RouteStops>() {
//
//            @Override
//            public int compare(RouteStops o1, RouteStops o2) {
//                //return o1.getStop_number().compareTo(o2.getStop_number());
//                // stop numbers based sorting.
//                // return o1.getStop_number().compareTo(o2.getStop_number());
//                // ABC based sorting
//                if (o1.getStop() != null && o2.getStop() != null) {
//                    return o1.getStop().getStop_name().compareTo(o2.getStop().getStop_name());
//                }
//                return 0;
//            }
//        });
        // it will get all unique stop names
        Set<RouteStops> routeStops = new LinkedHashSet<RouteStops>();
        Set<Integer> stops = new HashSet<Integer>();
        for (RouteStops rs : allRouteStops) {
            //System.out.println("rs.getId():"+rs.getId());
//            // only pickup stops are selected
//            if (!rs.getStop().getType().equals("P")) {
//                continue;
//            }
            // Adding a check to filter out all those stops those are not enabled.
            if (onlyEnabledStops) {
                Stops stop = rs.getStop();
                if (stop == null) {
                    continue;
                }
                if (!stop.getEnabled()) {
                    continue;
                }
            }
            if (!stops.contains(rs.getStop_id())) {
                routeStops.add(rs);
                stops.add(rs.getStop_id());
            }
        }
        return routeStops;
    }
    
    public List<RouteStops> getAllAvailableToStops(int from_stop_id) {
        List<RouteStops> allRouteStops = getAllEnabledRouteStops();
        List<RouteStops> allRoutesStopForThisStop = getAllEnabledRouteStopsForAStop(from_stop_id);

//        // Sorting based on the stops position so JNTU is first he wil come on top, like Ameerpet
//        Collections.sort(allRouteStops, new Comparator<RouteStops>() {
//
//            @Override
//            public int compare(RouteStops o1, RouteStops o2) {
//                //return o1.getStop_number().compareTo(o2.getStop_number());
//                // stop numbers based sorting.
//                // return o1.getStop_number().compareTo(o2.getStop_number());
//                // ABC based sorting
//                if (o1.getStop() != null && o2.getStop() != null) {
//                    return o1.getStop().getStop_name().compareTo(o2.getStop().getStop_name());
//                }
//                return 0;
//            }
//        });
        
//        Set<Integer> routeIds = new HashSet<Integer>();
//
//        for (RouteStops routeStops : allRoutesStopForThisStop) {
//            routeIds.add(routeStops.getRoute_id());
//        }

        Set<RouteStops> finalStops = new LinkedHashSet<RouteStops>();
        Set<Integer> stops = new HashSet<Integer>();

        for (RouteStops thisStop : allRoutesStopForThisStop) {
            int current_stop_number = -1;
            if (thisStop.getStop_id() == from_stop_id) {
                current_stop_number = thisStop.getStop_number();
                for (RouteStops rs : allRouteStops) {
                    // System.out.println(thisStop.getRoute_id() +"--"+
                    // rs.getRoute_id() +"--"+(thisStop.getRoute_id().intValue()
                    // == rs.getRoute_id().intValue()) +"--"+rs.getStop_number()
                    // +"---"+ current_stop_number);
                    if (thisStop.getRoute_id().intValue() == rs.getRoute_id().intValue()
                            && rs.getStop_number() > current_stop_number) {
                        // System.out.println("rs.getStop_id()"+rs.getStop_id()+"-----"+thisStop.getStop_id());
                        if (!stops.contains(rs.getStop_id())) {
                            finalStops.add(rs);
                            stops.add(rs.getStop_id());
                        } else {
                            //System.out.println("Already exist");
                        }
                    }
                }
            }
        }
        
        List<RouteStops> finalStopsList = new ArrayList<RouteStops>();
        if (finalStops != null) {
            finalStopsList.addAll(finalStops);
        }

        // // Sorting based on the stops name
        Collections.sort(finalStopsList, new StopNameAlphabeticComparator());
        
        return finalStopsList;
    }
	
    public List<RouteStopsDTO> getAllAvailableRoutesWithStops(boolean onlyEnabledRoutes) {
        List<RouteStopsDTO> list = new ArrayList<RouteStopsDTO>();
        // Getting only enabled routes
        List<Routes> routes = routeService.getAllRoutes(onlyEnabledRoutes);
        
        Map<String, List<StopTimings>> routeIdWithStopsTimings = new HashMap<String,List<StopTimings>>();
        
        for (Routes r : routes) {
            List<RouteStops> routeStops = getAllRouteStops(r.getId(), true);
            Collections.sort(routeStops, new RouteStopsTimingsComparator());

            if (routeIdWithStopsTimings.containsKey(r.getDisplay_name())) {
                for (RouteStops rs : routeStops) {
                    List<StopTimings> ls = routeIdWithStopsTimings.get(r.getDisplay_name());
                    ls.add(new StopTimings(rs.getStop_time()));
                }
            }
            else {
                List<StopTimings> ls = new ArrayList<RouteStopsService.StopTimings>();
                for (RouteStops rs : routeStops) {
                    ls.add(new StopTimings(rs.getStop_time()));
                }
                routeIdWithStopsTimings.put(r.getDisplay_name(), ls);
            }
        }
        
        // sorting routes.
        //Collections.sort(routes, new RoutesTimingsComparator(routeIdWithStopsTimings));
        
        Set<String> routeAlreadyAdded = new HashSet<String>();
        // Iterating again
        for (Routes r : routes) {
            // to maintain uniqueness in Mobile App based on Display Name
            if(routeAlreadyAdded.contains(r.getDisplay_name())){
                continue;
            }
            routeAlreadyAdded.add(r.getDisplay_name());
            
            RouteStopsDTO rsDTO = new RouteStopsDTO();
            rsDTO.setRoute_id(r.getId());
            rsDTO.setRoute_name(r.getDisplay_name());
            //rsDTO.setTimings("8am - 11.30am, 15.30pm - 21.30pm");
            List<StopTimings> stopTimings = routeIdWithStopsTimings.get(r.getDisplay_name());
            rsDTO.setTimings(prepareTimeRangeString(stopTimings));
            
            if (stopTimings != null && stopTimings.size() > 0) {
                StopTimings firstStop = stopTimings.get(0);
                rsDTO.setRouteStartHour(firstStop.getTimeInHour());
                rsDTO.setRouteStartMin(firstStop.getTimeInMin());
            }
            
            List<RouteStops> routeStops = getAllRouteStops(r.getId(), true);
            Collections.sort(routeStops, new Comparator<RouteStops>() {

                @Override
                public int compare(RouteStops o1, RouteStops o2) {
                    return o1.getStop_number().compareTo(o2.getStop_number());
                }
            });
            
            List<StopDTO> stops = new ArrayList<StopDTO>();
            int totalStops = routeStops.size();
            
            StopTimings currentStopTiming = null;
            StopTimings firstStopTiming = null;
            for (RouteStops rs : routeStops) {
                // RouteStops rs = routeStops.get(0);
                StopDTO stop = new StopDTO();
                stop.setRoute_id(r.getId());
                stop.setStop_name(rs.getStop().getStop_name());
                stop.setStop_position(rs.getStop_number());
                stop.setLatitude(rs.getStop().getLatitude());
                stop.setLongitude(rs.getStop().getLongitude());
                stop.setStop_time(rs.getStop_time());
                stop.setGeofence(rs.getStop().getGeofence());
                stop.setStop_id(rs.getStop_id());
                
                currentStopTiming = new StopTimings(rs.getStop_time());

                String time_duration = null;
                if (firstStopTiming != null) {
                    // compare time duration between previous and current one.
                    Date d1  = new Date();
                    d1.setHours(firstStopTiming.timeInHour);
                    d1.setMinutes(firstStopTiming.timeInMin);
                    
                    Date d2= new Date();
                    d2.setHours(currentStopTiming.timeInHour);
                    d2.setMinutes(currentStopTiming.timeInMin);
                    
                    time_duration = getTimeDifference(d1, d2);
                } else {
                    time_duration = "0 min";
                    // assigning current to previous one.
                    firstStopTiming = currentStopTiming;
                }

                stop.setTime_duration(time_duration);
                
                if (rs.getStop_number() == 1) {
                    stop.setStart_stop(true);
                } else {
                    stop.setStart_stop(false);
                }

                if (rs.getStop_number() == totalStops) {
                    stop.setEnd_stop(true);
                } else {
                    stop.setEnd_stop(false);
                }
                stops.add(stop);

            }
            rsDTO.setStops(stops);
            
            list.add(rsDTO);
        }
        
        Collections.sort(list, new RoutesTimingComparator());
        return list;
    }
    
    
    public String getTimeDifference(Date d1, Date d2) {
        try {
            // in milliseconds
            long diff = d2.getTime() - d1.getTime();

            //long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            //long diffDays = diff / (24 * 60 * 60 * 1000);

            String diffStr = null;
            if (diffHours < 1) {
                if (diffMinutes == 1) {
                    diffStr = diffMinutes + " min";
                } else {
                    diffStr = diffMinutes + " mins";
                }
            } else {
                if (diffMinutes == 0) {
                    diffStr = diffHours + " hours ";
                } else {
                    if (diffHours == 1) {
                        diffStr = diffHours + " hour, " + diffMinutes + " mins";
                    } else {
                        diffStr = diffHours + " hours, " + diffMinutes + " mins";
                    }
                }
            }
            return diffStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public String prepareTimeRangeString(List<StopTimings> stopsTimingsList) {
        String morningFirstStopTiming = null;
        String morningLastStopTiming = null;
        String eveningFirstStopTiming = null;
        String eveningLastStopTiming = null;

        //Collections.sort(stopsTimingsList, new RouteStopsTimingsComparator());
        
        // StopTimings are in order ascending, from morning to evening.
        for (StopTimings st : stopsTimingsList) {
            int inHour = st.getTimeInHour();
            int inMin = st.getTimeInMin();

            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR, inHour);
            c.set(Calendar.MINUTE, inMin);

            if (inMin == 0) {
                inMin = 00;
            }
            // the very first entry
            if (inHour < 12 && morningFirstStopTiming == null) {
                morningFirstStopTiming = inHour + ":" + inMin + "am";
            }
            // 3pm - 15.00 // the last entry so every time in for loop this
            // variable will be updated.
            if (inHour < 15) {
                // less than 12am, min can be anything but will be like 11.59
                if (inHour <12) {
                    morningLastStopTiming = inHour + ":" + inMin + "am";
                } else {
                    morningLastStopTiming = inHour + ":" + inMin + "pm";
                }
            }

            // only 1 time this variable will be updated, first time after 15
            if (inHour > 15 && eveningFirstStopTiming == null) {
                eveningFirstStopTiming = inHour + ":" + inMin + "pm";
            }

            if (inHour > 15) {
                // the last entry, so for every entry in for loop, this will
                // be updated.
                eveningLastStopTiming = inHour + ":" + inMin + "pm";
            }
        }

        String morningTimeRange = null;
        String eveningTimeRange = null;

        if (morningLastStopTiming != null) {
            morningTimeRange = morningFirstStopTiming + " to " + morningLastStopTiming;
        } else {
            morningTimeRange = morningFirstStopTiming;
        }
        
        if (eveningLastStopTiming != null) {
            eveningTimeRange = eveningFirstStopTiming + " to " + eveningLastStopTiming;
        } else {
            eveningTimeRange = eveningFirstStopTiming;
        }

        String timeRange = null;
        if (morningTimeRange != null && eveningTimeRange != null) {
            timeRange = morningTimeRange + " - " + eveningTimeRange;
        } else if (morningTimeRange != null) {
            timeRange = morningTimeRange;
        } else {
            timeRange = eveningTimeRange;
        }
        return timeRange;
    }
    
    public static class StopTimings {

        int timeInHour;
        int timeInMin;

        public StopTimings(String stop_time) {
            String[] timings1 = stop_time.split(":");
            timeInHour = Integer.parseInt(timings1[0]);
            timeInMin = Integer.parseInt(timings1[1]);
        }

        public int getTimeInHour() {
            return timeInHour;
        }

        public int getTimeInMin() {
            return timeInMin;
        }
    }
    
    public static class RouteStopsTimingsComparator implements Comparator<RouteStops> {

        @Override
        public int compare(RouteStops o1, RouteStops o2) {
            String[] timings1 = o1.getStop_time().split(":");
            String[] timings2 = o2.getStop_time().split(":");

            // comparing hours value first., if they are same (means
            // compare value is zero, then checking for mins)
            int res = ((Integer) Integer.parseInt(timings1[0])).compareTo((Integer) Integer.parseInt(timings2[0]));
            // if hours are same.
            if (res == 0) {
                res = ((Integer) Integer.parseInt(timings1[1])).compareTo((Integer) Integer.parseInt(timings2[1]));
            }
            return res;
        }
    }
    
    public static class RoutesTimingsComparator implements Comparator<Routes> {

        Map<Integer, List<StopTimings>> routeIdWithStopsTimings;
        
        public RoutesTimingsComparator(Map<Integer, List<StopTimings>> routeIdWithStopsTimings) {
           this.routeIdWithStopsTimings = routeIdWithStopsTimings;
        }
        
        @Override
        public int compare(Routes o1, Routes o2) {
            List<StopTimings> timings1 = routeIdWithStopsTimings.get(o1.getId());
            List<StopTimings> timings2 = routeIdWithStopsTimings.get(o2.getId());

            StopTimings st1 = timings1.get(0);
            StopTimings st2 = timings2.get(0);

            // comparing hours value first., if they are same (means
            // compare value is zero, then checking for mins)
            int res = ((Integer) st1.getTimeInHour()).compareTo((Integer) st2.getTimeInHour());
            // if hours are same.
            if (res == 0) {
                res = ((Integer) st1.getTimeInMin()).compareTo((Integer) st2.getTimeInMin());
            }
            return res;
        }
    }
    
    public static class RoutesTimingComparator implements Comparator<RouteStopsDTO> {

        @Override
        public int compare(RouteStopsDTO r1, RouteStopsDTO r2) {
            // comparing hours value first., if they are same (means
            // compare value is zero, then checking for mins)
            int res = ((Integer) r1.getRouteStartHour()).compareTo((Integer) r2.getRouteStartHour());
            // if hours are same.
            if (res == 0) {
                res = ((Integer) r1.getRouteStartMin()).compareTo((Integer) r2.getRouteStartMin());
            }
            return res;
        }
    }

    
    public static class StopNameAlphabeticComparator implements Comparator<RouteStops> {

        @Override
        public int compare(RouteStops o1, RouteStops o2) {
            // ABC based sorting
            //return o1.getStop().getStop_name().compareTo(o2.getStop().getStop_name());
            if (o1.getStop() != null && o2.getStop() != null) {
                return o1.getStop().getStop_name().compareTo(o2.getStop().getStop_name());
            }
            return 0;
        }
    }
    
    public static class StopNumberComparator implements Comparator<RouteStops> {

        @Override
        public int compare(RouteStops o1, RouteStops o2) {
            // stop numbers based sorting.
            if (o1 != null && o2 != null) {
                return o1.getStop_number().compareTo(o2.getStop_number());
            }
            return 0;
        }
    }
}
