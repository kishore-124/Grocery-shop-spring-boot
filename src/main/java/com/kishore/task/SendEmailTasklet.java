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

import java.io.IOException;


public class SendEmailTasklet implements Tasklet {

    final static Logger LOGGER = LoggerFactory.getLogger(SendEmailTasklet.class);

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    String userName = chunkContext.getStepContext().getStepExecution().getJobParameters().getString("userName");
        String email = chunkContext.getStepContext().getStepExecution().getJobParameters().getString("email");
        Email from = new Email("gopalakrishnankishore510@gmail.com");
        String subject = "User registration mail";
        Email to = new Email(email);
        Content content = new Content("text/plain", "welcome  "+ userName);
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
}
