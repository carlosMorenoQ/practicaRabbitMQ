package com.example.servicioluckia.infra.rabit_mensajes;

import com.example.servicioluckia.domain.Mensaje;
import com.example.servicioluckia.infra.generics.RabitConst;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PublicarMensajes {

    private final RabbitTemplate rabbitTemplate;

    public PublicarMensajes(RabbitTemplate template) {
        this.rabbitTemplate = template;
    }

    public void scrapingRealizado(Mensaje mensaje){
        rabbitTemplate.convertAndSend(RabitConst.EXCHANGE, RabitConst.ROUTING_KEY, mensaje);
    }

}
