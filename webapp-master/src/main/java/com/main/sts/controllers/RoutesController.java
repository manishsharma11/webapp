package com.main.sts.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Routes;
import com.main.sts.entities.Stops;
import com.main.sts.service.RouteService;
import com.main.sts.service.RouteStopsService;
import com.main.sts.service.StopsService;
import com.main.sts.util.RolesUtility;

@Controller
@RequestMapping(value = "/school_admin/route")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST','ROLE_CUSTOMER_SUPPORT','ROLE_OPERATOR')")
public class RoutesController {

	
	@Autowired
	private RolesUtility rolesUtility;
	@Autowired
	private RouteStopsService routeStopsService;
	@Autowired
	private RouteService routeservice;
	@Autowired
	private StopsService stopsService;
	private static final Logger logger=Logger.getLogger(Routes.class);
	
	@RequestMapping(value = "/routes", method = RequestMethod.GET)
	public ModelAndView routesHomePage(ModelAndView model,	HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated())==false)
		{						
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/route/routes");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		//System.out.println("inside routesController");

		model.addObject("date", formatter.format(new Date()));
		List<Routes> routes=routeservice.getAllRoutes();
		Collections.sort(routes,new Routes());
		
		model.addObject("routes", routes);
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "routes_list");
		
		System.out.println("rolesUtility.getRole(request):"+rolesUtility.getRole(request));
		model.addObject("login_role",rolesUtility.getRole(request));
		
		return model;
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView seniorHomePage(ModelAndView model,	HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated())==false)
		{						
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/route/addroute");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		model.addObject("date", formatter.format(new Date()));
		
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "routes_list");
		
		model.addObject("login_role",rolesUtility.getRole(request));
		
		return model;
	}
	
	@RequestMapping(value = "/addrouteaction", method = RequestMethod.POST)
	public ModelAndView addRouteAction(ModelAndView model,	HttpServletRequest request,@ModelAttribute Routes routesEntity) {
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated())==false)
		{						
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		
		String route_name=request.getParameter("route_name");
	    String route_display_name=request.getParameter("display_name");

		String route_type=request.getParameter("route_type");
		//System.out.println();
		//routesEntity.setRoute_name(route_name);
		//routesEntity.setRoute_type(route_type);
		
		//validation for duplicate route name
		
		Routes re= routeservice.getRouteName(route_name);
		if(re!=null)
		{
			//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			model.setViewName("/school_admin/route/addroute");
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			model.addObject("date", formatter.format(new Date()));
			model.addObject("login_name", auth.getName());
			model.addObject("current_page", "routes_list");
			model.addObject("route", routesEntity);
			
			model.addObject("error", "routeExistError");
			return model;
		}	
		//routesEntity.setIsAssigned("no");
		 routeservice.insertRoute(routesEntity);
		 logger.info("Route [ "+routesEntity.getRoute_name()+" ]got added");
		model.setViewName("redirect:/school_admin/route/routes");
		return model;
	}
	@RequestMapping(value = "/updateroute", method = RequestMethod.GET)
	public ModelAndView updateRoute(ModelAndView model,	HttpServletRequest request,HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated())==false)
		{						
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		
		int id=Integer.parseInt(request.getParameter("id"));
		////System.out.println("OldRouteId:"+id);
		session.setAttribute("routeIdInSession", id);
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/route/updateroute");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("route", routeservice.getRoute(id));
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "routes_list");
		//System.out.println("^^^^^^"+routeDaoImpl.getRouteById(id));
		
		model.addObject("login_role",rolesUtility.getRole(request));
		
		return model;
	}
	@RequestMapping(value = "/updaterouteaction", method = RequestMethod.POST)
	public ModelAndView updateRouteAction(ModelAndView model,HttpServletRequest request,HttpSession session,@ModelAttribute Routes routesEntity) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated())==false)
		{						
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		
		
		int routeId=(Integer)session.getAttribute("routeIdInSession");	
		String routeName=routesEntity.getRoute_name();
		 routeservice.updateRoute(routeId,routesEntity);
		 logger.info("Route [ "+routesEntity.getRoute_name()+" ]got updated");
		
		/*//if route update then this should impact on students 
		StudentEntity se1=studentDaoImpl.validateStudentByRouteIdFromHome(routeId);
		if(se1 !=null)
		{
			//System.out.println("from home student"+se1);
			studentDaoImpl.updateStudentRouteFromHome(routeId, routeName);
		}
		StudentEntity se2=studentDaoImpl.validateStudentByRouteFromSchool(routeId);
		if(se2 !=null)
		{
			//System.out.println("from school student"+se2);
			studentDaoImpl.updateStudentRouteFromSchool(routeId, routeName);
		}
		//student updatation over
		
		//if route update then this should impact on students 
		StaffEntity staff1=staffDaoImpl.validateStaffByRouteIdFromHome(routeId);
		if(staff1 !=null)
		{
			//System.out.println("from home staff"+staff1);
			staffDaoImpl.updateStaffRouteFromHome(routeId, routeName);
		}
		StaffEntity staff2=staffDaoImpl.validateStaffByRouteFromSchool(routeId);
		if(staff2 !=null)
		{
			//System.out.println("from school staff"+staff2);
			staffDaoImpl.updateStaffRouteFromSchool(routeId, routeName);
		}*/
		//student updatation over
					
		model.setViewName("redirect:/school_admin/route/routes");
		return model;
	}
	@RequestMapping(value = "/removeroute", method = RequestMethod.GET)
	public ModelAndView removeRoute(ModelAndView model,	HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated())==false)
		{						
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		
		
		int id=Integer.parseInt(request.getParameter("id"));
		HttpSession session=request.getSession();
		session.setAttribute("id", id);
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/route/removeroute");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("route", routeservice.getRoute(id));
		model.addObject("date", formatter.format(new Date()));
		
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "routes_list");
		
		model.addObject("login_role",rolesUtility.getRole(request));
		
		return model;
	}
	@RequestMapping(value = "/removerouteaction", method = RequestMethod.POST)
	public ModelAndView removerouteRouteAction(ModelAndView model,	HttpServletRequest request,HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated())==false)
		{						
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		
		
		int id=(Integer) session.getAttribute("id");
		Routes route=routeservice.getRoute(id);
		String route_name=route.getRoute_name();
		//List<RouteStopsEntity> routeStopsEntities=routeStopsDaoImpl.getStopsByRouteId(id);
		//int count=routeStopsEntities.size();
		//System.out.println(count);
		//if stops not assigned to route then only it should delete
		if(route.getIsAssigned().equalsIgnoreCase("0")){
		 routeservice.deleteRoute(id);
		 logger.info("Route [ "+route_name+" ]got Deleted");
		////System.out.println("can be deleted");
		}
		else
		{
			 logger.info("Route [ "+route_name+" ]cannot deleted");
		}
		//busStopsDaoImpl.deleteStopsWhenRouteDeleted(id);		
		model.setViewName("redirect:/school_admin/route/routes");
		
		model.addObject("login_role",rolesUtility.getRole(request));
		
		return model;
	}
	
	// search students starts
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody
	String searchBusByBusId(HttpServletRequest request, Model model,HttpSession session) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated())==false)
		{			
			System.out.println("inside if");			
			return "redirect:/j_spring_security_logout";
		}
		
		String search_route = request.getParameter("search_route");
		String searchOption = request.getParameter("searchOption");
		//System.out.println("inside controller" + search_route+"  "+searchOption);
		List<Routes> searchRoutes = null;
		if (searchOption.equals("route_name")) {
			searchRoutes= routeservice.searchRoutes(searchOption, search_route);
										
		} else {			
			searchRoutes= routeservice.searchRoutes(searchOption, search_route);			
		}
		session.setAttribute("searchRoutes", searchRoutes);
		return "/sts/school_admin/route/searchRoute";
	}

	@RequestMapping(value = "/searchRoute", method = RequestMethod.GET)
	public ModelAndView studentSearchResponse(ModelAndView model,
			HttpServletRequest request, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated())==false)
		{						
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		model.setViewName("/school_admin/route/routes");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "routes_list");			
		List<Routes> searchRouteList = (List<Routes>) session.getAttribute("searchRoutes");
		model.addObject("routes", searchRouteList);	
		
		if(searchRouteList.isEmpty())
		{
			model.addObject("routes", routeservice.getAllRoutes());
			model.addObject("error_message", "noMatching");
		}
		return model;				
	}
	// search students ends
	/*added by Mushtaq for 2.0_patch starts here*/
	@RequestMapping(value = "/route_stops", method = RequestMethod.GET)
	public ModelAndView routesDisplay(ModelAndView model,	HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated())==false)
		{						
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		
		
		Integer route_id=Integer.parseInt(request.getParameter("route_id"));
		
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.setViewName("/school_admin/route/route_stops");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		List<Stops> stops_allocated= stopsService.getAllStops();
		List<Stops> stops= new ArrayList<Stops>();
		List<RouteStops> routeStops=routeStopsService.getAllRouteStops(route_id);
		
		for( Stops stopEntity : stops_allocated){
			boolean found=false;
			for(RouteStops routeStopsEntity : routeStops){
				
				if(stopEntity.getId() == routeStopsEntity.getStop_id()){
					//System.out.println(stopEntity.getId());
					found=true;
					break;
				}
				
			}
			if(found==false){
				stops.add(stopEntity);
			}
		}
		Collections.sort(routeStops);
		Routes routes = routeservice.getRoute(route_id);
		model.addObject("route",routes);
		model.addObject("stops", stops);
		model.addObject("route_stops", routeStops);
		////System.out.println(busStopsDaoImpl.getAllStops());
		model.addObject("date", formatter.format(new Date()));
		model.addObject("login_name", auth.getName());
		model.addObject("current_page", "routes_list");
		//model.addObject("route", routeDaoImpl.getRouteById(route_id));
		////System.out.println(routeDaoImpl.getRouteById(route_id));
		
		if (request.isUserInRole("ROLE_ADMIN")) {
			model.addObject("login_role", "ROLE_ADMIN");
	    }
		return model;
	}
	/*added by Mushtaq for 2.0_patch ends here*/
	@RequestMapping(value = "/show_stop_map", method = RequestMethod.GET)
	public ModelAndView removeStop(ModelAndView model,	HttpServletRequest request) {
		//right,53f588a711fc926c6cac5087,Sunrisers Super Market,56.0,66.0,6,53eef3825c6d7999161b9fd4,53f181cfc63544ebacdfc753,2,14,19

		//System.out.println(request.getParameter("data"));
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated())==false)
		{						
			model.setViewName("redirect:/j_spring_security_logout");
			return model;
		}
		String data[]=request.getParameter("data").split(",");
		String stop_lat="",stop_long="",stop_number="",stop_name="";
		if(data[0].equals("right")){
			  
			
			if(data[3].contains("."))
			{
				stop_name=data[2];
			  stop_lat=data[3];
			  stop_long=data[4];
			  stop_number=data[8];
			}
			else if(data[4].contains("."))
			{
				stop_name=data[3];
				stop_lat=data[4];
				  stop_long=data[5];
				  stop_number=data[9];
			}
			  
			////System.out.println(data);
			model.addObject("stop_name", stop_name);
			model.addObject("stop_lat", stop_lat);
			model.addObject("stop_long", stop_long);
			model.addObject("stop_number", stop_number);
			model.setViewName("/school_admin/route/show_stop_map");	
		}
		if(data[0].equals("left")){
			  
			if(data[3].contains("."))
			{
				stop_name=data[2];
			  stop_lat=data[3];
			  stop_long=data[4];
			}
			else if(data[4].contains("."))
			{
				stop_name=data[3];
				stop_lat=data[4];
				  stop_long=data[5];
			}
			  stop_number="";
			////System.out.println(data);
			model.addObject("stop_name", stop_name);
			model.addObject("stop_lat", stop_lat);
			model.addObject("stop_long", stop_long);
			model.addObject("stop_number", stop_number);
			model.setViewName("/school_admin/route/show_stop_map");	
		}	
		
		
		return model;
	}
	
	@RequestMapping(value = "/removeAllRoutesByRouteIds", method = RequestMethod.POST)
	public @ResponseBody
	String removeAllRoutesByRouteIds(ModelAndView model,	HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if((auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated())==false)
		{			
			System.out.println("inside if");			
			return "redirect:/j_spring_security_logout";
		}
		
		
		//System.out.println("inside delete routes method");
		
		String routeIds=request.getParameter("routeIds");
		////System.out.println(routeIds);
		String routeIdsArray[]=routeIds.split(",");
		int totalItems=routeIdsArray.length;
		System.out.println(totalItems);
						
		for(int i=0;i<=totalItems-1;i++)
		{											
			List<RouteStops> routeStopsEntities=routeStopsService.getAllRouteStops(Integer.parseInt(routeIdsArray[i]));
			int count=routeStopsEntities.size();
			////System.out.println(count);
			//if stops not assigned to route then only it should delete
			if(count==0){
			//System.out.println("route to be deleted: "+routeIdsArray[i]);
			routeservice.deleteRoute(Integer.parseInt(routeIdsArray[i]));
			////System.out.println("can be deleted");
			}								
		}		
		//return null;	
		return "routes";
	}
}//class
