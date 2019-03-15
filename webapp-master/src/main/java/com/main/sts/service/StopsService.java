package com.main.sts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.StopsDao;
import com.main.sts.dto.RouteStopsDTO;
import com.main.sts.dto.StopsDTO;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.StopType;
import com.main.sts.entities.Stops;

@Service
public class StopsService {

	@Autowired
	private StopsDao stopsdao;

	@Autowired
	private RouteStopsService routeStopsSerice;
	
	@Autowired
	private StopTypesService stopTypesService;

	public List<Stops> getAllStops() {
		return stopsdao.getStops();
	}

	public void insertStop(Stops stop) {
		stop.setIsAssigned("0");
		stopsdao.insert(stop);
	}

	public Stops getStop(int id) {
		return stopsdao.getStop(id);
	}

	public void deleteStop(int id) {
		Stops stop = getStop(id);
		stopsdao.deleteStop(stop);
	}

	public void deleteStop(Stops stop) {
		stopsdao.deleteStop(stop);
	}

	public Stops getStopName(String name) {
		return stopsdao.getStopByName(name);
	}

    public void updateStop(int id, Stops s) {
        Stops stop = getStop(id);
        stop.setGeofence(s.getGeofence());
        stop.setIsAssigned(s.getIsAssigned());
        stop.setLatitude(s.getLatitude());
        stop.setLongitude(s.getLongitude());
        stop.setStop_name(s.getStop_name());
        stop.setShortcode(s.getShortcode());;
        stop.setHelp_text(s.getHelp_text());;
        stop.setImage_path(s.getImage_path());;
        stop.setDisplay_name(s.getDisplay_name());;
        stop.setEnabled(s.getEnabled());
        stop.setType(s.getType());
        stop.setTags(s.getTags());
        stop.setStop_type(s.getStop_type());
        stopsdao.updateStop(stop);
    }

	public void updateStop(Stops stop) {
		stopsdao.updateStop(stop);
	}

	public Stops getStop(String latitude, String longitude) {
		return stopsdao.getStop(latitude, longitude);
	}

	public List<Stops> searchStops(String type, String str) {
		return stopsdao.searchStops(str, type);
	}

	
//    public List<Stops> getAllAvailableFromStops() {
//        return routeStopsSerice.getAllAvailableFromStops();
//    }

    public Set<RouteStops> getAllAvailableFromStops() {
        return routeStopsSerice.getAllAvailableFromStops();
    }

    public List<RouteStops> getAllAvailableToStops(int from_stop_id) {
        return routeStopsSerice.getAllAvailableToStops(from_stop_id);
    }
    
    public List<RouteStopsDTO> getAllAvailableRoutesWithStops() {
        List<RouteStopsDTO> routeStops = routeStopsSerice.getAllAvailableRoutesWithStops(true);
        return routeStops;
    }
    
    public StopsDTO toStopsDTO(RouteStops rs) {
        
        List<StopType> stopTypes = stopTypesService.getAllStopTypes();
        Map<Integer, StopType> stopTypesMap = new HashMap<Integer, StopType>();
        for (StopType stopType : stopTypes) {
            stopTypesMap.put(stopType.getId(), stopType);
        }
        
        StopsDTO stop = new StopsDTO();
        stop.route_stop_id = rs.getId();
        stop.stop_id = rs.stop.getId();
        stop.stop_name = rs.stop.getDisplay_name();// rs.stop.getStop_name();
        stop.latitude = rs.stop.getLatitude();
        stop.longitude = rs.stop.getLongitude();
        stop.shortcode = rs.stop.getShortcode();
        stop.help_text = rs.stop.getHelp_text();
        //stop.image_icon_path = rs.stop.getImage_icon_path();
        stop.image_path = rs.stop.getImage_path();
        stop.tags = rs.stop.getTagsArr();
        stop.stop_icon_enabled = rs.stop.getStop_icon_enabled();
        
        StopType st = stopTypesMap.get(rs.stop.getStop_type());
        if (st != null) {
            stop.stop_type_name = st.getStop_type_name();
            stop.stop_icon_path = st.getStop_icon_path();
        }

        return stop;
    }

}
