/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.NaveDTO;
import util.NaveDTOAdapter;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class ManejadorMensajes {
    private static final Gson gson = new GsonBuilder()
        .registerTypeAdapter(Mensajes.class, new MensajesAdaptador())
        .registerTypeAdapter(NaveDTO.class, new NaveDTOAdapter())
        .create();
        
    public static String toJson(Mensajes mensaje) {
        return gson.toJson(mensaje, Mensajes.class);
    }
    
    public static Mensajes fromJson(String json) {
        return gson.fromJson(json, Mensajes.class);
    }
}

