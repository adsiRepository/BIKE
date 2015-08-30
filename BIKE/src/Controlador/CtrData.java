
package Controlador;


import java.util.ArrayList;//Maneja el ArrayList

/**
 *
 * @author Usuario
 */
public class CtrData {
    
    
    ArrayList<Object> datos;
    String tabla;
    
    
    public CtrData(String tabla){
        //this.miCndb = ConnBD.GetConnection();
        this.tabla = tabla;
    }
    
    
    /*public void DataToSave(ArrayList<Object> vals){
        this.datos = vals;
    }*/
    
    
    public String Implode(String[] array){
        String fill;
        StringBuilder sb = new StringBuilder();
        sb.append(array[0]);
        for(int i=1; i<array.length; i++){
            sb.append(",");
            sb.append(array[i]);
        }
        fill = sb.toString();
        return fill;
    }
    
    
    public String ImplodeArL(ArrayList<Object> inn){
        String fill = "(";
        for(int i=0; i<inn.size(); i++){
            if(i == inn.size()-1){
                fill = fill.concat("'"+inn.get(i)+"')");
            }else{
                fill = fill.concat("'"+inn.get(i)+"',");
            }
        }
        return fill;
    }
    
    
    public String ArrayToBD(ArrayList<Object> newreg){
        this.datos = newreg;
        ArrayList<Object> aux = new ArrayList<>();
        //String[] reg = new String[datos.size()];
        /*for (int i=0; i<datos.size(); i++) {
            aux.add(i) = "'"+datos.get(i)+"'";
        }*/
        String query = "insert into "+tabla+" values "+ImplodeArL(datos)+"";
        return query;
    }
    
    
    public String ExtWhere( String camp, String search ){
        String qs = "select * from "+tabla+" where "+camp+" = '"+search+"'";
        return qs;
    }
    
    
    public String ChangeReg( String camp, String ref, ArrayList<Object> newdata, String[] camps){
        String[] reg = new String[camps.length];
        for (int i=0; i<camps.length; i++) {
            reg[i] = camps[i]+"='"+newdata.get(i)+"'";
        }
        String qry = "update "+tabla+" set "+Implode(reg)+" where "+camp+"='"+ref+"'";
        return qry;
    }
    
    
    public String EraseIt(String camp, String ref){
        String qry = "delete from "+tabla+" where "+camp+"='"+ref+"'";
        return qry;
    }
    
    
    public String ExtractAll(){
        String qry = "select * from "+tabla;
        return qry;
    }
    
    /*public void ArrayToBD(){
        try{
            appeal = miCndb.createStatement();
        }catch(Exception ex){
            
        }
    }*/
    
}
