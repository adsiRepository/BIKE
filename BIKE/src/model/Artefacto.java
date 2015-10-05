
//code

package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Miguel
 */
public class Artefacto {
    
    public static class Aro{
        
        public ArrayList<String> componentes;
        
        public Aro(){
            componentes = new ArrayList<>();
            Collections.addAll(componentes, "Manzana","Radios","Aro");
        }
        
    }
    
    public static class Bicicleta{
        //Fuentes => tipos de datos -> http://codehero.co/java-desde-cero-tipos-de-datos/
        public ArrayList<String> componentes;
        @SuppressWarnings("FieldMayBeFinal")
        private String miTipo;
        
        public Bicicleta(String tipo) {
            componentes = new ArrayList<>();
            this.miTipo = tipo;
            determinarComponentes(miTipo);
        }
        
        private void determinarComponentes(String tipo){
            this.miTipo = tipo;
            componentes.clear();
            Collections.addAll(componentes, "Marco/Cuadro","Horquilla/Tenedor","Manubrio","Codo","Plato/Relacion"
                    ,"Piñon/Pacha","Cadena/nilla","Sillin","Poste Sillin","Frenos","Mangos","Pedales","Eje Centro","Copas Centro"
                    ,"Copas Frente","Balines","Tornillo Sillin");
            switch (miTipo) {
                case "Tipo Cross":
                    componentes.add(5, "Biela");
                    break;
                case "Todo Terreno":
                    componentes.add(10, "Cambios");
                    break;
                case "Turismo":
                    componentes.add("Guardabarros");
                    componentes.add("Parrilla");
                    break;
                case "Alta Gama":
                    componentes.set(3, "Espigo");
                    break;
                default:
                    break;
            }
        }

    }
    
    //METODO PRINCIPAL DE ESTA CLASE, ES USADO PARA PRUEBAS DE SU FUNCIONAMIENTO
    public static void main(String[] args) {
        
        Bicicleta bici = new Bicicleta("Bici. s/c Econo");
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Tipo Bici");
        System.out.println("TT => 1");
        System.out.println("Turismo => 2");
        System.out.println("You Choose");
        int r = scan.nextInt();
        switch (r) {
            case 1:
                bici.determinarComponentes("Todo Terreno");
                bici.componentes.stream().forEach((comp) -> {
                    System.out.println(comp);
                });
                break;
            case 2:
                bici.determinarComponentes("Turismo");
                bici.componentes.stream().forEach((comp) -> {
                    System.out.println(comp);
                });
                break;
        }
   
    }
    
}
