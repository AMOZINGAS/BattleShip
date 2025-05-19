/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class MensajesAdaptador implements JsonSerializer<Mensajes>, JsonDeserializer<Mensajes> {
    @Override
    public JsonElement serialize(Mensajes mensaje, Type type, JsonSerializationContext context) {
        return context.serialize(mensaje);
    }   
    
    @Override
    public Mensajes deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String comando = jsonObject.get("comando").getAsString();

        try {
            Class<? extends Mensajes> messageClass = switch (comando) {
                    // requestsss
                    case "CREAR_PARTIDA" -> ReqCrearPartida.class;
                    case "UNIRSE" -> ReqUnirse.class;
                    case "REGISTRAR_JUGADOR_CONFIGURACION" -> ReqRegistrarJugadorConfig.class;

                    // Response
                    case "JUGADOR_UNIDO", "JUGADOR_NO_UNIDO" -> ResUnirse.class;
                    case "PARTIDA_CREADA", "PARTIDA_NO_CREADA" -> ResCrearPartida.class;
                    case "ESPERANDO_OPONENTE" -> ResRegistrarJugador.class; 
                    case "CONFIGURACION_RECIBIDA" -> ResConfiguracionRecibida.class;
                    default -> throw new JsonParseException("Comando desconocido: " + comando);
            };

            return context.deserialize(json, messageClass);
        } catch (Exception e) {
            throw new JsonParseException("Error deserializando mensaje. " + "Comando: " +comando + " No reconocido", e);
        }
    }
}
