package ua.ilnicki.jbgm.io.lwjgl2;

import java.util.HashMap;

/**
 *
 * @author Dmytro Ilnicki {@literal <dmytro@ilnicki.me>}
 */
public class SegmentShematic
{
    private final static HashMap<Character, boolean[]> shematics = new HashMap<>();
    
    static
    {
        shematics.put(null, new boolean[]{false, false, false, false, false, false, false});
        
        shematics.put('0', new boolean[]{true,  true,  true,  true,  true,  true,  false});
        shematics.put('1', new boolean[]{false, true,  true,  false, false, false, false});
        shematics.put('2', new boolean[]{true,  true,  false, true,  true,  false, true});
        shematics.put('3', new boolean[]{true,  true,  true,  true,  false, false, true});
        shematics.put('4', new boolean[]{false, true,  true,  false, false, true,  true});
        shematics.put('5', new boolean[]{true,  false, true,  true,  false, true,  true});
        shematics.put('6', new boolean[]{true,  false, true,  true,  true,  true,  true});
        shematics.put('7', new boolean[]{true,  true,  true,  false, false, false, false});
        shematics.put('8', new boolean[]{true,  true,  true,  true,  true,  true,  true});
        shematics.put('9', new boolean[]{true,  true,  true,  true,  false, true,  true});
        /*                               A      B      C      D      E      F      G      H      I    */
        shematics.put('A', new boolean[]{true,  true,  true,  false, true,  true,  true,  false, false});
        shematics.put('B', new boolean[]{false, false, false, true,  true,  true,  true,  true,  true});
        shematics.put('C', new boolean[]{true,  false, false, true,  true,  true,  false, false, false});
        shematics.put('D', new boolean[]{false, false, true,  true,  true,  true,  false, true,  false});
        shematics.put('E', new boolean[]{true,  false, false, true,  true,  true,  true,  false, false});
        shematics.put('F', new boolean[]{true,  false, false, false, true,  true,  true,  false, false});
        shematics.put('G', new boolean[]{true,  false, true,  true,  true,  true,  false, false, false});
        shematics.put('H', new boolean[]{false, true,  true,  false, true,  true,  true,  false, false});
        shematics.put('I', new boolean[]{false, true,  true,  false, false, false, false, false, false});
        shematics.put('J', new boolean[]{false, true,  true,  true,  true,  false, false, false, false});
        shematics.put('K', new boolean[]{false, true,  false, false, true,  true,  true,  false, true});
        shematics.put('L', new boolean[]{false, false, false, true,  true,  true,  false, false, false});
        shematics.put('M', new boolean[]{false, false, false, false, false, false, false, false, false});
        shematics.put('N', new boolean[]{false, false, true,  false, true,  true,  false, true,  false});
        shematics.put('O', new boolean[]{true,  true,  true,  true,  true,  true,  false, false, false});
        shematics.put('P', new boolean[]{true,  true,  false, false, true,  true,  true,  false, false});
        shematics.put('Q', new boolean[]{true,  true,  true,  true,  true,  true,  true,  false, false});
        shematics.put('R', new boolean[]{true,  true,  false, false, true,  true,  true,  false, true});
        shematics.put('S', new boolean[]{true,  false, true,  true,  false, true,  true,  false, false});
        shematics.put('T', new boolean[]{false, false, false, true,  true,  true,  true,  false, false});
        shematics.put('U', new boolean[]{false, true,  true,  true,  true,  true,  false, false, false});
        shematics.put('V', new boolean[]{false, true,  true,  false, false, true,  false, false, true});
        shematics.put('W', new boolean[]{false, false, false, false, false, false, false, false, false});
        shematics.put('X', new boolean[]{false, false, false, false, false, false, false, false, false});
        shematics.put('Y', new boolean[]{false, true,  true,  false, false, false, false, true,  false});
        shematics.put('Z', new boolean[]{false, false, false, false, false, false, false, false, false});
        shematics.put(' ', new boolean[]{false, false, false, false, false, false, false, false, false});
        shematics.put('-', new boolean[]{false, false, false, false, false, false, true,  false, false});
    }
    
    public static boolean[] getSchematic(Character chr)
    {
        boolean[] shematic = shematics.get(chr);
        return shematic != null ? shematic : shematics.get(null);
    }

    static boolean[] getSchematic(Integer digit)
    {
        if(digit == null)
        {
            return shematics.get(null);
        }
        else
        {
            return shematics.get(digit.toString().charAt(0));
        }
    }
}
