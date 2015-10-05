
//code

package model;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Miguel
 */
public class FuenteLetra {
    
    //private
    private Font letter = null;
    private String source;
        //String name
     
    public Font getFont(){
        source = "DS-DIGI.ttf";
                //LiquidCrystal-Bold.otf";
        try{
            InputStream upload = getClass().getResourceAsStream(source);
            letter = Font.createFont(Font.TRUETYPE_FONT, upload);
            letter = letter.deriveFont(0, 18);
        }
        catch(FontFormatException | IOException ex){
            /*ex.printStackTrace();
            System.err.println(source + " not loaded.  Using serif font.");*/
            letter = new Font("serif", Font.PLAIN, 18);
        }
        return letter;
    }
    
}
