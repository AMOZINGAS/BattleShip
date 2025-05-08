/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvpMenu;

/**
 *
 * @author Amos Heli Olguin Quiroz
 */
public class PresentadorMenu {
    
    private static PresentadorMenu presentadorMenu;
    private final ModeloMenu modeloMenu;
    
    public PresentadorMenu(ModeloMenu modeloMenu) {
        this.modeloMenu = modeloMenu;

    }
    
    public void unirseAPartida(int puerto) {
        modeloMenu.unirseAPartida(puerto);
    }
    
    public void crearPartida(int puerto){
        modeloMenu.crearPartida(puerto);
    }
    
}
