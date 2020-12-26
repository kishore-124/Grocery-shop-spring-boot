package com.kishore.controller;

import com.kishore.model.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Chat greeting(Chat message) throws Exception {
        Thread.sleep(1000); // simulated delay
        Chat chat = new Chat();
        chat.setContent(HtmlUtils.htmlEscape(message.getName()));
        return chat;
    }
}
