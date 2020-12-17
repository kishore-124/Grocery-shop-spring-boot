package com.kishore.controller;
import com.kishore.model.*;
import com.twilio.Twilio;
import java.net.URI;
import com.twilio.rest.api.v2010.account.*;
import com.twilio.type.PhoneNumber;
import org.springframework.web.bind.annotation.*;


@RestController
public class SmsController {

    @PostMapping("/send/messages")
    public String sendMessage(@RequestBody SmsMessage smsMessage)
    {
        Twilio.init("AC70b04dba847ec820f1a100dd90b49802", "dc8050d9ba501fab1adf9f369c76737e");
        Message message = Message.creator(
                new PhoneNumber(smsMessage.getPhone()),
                new PhoneNumber("+18327348731"),
                smsMessage.getContent())
                .create();

        return "Messsage sent successfully";
    }

    @PostMapping("/send/calls")
    public String makeCall(@RequestBody CallModel callModel)
    {

        Twilio.init("AC70b04dba847ec820f1a100dd90b49802", "dc8050d9ba501fab1adf9f369c76737e");
        Call call = Call.creator(
                new PhoneNumber(callModel.getPhone()),
                new PhoneNumber("+18327348731"),
                URI.create("http://demo.twilio.com/docs/voice.xml"))
                .create();

        return "Call made";
    }
}
