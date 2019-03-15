package com.main.sts.service;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.SuggestedRoutesDao;
import com.main.sts.entities.SuggestRoute;

@Service("suggestService")
public class SuggestRoutesService {

    @Autowired
    private SuggestedRoutesDao suggestedRoutesDao;

    public List<SuggestRoute> getAllSuggestedRoutes() {
        List<SuggestRoute> suggestedRoutes = suggestedRoutesDao.getAllSuggestRoutes();
        return suggestedRoutes;
    }

    public BigInteger getSuggestRouteCount() {
        return suggestedRoutesDao.getSuggestRouteCount();
    }

    public List<SuggestRoute> getRecordsByPagination(Integer offset, Integer limit) {
        return suggestedRoutesDao.getRecordsByPagination(SuggestRoute.class, null, Order.desc("id"),offset, limit);
    }
    
    public List<SuggestRoute> searchSuggestedRoutes(String type, String str) {
        return suggestedRoutesDao.searchSuggestedRoutes(str, type);
    }

}