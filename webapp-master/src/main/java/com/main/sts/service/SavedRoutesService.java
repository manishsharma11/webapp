package com.main.sts.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.SavedRoutesDao;
import com.main.sts.dto.SavedRouteDTO;
import com.main.sts.dto.SavedRouteResponse;
import com.main.sts.entities.SavedRoutes;
import com.main.sts.entities.Stops;

@Service("savedRoutesService")
public class SavedRoutesService {

    @Autowired
    private SavedRoutesDao savedRoutesDao;

    @Autowired
    private StopsService stopsService;

    public List<SavedRoutes> getAllSavedRoutes() {
        return savedRoutesDao.getAllSavedRoutes();
    }

    public List<SavedRoutes> getAllSavedRoutes(int commuter_id) {
        List<SavedRoutes> ls = savedRoutesDao.getAllSavedRoutes(commuter_id);
        return ls;
    }

    public List<SavedRouteResponse> getAllSavedRoutesResponse(int commuter_id) {
        List<SavedRoutes> ls = getAllSavedRoutes(commuter_id);
        List<SavedRouteResponse> respList = new ArrayList<SavedRouteResponse>();
        
        for (SavedRoutes sr : ls) {
            SavedRouteResponse resp = new SavedRouteResponse();
            resp.setCommuter_id(sr.getCommuter_id());
            resp.setFrom_stop_id(sr.getFrom_stop_id());
            resp.setTo_stop_id(sr.getTo_stop_id());
            resp.setPickup_time_hours(sr.getPickup_time_hours());
            resp.setPickup_time_min(sr.getPickup_time_min());
            resp.setDropoff_time_hours(sr.getDropoff_time_hours());
            resp.setDropoff_time_min(sr.getDropoff_time_min());

            Stops from_stop = stopsService.getStop(sr.getFrom_stop_id());
            Stops to_stop = stopsService.getStop(sr.getTo_stop_id());

            resp.setFrom_stop_name(from_stop.getDisplay_name());
            resp.setTo_stop_name(to_stop.getDisplay_name());

            resp.setFrom_stop_shortcode(from_stop.getShortcode());
            resp.setTo_stop_shortcode(to_stop.getShortcode());
            respList.add(resp);
        }
        return respList;
    }

    public ReturnCodes saveARoute(int commuter_id, int from_stop_id, int to_stop_id, Integer pickup_time_hours,
            Integer pickup_time_min, Integer dropoff_time_hours, Integer dropoff_time_min) {

        SavedRoutes existingSR = savedRoutesDao.getSavedRouteForACommuter(commuter_id, from_stop_id, to_stop_id,
                pickup_time_hours, pickup_time_min, dropoff_time_hours, dropoff_time_min);
       System.out.println(existingSR);
        if (existingSR == null) {
            SavedRoutes sdr = new SavedRoutes();
            sdr.setCommuter_id(commuter_id);
            sdr.setFrom_stop_id(from_stop_id);
            sdr.setTo_stop_id(to_stop_id);
            sdr.setPickup_time_hours(pickup_time_hours);
            sdr.setPickup_time_min(pickup_time_min);
            sdr.setDropoff_time_hours(dropoff_time_hours);
            sdr.setDropoff_time_min(dropoff_time_min);

            boolean result = savedRoutesDao.saveARoute(sdr);
            if (result) {
                return ReturnCodes.SAVE_ROUTE_SUCCESS;
            } else {
                return ReturnCodes.SAVE_ROUTE_FAILED;
            }
        } else {
            return ReturnCodes.SAVE_ROUTE_ALREADY_EXISTS;
        }
    }

    public ReturnCodes saveARoute(SavedRouteDTO srReq) throws ServiceException {
        return saveARoute(srReq.getCommuter_id(), srReq.getFrom_stop_id(), srReq.getTo_stop_id(),
                srReq.getPickup_time_hours(), srReq.getPickup_time_min(), srReq.getDropoff_time_hours(),
                srReq.getDropoff_time_min());
    }

}
