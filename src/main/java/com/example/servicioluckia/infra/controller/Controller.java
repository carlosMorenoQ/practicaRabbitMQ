package com.example.servicioluckia.infra.controller;


import com.example.servicioluckia.domain.Mensaje;
import com.example.servicioluckia.infra.generics.RabitConst;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private RabbitTemplate template;

    @PostMapping()
    public String bookOrder(@RequestBody Mensaje mensaje) {
        System.out.println("Controller se recibio mensaje: " + mensaje.getDescripcion());
        template.convertAndSend(RabitConst.EXCHANGE, RabitConst.ROUTING_KEY, mensaje);
        return "Success !!";
    }




}
