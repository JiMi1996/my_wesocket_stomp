package com.rabbitmq.controller;


import com.rabbitmq.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @program: mqtest
 * @description:
 * @author: 李晶敏
 * @create: 2019-09-09 12:05
 **/
@Controller
public class MQController {
//    @Autowired
//    private FirstSender firstSender;
//    @Autowired
//    private RabbitProductorService rabbitProductorService;
//    @GetMapping("/send")
//    public String send(String message){
//        String uuid = "12";
//        rabbitProductorService.sendMessage(uuid,message);
//        return uuid;
//    }
//    @GetMapping("/send2")
//    public String send2(String message){
//        String uuid = "13";
//        rabbitProductorService.sendMessage(uuid,message);
//        return uuid;
//    }


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(String message) throws Exception {
        System.out.println("receiving " + message);
        System.out.println("connecting successfully.");
        return new Greeting("Hello, " + message + "!");
    }

    @SubscribeMapping("/macro")
    public Greeting handleSubscription() {
        System.out.println("this is the @SubscribeMapping('/marco')");
        Greeting greeting = new Greeting("i am a msg from SubscribeMapping('/macro').");
        return greeting;
    }

    private SimpMessageSendingOperations template;

    @Autowired
    public MQController(SimpMessageSendingOperations template) {
        this.template = template;
    }

    @RequestMapping(path="/toHello", method=RequestMethod.POST)
    @ResponseBody
    public void toHello(
            @RequestParam String greeting) {
        String text = "you said just now " + greeting;

        Greeting greetings = new Greeting(greeting);
        this.template.convertAndSend("/topic/greetings", greetings);
    }

    @RequestMapping(path="/tomacro", method=RequestMethod.POST)
    @ResponseBody
    public void tomacro(
            @RequestParam String greeting) {
        String text = "you said just now " + greeting;
        System.out.println("tomacro test:"+ text);
        Greeting greetings = new Greeting(greeting);
        this.template.convertAndSend("/app/tomacro", greetings);
    }


    @RequestMapping(path="/feed", method=RequestMethod.POST)
    @ResponseBody
    public void greet(
            @RequestParam String greeting) {
        String text = "you said just now " + greeting;
        System.out.println("greet test:"+ text);
        Greeting greetings = new Greeting(greeting);
        this.template.convertAndSend("/topic/feed"+12, greetings);
    }

}
