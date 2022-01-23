package com.example.servicioluckia.usecases;

import com.example.servicioluckia.domain.Encuentro;
import com.example.servicioluckia.domain.EncuentroRepository;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExtraerEventosUseCase {

    private static final String URL_BASE = "https://www.luckia.co/";
    private static final String URL_ENCUENTROS_FUTBOL = "https://www.luckia.co/apuestas/futbol/?date=sve";
    private static final String CLASS_LISTA_ENCUENTROS = "u-flex u-flex-col-reverse md:u-flex-col u-justify-center u-p-0 md:u-pr-4 event-header-center";
    private static final String CLASS_LOCAL = "c-event-list__team event-header-team top";
    private static final String CLASS_VISITANTE = "c-event-list__team event-header-team bottom";
    private static final String CLASS_TIPO_CUOTA = "u-text-dark u-block u-p-8 u-ellipsis u-text-center pick-title";
    private static final String CLASS_VALOR_CUOTA = "u-text-dark u-flex u-justify-center u-items-center u-p-8 u-bg-white pick-value";
    private static final String CLASS_LISTA_APUESTAS = "c-game-offers__pick pick bet-pick ";
    private final EncuentroRepository encuentroRepository;

    public ExtraerEventosUseCase(EncuentroRepository encuentroRepository) {
        this.encuentroRepository = encuentroRepository;
    }

    public void verificarFuncionamiento() {

        try {
            urlsEncuentros().forEach(element -> {

                var local = element.getElementsByClass(CLASS_LOCAL).text();
                var visitante = element.getElementsByClass(CLASS_VISITANTE).text();
                var linkEncuentro = element.select("a").attr("href");
                var apuestas = extraccionApuestas(URL_BASE.concat(linkEncuentro));

                var encuentro = new Encuentro();

                encuentro.setLink(URL_BASE.concat(linkEncuentro));
                encuentro.setLocal(local);
                encuentro.setVisitante(visitante);
                encuentro.setCuotas(apuestas);

                encuentroRepository.save(encuentro);


            });
        } catch (IOException e) {
            e.getStackTrace();
        }
    }


    private Elements urlsEncuentros() throws IOException {
        return Jsoup.connect(URL_ENCUENTROS_FUTBOL)
                .userAgent("Mozilla/5.0")
                .get()
                .getElementsByClass(CLASS_LISTA_ENCUENTROS);
    }

    private List<String> extraccionApuestas(String link) {
        List<String> apuestas = new ArrayList<>();
        try {
            Jsoup.connect(link)
                    .userAgent("Mozilla/5.0")
                    .get()
                    .getElementsByClass("offer-type")
                    .get(0)
                    .getElementsByClass(CLASS_LISTA_APUESTAS)
                    .forEach(element -> {
                        var tipoCuota = element.getElementsByClass(CLASS_TIPO_CUOTA).text();
                        var cuota = element.getElementsByClass(CLASS_VALOR_CUOTA).text();
                        var apuesta = tipoCuota.concat("= ").concat(cuota);
                        apuestas.add(apuesta);
                    });
        } catch (IOException e) {
            e.getStackTrace();
        }
        return apuestas;
    }

}
