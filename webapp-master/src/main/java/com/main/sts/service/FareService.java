package com.main.sts.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.FareDao;
import com.main.sts.dao.sql.StopsDao;
import com.main.sts.entities.BusFarePriceEntity;
import com.main.sts.entities.Stops;

@Service("fareService")
public class FareService {

    @Autowired
    private FareDao fareDao;
    
    @Autowired
    private StopsDao stopsDao;

    public List<BusFarePriceEntity> findAll() {
        return fareDao.findAll();
    }

    private BusFarePriceEntity fetchToFare(int source_stop_id, int dest_stop_id) {
        return fareDao.fetchFare(source_stop_id, dest_stop_id);
    }

    public BusFarePriceEntity fetchFare(int source_stop_id, int dest_stop_id) {
        BusFarePriceEntity entity = this.fetchToFare(source_stop_id, dest_stop_id);
        // may be we have defined only one type of fare. i mean from JNTU to
        // Mindspace and Mindspace to JNTU would be same.
        // Incase they are different, then one entry should be there for each
        // route. and in that case entity will not be null.
        if (entity == null) {
            entity = this.fetchToFare(dest_stop_id, source_stop_id);
        }
        // Again trying to find through the sibling_stop_id
        if (entity == null) {
            Stops sourceStop = stopsDao.getStop(source_stop_id);
            Stops destStop = stopsDao.getStop(dest_stop_id);
            
            // if source stop is 'D' dropoff then taking its sibling and then trying.
            if (sourceStop != null && sourceStop.getType()!=null && sourceStop.getType().equals("D")) {
                int src_id = sourceStop.getSibling_stop_id();
                entity = this.fetchToFare(dest_stop_id, src_id);
            }
            
            if (entity == null && destStop != null && destStop.getType() != null && destStop.getType().equals("D")) {
                int dest_id = destStop.getSibling_stop_id();
                entity = this.fetchToFare(source_stop_id, dest_id);
            }
        }
        return entity;
    }
    
    public void insertFare(BusFarePriceEntity e) {
        if (e.getSource_stop_id() == e.getDest_stop_id()) {
            throw new IllegalArgumentException("Source and dest stops cant be same");
        }
        fareDao.insertFare(e);
    }

    public void updateFare(BusFarePriceEntity e) {
        fareDao.updateFare(e);
    }

    public void deleteFare(BusFarePriceEntity e) {
        fareDao.deleteFare(e);
    }

    public void deleteFare(int fare_id) {
        BusFarePriceEntity e = fareDao.getFareById(fare_id);
        fareDao.deleteFare(e);
    }

}
