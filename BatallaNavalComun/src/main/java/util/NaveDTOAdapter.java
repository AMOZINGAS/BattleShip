/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import dtos.BarcoDTO;
import dtos.CasillaDTO;
import dtos.CoordenadasDTO;
import dtos.CruceroDTO;
import dtos.EstadoNaveENUM;
import dtos.NaveDTO;
import dtos.OrientacionENUM;
import dtos.PortaAvionesDTO;
import dtos.SubmarinoDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Beto_
 */
public class NaveDTOAdapter extends TypeAdapter<NaveDTO>{
    private final Gson gson = new Gson();
    
    @Override
    public void write(JsonWriter out, NaveDTO value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.beginObject();
        out.name("tamanio").value(value.getTamanio());
        out.name("orientacion").value(value.getOrientacion().toString());
        if (value.getCoordenadaInicial() != null) {
            out.name("coordenadaInicial");
            gson.toJson(value.getCoordenadaInicial(), CoordenadasDTO.class, out);
        }
        out.name("estado").value(value.getEstado().toString());
        out.name("nombre").value(value.getNombre());
        if (value.getCasillas() != null) {
            out.name("casillas");
            gson.toJson(value.getCasillas(), List.class, out); // O List<CasillaDTO>.class
        }
        out.name("tipo").value(value.getTipo()); // Llama al método abstracto
        out.endObject();
    }

    @Override
    public NaveDTO read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        in.beginObject();
        int tamanio = 0;
        OrientacionENUM orientacion = null;
        CoordenadasDTO coordenadaInicial = null;
        EstadoNaveENUM estado = null;
        String nombre = null;
        List<CasillaDTO> casillas = null;
        String tipo = null;

        while (in.hasNext()) {
            switch (in.nextName()) {
                case "tamanio":
                    tamanio = in.nextInt();
                    break;
                case "orientacion":
                    orientacion = OrientacionENUM.valueOf(in.nextString());
                    break;
                case "coordenadaInicial":
                    coordenadaInicial = gson.fromJson(in, CoordenadasDTO.class);
                    break;
                case "estado":
                    estado = EstadoNaveENUM.valueOf(in.nextString());
                    break;
                case "nombre":
                    nombre = in.nextString();
                    break;
                case "casillas":
                    casillas = new ArrayList<>();
                    in.beginArray();
                    while (in.hasNext()) {
                        casillas.add(gson.fromJson(in, CasillaDTO.class));
                    }
                    in.endArray();
                    break;
                case "tipo":
                    tipo = in.nextString();
                    break;
                default:
                    in.skipValue();
            }
        }
        in.endObject();
        
        if (tipo != null) {
            switch (tipo.toLowerCase()) {
                case "barco":
                    return new BarcoDTO(tamanio, nombre, orientacion.ordinal()); // Ajusta el constructor
                case "submarino":
                    return new SubmarinoDTO(tamanio, nombre, orientacion.ordinal()); // Ajusta el constructor
                case "crucero":
                    return new CruceroDTO(tamanio, nombre, orientacion.ordinal()); // Ajusta el constructor
                case "portaaviones":
                    return new PortaAvionesDTO(tamanio, nombre, orientacion.ordinal()); // Ajusta el constructor
            }
        }
        return null;
    }
}
