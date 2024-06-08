package DAAII_T1_RamirezAquinoClever.T1.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomPassword {

    public String generar(int longitud){
        String cadena = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for(int i = 0; i < longitud; i++){
            int index = random.nextInt(cadena.length());
            password.append(cadena.charAt(index));
        }
        return password.toString();
    }

}
