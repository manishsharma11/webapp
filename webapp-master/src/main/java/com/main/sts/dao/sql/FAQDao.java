package com.main.sts.dao.sql;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.main.sts.entities.FAQ;

@Repository
public class FAQDao extends BaseDao {

    public List<FAQ> findAll() {
        Order id = Order.asc("id");
        Order priority = Order.asc("priority");
        return getAllRecords(FAQ.class, priority, id);
    }

    public boolean insertAFAQ(FAQ faq) {
        return insertEntity(faq);
    }

    public boolean updateAFAQ(FAQ faq) {
        return updateEntity(faq);
    }

    public boolean deleteFAQ(FAQ faq) {
        return deleteEntity(faq);
    }
}
