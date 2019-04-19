package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyDetailsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyDetailsConfig companyDetailsConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("preview", "New Card!");
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("goodbye", "Have a nice day!");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", companyDetailsConfig.getCompanyName());
        context.setVariable("company_email", companyDetailsConfig.getCompanyEmail());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
