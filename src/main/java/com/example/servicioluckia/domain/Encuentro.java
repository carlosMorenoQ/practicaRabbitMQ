package com.example.servicioluckia.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document
public class Encuentro {

    @Id
    private String id;
    private String link;
    private String local;
    private String visitante;
    private List<String> cuotas;

}
