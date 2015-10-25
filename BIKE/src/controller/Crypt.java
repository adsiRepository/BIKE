
// Project: SIMGEPLAP

package controller;

import java.util.Random;

/*/
 * creation : 5/03/2015
 * author   : Angela Ramirez
/*/


/**
 * CLASE ESPECIALIZADA PARA LA GENERACION DE CONTRASEÑAS.
 */
public class Crypt {

    //private static final char[] constantesHexadecimales = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    
    public static final String ARTICULOS = "idarticulo";
    public static final String COMPONENTES = "idcomponente";
    public static final String REPUESTOS = "idrepuesto";
    
    public static String generarContraseña(String tipo) {
        String characters;
        StringBuilder stringBuilder = new StringBuilder();
        if (tipo.equals(ARTICULOS)) {
            characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // @#$%^&+=abcdefghijklmnopqrstuvwxyz
            int largo = 3; // cantidad de caracteres en la contraseña
            for (int index = 0; index < largo; index++) {
                int genIndex = new Random().nextInt(characters.length());
                stringBuilder.append(characters.charAt(genIndex));
            }
        }
        if (tipo.equals(COMPONENTES)) {
            characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // @#$%^&+=abcdefghijklmnopqrstuvwxyz
            int largo = 3; // cantidad de caracteres en la contraseña
            for (int index = 0; index < largo; index++) {
                int genIndex = new Random().nextInt(characters.length());
                stringBuilder.append(characters.charAt(genIndex));
            }
        }
        
        if (tipo.equals(REPUESTOS)) {
            characters = "0123456789"; // @#$%^&+=
            int largo = 6; // cantidad de caracteres en la contraseña
            for (int index = 0; index < largo; index++) {
                int genIndex = new Random().nextInt(characters.length());
                stringBuilder.append(characters.charAt(genIndex));
            }
        }
        
        return stringBuilder.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(generarContraseña(REPUESTOS));
    }

    /*public static String encryptMD5(String stringAEncriptar) {
        try {
            MessageDigest msdg = MessageDigest.getInstance("MD5");
            byte[] bytes = msdg.digest(stringAEncriptar.getBytes());
            StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
            for (int i = 0; i < bytes.length; i++) {
                int bajo = bytes[i] & 0x0f;
                int alto = (bytes[i] & 0xf0) >> 4;
                strbCadenaMD5.append(constantesHexadecimales[alto]);
                strbCadenaMD5.append(constantesHexadecimales[bajo]);
            }
            return strbCadenaMD5.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }*/
    
    
    public static String generarCodUser(){
        String prefijo = "AB";
        String cod = "0123456789";
        StringBuilder codUser = new StringBuilder();
        codUser.append(prefijo.charAt(new Random().nextInt(prefijo.length())));
        for (int i = 1; i <= 4; i++) { // cantidad de caracteres de la contraseñac
            codUser.append(cod.charAt(new Random().nextInt(cod.length())));
        }
        return codUser.toString();
    }
    
    /*
    public static void main(String[] args) {
        System.out.print(GenerarCodUser()+"\n");
        System.out.print(encryptMD5("1111"));
    }
    */
    
}
