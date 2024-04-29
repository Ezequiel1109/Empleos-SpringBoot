package com.organizacionname.AppSB.util;

import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public class Utileria {

    public static String guardarArchivo(MultipartFile multiPart, String ruta) {
        // Obtenemos el nombre original del archivo.
        String nombreOriginal = multiPart.getOriginalFilename();
        nombreOriginal.replace(" ", "-");// Reemplazamos los espacios por guiones.

        String nombreFinal = randomAlphaNumeric(8) + nombreOriginal;
        try {
            // Formamos el nombre del archivo para guardarlo en el disco duro.
            File imageFile = new File(ruta + nombreFinal);
            System.out.println("Archivo: " + imageFile.getAbsolutePath());
            // Guardamos fisicamente el archivo en HD.
            multiPart.transferTo(imageFile);
            return nombreFinal;
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }
    /**
     * metodo para generar una cadena de manera aleatoria con longitud N
    */

    public static String randomAlphaNumeric(int count){
        String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=}{][|\\:;\"'<>,.?/~`";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random() * CARACTERES.length());
            builder.append(CARACTERES.charAt(character));
        }
        return builder.toString();
    }
}
