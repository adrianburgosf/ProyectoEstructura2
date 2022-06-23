
import java.util.ArrayList;
import java.util.LinkedList;


public class Archivo {
    private String name;
    private int AvailListHead = -1;
    private ArrayList<Campo> ListaCampos = new ArrayList();
    private LinkedList<String> registros = new LinkedList<String>();
    private boolean camposCreados;

    public boolean getCamposCreados() {
        return camposCreados;
    }

    public void setCamposCreados(boolean camposCreados) {
        this.camposCreados = camposCreados;
    }
    
    public Archivo(){
        
    }
    public Archivo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailListHead() {
        return AvailListHead;
    }

    public void setAvailListHead(int AvailListHead) {
        this.AvailListHead = AvailListHead;
    }
    
    
    public int sizeCamposs() {
        String camps = "";
        
        camps+= AvailListHead;
        //System.out.println(AvailListHead);

        for (Campo campo : this.getListaCampos()) {
            camps +=  "|"  + campo.getName()   + ","   + campo.getType()  + "," + campo.getSize()   + ",";

            if (campo.isPrimKey()) {
                camps += "true";
            } else {
                camps += "false";
            }
            
        }
        camps+= "|";
       
        return camps.length() + 2 ;      
    }

    

    public ArrayList<Campo> getListaCampos() {
        return ListaCampos;
    }

    public void setListaCampos(ArrayList<Campo> ListaCampos) {
        this.ListaCampos = ListaCampos;
    }

    public LinkedList<String> getRegistros() {
        return registros;
    }

    public void setRegistros(LinkedList<String> registros) {
        this.registros = registros;
    }
    
    public void addCampo(Campo campo){
        this.ListaCampos.add(campo);
    }
    
    public void addCampo(Campo campo, int index){
        this.ListaCampos.add(index,campo);
    }
    
    public void delCampo(int index){
        this.ListaCampos.remove(index);
    }
    
    public Campo getCampo(int index){
        return this.ListaCampos.get(index);
    }
    
    public void printCampos(){
        for(int i = 0; i < ListaCampos.size();i++){
            System.out.println(ListaCampos.get(i));
        }
    }
    
    public boolean hasPrimaryKey(){
        for(int i = 0; i< ListaCampos.size();i++){
            if(ListaCampos.get(i).isPrimKey()){
                return true;
            }
        }
        return false;
    }
    
    public void addRegistro(String registro) {
        this.registros.add(registro);
    }

    @Override
    public String toString() {
        String retornar = "";
        retornar += "NOMBRE:" + name  + "\n\nCAMPOS:\n" ;
        for(int i = 0; i < ListaCampos.size(); i++){
            retornar += i + ") " + ListaCampos.get(i) + "\n";
        }

        retornar += "\nREGISTROS:\n";
        for(int i = 0; i < registros.size(); i++){
            retornar += i + ") " + registros.get(i) + "\n";
        }
        return retornar;
    }
    
}
