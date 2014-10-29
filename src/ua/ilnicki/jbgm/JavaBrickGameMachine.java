package ua.ilnicki.jbgm;

import ua.ilnicki.jbgm.machine.Keyboard;
import ua.ilnicki.jbgm.machine.Keyboard.*;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class JavaBrickGameMachine
{
    public static void main(String[] args)
    {
        Keyboard kb = new Keyboard();
        KeyboardPasser kbp = kb.getKeyboardPasser();
        
        System.out.println(kb.ctrlKeyPressed(CtrlKeys.UP));
        System.out.println(kbp.keyPressed(CtrlKeys.UP));
        
        kb.setCtrlKeyState(CtrlKeys.UP, true);
        
        System.out.println(kb.ctrlKeyPressed(CtrlKeys.UP));
        System.out.println(kbp.keyPressed(CtrlKeys.UP));
    } 
}
