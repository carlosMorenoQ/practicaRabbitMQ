package com.example.servicioluckia.infra.rabit_mensajes;

import com.example.servicioluckia.domain.Mensaje;
import com.example.servicioluckia.infra.generics.RabitConst;
import com.example.servicioluckia.usecases.ExtraerCuotasUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EscucharMensajes {

    private final ExtraerCuotasUseCase extraerCuotasUseCase;

    public EscucharMensajes(ExtraerCuotasUseCase extraerCuotasUseCase) {
        this.extraerCuotasUseCase = extraerCuotasUseCase;
    }

    @RabbitListener(queues = RabitConst.QUEUE_INICIAR_SCRAPING)
    public void recepcionarMensajeDesdeQuee(Mensaje mensaje){
        System.out.println("Se recibio el mensaje: " + mensaje.getDescripcion());
        extraerCuotasUseCase.realizarExtraccion();
    }

}
