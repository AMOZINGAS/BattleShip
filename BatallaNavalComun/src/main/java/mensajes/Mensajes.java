/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajes;

import java.io.Serializable;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public abstract class Mensajes implements Serializable {
    private String comando;

    public Mensajes(String comando) {
        this.comando = comando;
    }

    public String getComando() {
        return comando;
    }  
}

