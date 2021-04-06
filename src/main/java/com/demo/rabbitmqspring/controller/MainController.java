package com.demo.rabbitmqspring.controller;

import com.demo.rabbitmqspring.model.Person;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@RestController
@RequestMapping("/api")
public class MainController {

  private RabbitTemplate rabbitTemplate;

  @Autowired
  public void setRabbitTemplate(final RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @GetMapping("/user")
  public String getUser() throws IOException {

    Person person = new Person();
    person.setName("John");
    person.setSureName("Doe");
    person.setEmail("johny@gmail.com");
    int age = 33;
    person.setAge(age);
//    class Person should implements Serializable
//    rabbitTemplate.convertAndSend("Mobile", person);

    String message = "Some message";
//    rabbitTemplate.convertAndSend("Mobile", message);
//    rabbitTemplate.convertAndSend("Direct-Exchange", "mobile", message);
//    rabbitTemplate.convertAndSend("Fanout-Exchange", "", message);
//    rabbitTemplate.convertAndSend("Topic-Exchange", "tv.mobile.ac", message);

//    Code below used for Headers only
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
      oos.writeObject(person);
      oos.flush();

      byte[] byteArray = baos.toByteArray();
      Message headerMessage = MessageBuilder.withBody(byteArray)
              .setHeader("item1", "mobile")
              .setHeader("item2", "television")
              .build();
      rabbitTemplate.send("Headers-Exchange", "", headerMessage);
    }
    return "roman user";
  }
}
