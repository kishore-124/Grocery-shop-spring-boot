package com.kishore.task;

import com.sendgrid.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import redis.clients.jedis.Jedis;

import java.io.IOException;


public class SendEmailTasklet implements Tasklet {

    final static Logger LOGGER = LoggerFactory.getLogger(SendEmailTasklet.class);

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        Jedis jedis = new Jedis();
        jedis.rpush("email", "first");

    String userName = chunkContext.getStepContext().getStepExecution().getJobParameters().getString("userName");
        String email = chunkContext.getStepContext().getStepExecution().getJobParameters().getString("email");
        Email from = new Email("gopalakrishnankishore510@gmail.com");
        String subject = "Welcome"+ userName;
        Email to = new Email(email);
        Content content = new Content("text/html", default_email_template(userName));
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid("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");
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return RepeatStatus.FINISHED;
    }

    public String default_email_template(String username)
    {
        String html_content = "<html><body>Hi "+ username + "<br>Your are just joined our online shop, we will provide you a best shopping experience.</body></html>";
        return html_content;
    }
}
