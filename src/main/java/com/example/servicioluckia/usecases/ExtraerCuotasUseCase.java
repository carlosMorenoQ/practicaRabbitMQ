package com.example.servicioluckia.usecases;


import com.example.servicioluckia.domain.Mensaje;
import com.example.servicioluckia.infra.rabit_mensajes.PublicarMensajes;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ExtraerCuotasUseCase {

    private final PublicarMensajes publicarMensajes;
    private final ExtraerEventosUseCase extraerEventosUseCase;

    public ExtraerCuotasUseCase(PublicarMensajes publicarMensajes,
                                ExtraerEventosUseCase extraerEventosUseCase) {
        this.publicarMensajes = publicarMensajes;
        this.extraerEventosUseCase = extraerEventosUseCase;
    }


    public void realizarExtraccion(){
        Mensaje mensaje = new Mensaje();
        mensaje.setDescripcion("LuckiaEscucho el mensaje a las: " + new Date());
        extraerEventosUseCase.verificarFuncionamiento();
        publicarMensajes.scrapingRealizado(mensaje);
    }

}
