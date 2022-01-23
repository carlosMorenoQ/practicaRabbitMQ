package com.example.servicioluckia.infra.generics;


import org.springframework.stereotype.Component;

@Component
public class RabitConst {

    public static final String QUEUE_INICIAR_SCRAPING = "iniciarScraping";
    public static final String QUEUE_SCRAPING_REALIZADO = "scrapingRealizado";
    public static final String EXCHANGE = "scraping";
    public static final String ROUTING_KEY = "scraping_scrapingRealizado";

}
