package com.brecho.SistemasVendas.helpers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomException {

    private String titulo = "Error";
    private  int statusCode = 500;
    private String message;
    private long timestamp = System.currentTimeMillis();
}
