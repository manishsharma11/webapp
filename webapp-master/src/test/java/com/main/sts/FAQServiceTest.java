package com.main.sts;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.main.sts.entities.FAQ;
import com.main.sts.service.FAQService;

public class FAQServiceTest extends BaseTest {

    @Resource
    private FAQService faqService;

    @Test
    public void testInsertFAQ() {
        FAQ faq = new FAQ();
        faq.setQuestion("What is EasyCommute");
        faq.setAnswer("EasyCommute is an on-demand Mobile based Bus Shuttle Service");
        boolean status = faqService.insertFaq(faq);
        Assert.assertEquals(true, status);
        for (FAQ c : faqService.findAll()) {
            System.out.println(c.getId() + "--" + c.getQuestion() + "--" + c.getAnswer());
        }
    }

    @Test
    public void testUpdateFAQ() {
        String question = "What is EasyCommute Service";
        String answer = "EasyCommute is an on-demand Mobile based Bus Shuttle Service";
        int faq_id = 10;
        boolean status = faqService.updateAFAQ(faq_id, question, answer);
        Assert.assertEquals(true, status);
        for (FAQ c : faqService.findAll()) {
            System.out.println(c.getId() + "--" + c.getQuestion() + "--" + c.getAnswer());
        }
    }

    @Test
    public void testDeleteFAQ() {
        int faq_id = 400;
        boolean status = faqService.deleteFAQ(faq_id);
        Assert.assertEquals(true, status);
        for (FAQ c : faqService.findAll()) {
            System.out.println(c.getId() + "--" + c.getQuestion() + "--" + c.getAnswer());
        }
    }

    @Test
    public void testFindAll() {
        Assert.assertFalse(faqService.findAll().isEmpty());
        for (FAQ c : faqService.findAll()) {
            System.out.println(c.getId() + "--" + c.getQuestion() + "--" + c.getAnswer());
        }
    }

}
