package com.main.sts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.FAQDao;
import com.main.sts.entities.BusFarePriceEntity;
import com.main.sts.entities.FAQ;

@Service("faqService")
public class FAQService {

    @Autowired
    private FAQDao faqDao;

    public List<FAQ> findAll() {
        return faqDao.findAll();
    }

    public FAQ getFAQ(int faq_id) {
        String query = "from FAQ b where b.id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, faq_id);
        return faqDao.getSingleRecord(FAQ.class, query, parameters);
    }

    public boolean insertFaq(FAQ faq) {
        return faqDao.insertAFAQ(faq);
    }

    public boolean updateAFAQ(int faq_id, String question, String answer) {
        FAQ faq = getFAQ(faq_id);
        faq.setQuestion(question);
        faq.setAnswer(answer);
        return faqDao.updateAFAQ(faq);
    }

    public boolean deleteFAQ(int faq_id) {
        FAQ faq = getFAQ(faq_id);
        return faqDao.deleteFAQ(faq);
    }
}
