package com.main.sts.service;

import java.util.List;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.common.CommonConstants;
import com.main.sts.common.CommonConstants.TransactionStatus;
import com.main.sts.common.CommonConstants.UserActiveType;
import com.main.sts.common.CommonConstants.UserStatusType;
import com.main.sts.dao.sql.CommuterDao;
import com.main.sts.dto.CommuterDTO;
import com.main.sts.dto.EmailDTO;
import com.main.sts.dto.EmailResponse;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.PushMessage;
import com.main.sts.entities.ReferralCode;
import com.main.sts.entities.ReferralTransactionInfo;
import com.main.sts.entities.SMSCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.RouteDao;
import com.main.sts.dao.sql.SuggestedRoutesDao;
import com.main.sts.entities.Routes;
import com.main.sts.entities.SuggestRoute;
import com.main.sts.entities.SuggestRouteWebDTO;

@Service
public class RouteService {

    @Autowired
    private RouteDao routesDao;
    
    public List<Routes> getAllRoutes() {
        return routesDao.getRoutes();
    }

    public List<Routes> getAllRoutes(boolean onlyEnabledRoutes) {
        return routesDao.getRoutes(onlyEnabledRoutes);
    }

    public void insertRoute(Routes route) {
        route.setIsAssigned("0");
        routesDao.insert(route);
    }

    public Routes getRoute(int id) {
        return routesDao.getRoute(id);
    }

    public void deleteRoute(int id) {
        Routes route = getRoute(id);
        routesDao.deleteRoute(route);
    }

    public Routes getRouteName(String name) {
        return routesDao.getRouteByName(name);
    }

    public void updateRoute(int id, Routes routeentity) {
        Routes route = getRoute(id);
        route.setIsAssigned(routeentity.getIsAssigned());
        route.setRoute_name(routeentity.getRoute_name());
        route.setDisplay_name(routeentity.getDisplay_name());
        route.setRoute_type(routeentity.getRoute_type());
        route.setEnabled(routeentity.getEnabled());
        routesDao.updateRoute(route);
    }

    public void updateStatus(int id, String isAssigned) {
        Routes route = getRoute(id);
        route.setIsAssigned(isAssigned);
        routesDao.updateRoute(route);
    }

    public List<Routes> getRouteByType(String name) {
        return routesDao.getRouteByType(name);
    }

    public List<Routes> searchRoutes(String type, String str) {
        return routesDao.searchRoutes(str, type);
    }

    public boolean suggestARoute(SuggestRoute suggestedRoute) {
        return this.routesDao.addASuggestedRoute(suggestedRoute);
    }
    
}
