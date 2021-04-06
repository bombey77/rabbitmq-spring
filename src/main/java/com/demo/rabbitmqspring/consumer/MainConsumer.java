package com.demo.rabbitmqspring.consumer;

import com.demo.rabbitmqspring.model.Person;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Service
public class MainConsumer {

//  @RabbitListener(queues = "TV")
//  public void getMessage(final String message) {
//    System.out.println("Message received = " + message);
//  }

//  @RabbitListener(queues = "Mobile")
//  public void getPerson(final Person person) {
//    System.out.println(person.getName());
//  }

  //  Method below used for Headers only
  @RabbitListener(queues = "Mobile")
  public void getByte(final byte[] message) throws IOException, ClassNotFoundException {
    try (ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(message);
         ObjectInputStream inputStream = new ObjectInputStream(arrayInputStream)) {
      Person person = (Person) inputStream.readObject();
      System.out.println(person.getName());
    }
  }
}
