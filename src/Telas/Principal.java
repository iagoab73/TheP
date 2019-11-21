package Telas;

import Telas.MenuPrincipal.MenuPrincipal;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

/**
 *
 * @author 115111168
 */
public class Principal {
    
        static Controller controller1 = null;
        static Controller controller2 = null;
        static boolean musica = true;
    public static void main(String[] args) {
            
        
            try {
                Controllers.create();
            } catch (LWJGLException ex) {
                //Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            Controllers.poll();
            for(int i = 0 ; i< Controllers.getControllerCount();i++){
                    //if(controller.getName().equals("usb gamepad           ")){
                    if(Controllers.getController(i).getName().equals("Controller (XBOX 360 For Windows)")){
                        if(controller1 == null){
                            controller1 = Controllers.getController(i);
                        }else{
                            controller2 = Controllers.getController(i);
                            break;
                        }
                    }
            }
            
            /*System.out.println("1: " + controller1.getName());
            System.out.println("2: " + controller2.getName());*/
            
            /*Personagem p = new Guerreiro();

            Personagem k = new Mago();

            Tela t = new Tela(p,k);
            t.setVisible(true);*/

            MenuPrincipal pr = new MenuPrincipal(1280,768,controller1, controller2, musica);
            pr.setVisible(true);
            
            /*Pause p = new Pause(1280, 720, musica, controller1,controller2);
            p.setVisible(true);*/

            /*VS v = new VS(1280,720, controller1, controller2);
            v.setVisible(true);*/
            
        
    }
}
