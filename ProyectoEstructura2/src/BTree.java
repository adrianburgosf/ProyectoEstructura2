
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class BTree implements Serializable {

    int raiz;
    int m;// 
    ArrayList<Node> nodos;
    private static final long serialVersionUID = 6529685098267757690L;
    long posicion;

    public BTree(int orden) {
        nodos = new ArrayList<Node>();
        this.m = orden;
        nodos.add(new Node(m));
        raiz = 0;

    }

    public long getPosicion() {
        return this.posicion;
    }

    public int getRaiz() {
        return raiz;
    }

    public void setRaiz(int raiz) {
        this.raiz = raiz;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public ArrayList<Node> getNodos() {
        return nodos;
    }

    public void setNodos(ArrayList<Node> nodos) {
        this.nodos = nodos;
    }

    public void insert(String x, long offset) {
        posicion = offset;
        int r1 = raiz;
        Node r = nodos.get(this.getRaiz());
        //System.out.println(r.getLlaves().size());
        //System.out.println(raiz.getLlaves().size());
      //  System.out.println("N: " + r.getN());
       // System.out.println("M: " + (m - 1));
        if (r.getN() == m - 1) {
            int s1 = nodos.size();
            Node s = new Node(m);
            nodos.add(s);
            raiz = s1;
            s.setLeaf(false);
            s.setN(0);
            s.getHijos().set(0, r1);
            split(s1, 0, r1);
            insertNonFull(s1, x, offset);
        } else {
          //  System.out.println("no hay split");
            insertNonFull(r1, x, offset);
        }
    }

    public void split(int x1, int i, int y1) {
        int z1 = nodos.size();
        Node z = new Node(m);
        nodos.add(z);
        Node y = nodos.get(y1);
        Node x = nodos.get(x1);
        z.setLeaf(y.isLeaf());
        int lower = Math.max((m / 2) - 1, 1);
        z.setN((m - 1) - lower - 1);
        for (int j = 0; j < z.getN(); j++) {
            z.getLlaves().set(j, y.getLlaves().get(j + lower + 1));
        }
        if (!y.isLeaf()) {
            for (int j = 0; j < z.getN() + 1; j++) {
                z.getHijos().set(j, y.getHijos().get(j + lower + 1));
            }
        }
        y.setN(lower);
        x.getHijos().add(i + 1, z1);
        x.getHijos().remove(m);

        x.getLlaves().add(i, y.getLlaves().get(lower));
        x.getLlaves().remove(m - 1);
      //  System.out.println("N: " + x.getN());

        x.setN(x.getN() + 1);
       // System.out.println("N: " + x.getN());

        //imprimir_arbol(0, 0);
        //imprimir_arbol(1, 0);
    }

    public BTree loadT(String nombre) {
        File archivo = new File(nombre + "BTree");
        try {
            if (archivo.exists()) {
                FileInputStream entrada = new FileInputStream(archivo);
                ObjectInputStream objeto = new ObjectInputStream(entrada);
                try {
                    BTree Temp = (BTree) objeto.readObject();
                    return Temp;
                } catch (EOFException e) {
                    //encontro el final del archivo
                }
                objeto.close();
                entrada.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void insertNonFull(int x1, String y1, long offset) {
        Node x = nodos.get(x1);
        int i = x.getN() - 1;
        if (x.isLeaf()) {
            while (i >= 0 && y1.compareTo(x.getLlaves().get(i).getLlave()) < 0) {
                i--;
            }
            x.getLlaves().add(i + 1, new Keys(y1, offset));
            x.getLlaves().remove(m - 1);
            x.setN(x.getN() + 1);
          //  System.out.println("N en nonfull: " + x.getN());
        } else {
            while (i >= 0 && y1.compareTo(x.getLlaves().get(i).getLlave()) < 0) {
                i--;
            }
            i++;

            if (nodos.get(x.getHijos().get(i)).getN() == m - 1) {
                split(x1, i, x.getHijos().get(i));
              //  System.out.println("hay split en non full");
                if (y1.compareTo(x.getLlaves().get(i).getLlave()) > 0) {
                    i++;
                }
            }
            insertNonFull(x.getHijos().get(i), y1, offset);
        }

    }
    
    public void getOffsets(int x , ArrayList<Long> y){
        if (x >= 0) {
            Node node = nodos.get(x);

            for (int i = 0; i < node.getN(); i++) {
                getOffsets(node.getHijos().get(i), y);
                y.add(node.getLlaves().get(i).getPos());
            }
            getOffsets(node.getHijos().get(node.getN()), y);
        }
      /*for(int i = 0 ; i < nodos.size(); i ++){
          for(int j = 0 ; j < nodos.get(i).getLlaves().size();j++){
              System.out.println(nodos.get(i).getLlaves().get(j).getPos());
              y.add(nodos.get(i).getLlaves().get(j).getPos());
          }
      }*/
        
    }
    
    public void modif(int x, String keys, ArrayList<Long> positions){
        
         //   System.out.println("1");
            int i = 0;
            Node nodito = nodos.get((int) x);
            //System.out.println("i antes de primer while: " + i);
            //System.out.println("N antes de primer while: " + nodito.getN());
            //System.out.println("antes de primer while: " + nodito.getLlaves().get(i).getLlave());
            String k = nodito.getLlaves().get(i).getLlave();
            //System.out.println("K antes primer while: " + k);
            //System.out.println(k);
            //System.out.println("antes de primer while: " + nodito.getLlaves().get(i).getLlave());
            while (i < nodito.getN() && k.compareTo(nodito.getLlaves().get(i).getLlave()) > 0) {
                i++;
            }
           // System.out.println("2");
            boolean flag = false;
            /*System.out.println("3");
            System.out.println("in search 2: " + nodito.getLlaves().get(i).getLlave());
            System.out.println("String en metodo: " + k);

            System.out.println("i: " + i);
            System.out.println("N: " + nodito.getN());
            //i < x.getN() && 
            //k.compareTo(x.getLlaves().get(i).getLlave()) == 0*/
            while (i < nodito.getN() && k.compareTo(nodito.getLlaves().get(i).getLlave()) == 0) {
             /*   System.out.println("Aqui entro al while");
                System.out.println("i en while: " + i);
                System.out.println("N en while: " + nodito.getN());*/
                flag = true;
                //System.out.println("k in modif: " + k);
                nodito.getLlaves().get(i).setLlave(keys);
                //System.out.println("after: " + nodito.getLlaves().get(i).getLlave());
               
                
                //positions.add(nodito.getLlaves().get(i).getPos());
              //  System.out.println("done");
                if (!nodito.isLeaf()) {
                    modif(nodito.getHijos().get(i), keys, positions);
                   // System.out.println("done 2");
                }
                i++;
            }
            if (!nodito.isLeaf()) {
                modif(nodito.getHijos().get(i), keys, positions);
             //   System.out.println("done 3");
            }
            
    }

    public void BTreeSearch(int x, ArrayList<String> keys, ArrayList<Long> positions) {
        for (int j = 0; j < keys.size(); j++) {
            String k = keys.get(j);
            //System.out.println("K: " + k);
         //   System.out.println("1");
            int i = 0;
            Node nodito = nodos.get((int) x);
           /* System.out.println("i antes de primer while: " + i);
            System.out.println("N antes de primer while: " + nodito.getN());
            System.out.println("antes de primer while: " + nodito.getLlaves().get(i).getLlave());
            System.out.println("K antes primer while: " + k);
            System.out.println(k);*/
           // System.out.println("antes de primer while: " + nodito.getLlaves().get(i).getLlave());
            while (i < nodito.getN() && k.compareTo(nodito.getLlaves().get(i).getLlave()) > 0) {
                i++;
            }
           // System.out.println("2");
            boolean flag = false;
            /*System.out.println("3");
            System.out.println("in search 2: " + nodito.getLlaves().get(i).getLlave());
            System.out.println("String en metodo: " + k);

            System.out.println("i: " + i);
            System.out.println("N: " + nodito.getN());*/
            //i < x.getN() && 
            //k.compareTo(x.getLlaves().get(i).getLlave()) == 0
            while (i < nodito.getN() && k.compareTo(nodito.getLlaves().get(i).getLlave()) == 0) {
            //    System.out.println("positions: " + positions);
             /*   System.out.println("Aqui entro al while");
                System.out.println("i en while: " + i);
                System.out.println("N en while: " + nodito.getN());*/
                flag = true;
                positions.add(nodito.getLlaves().get(i).getPos());
            //    System.out.println("pos in search: " + nodito.getLlaves().get(i).getPos());
            //    System.out.println("key in search: " + nodito.getLlaves().get(i).getLlave());
              //  System.out.println("done");
                if (!nodito.isLeaf()) {
                    BTreeSearch(nodito.getHijos().get(i), keys, positions);
                   // System.out.println("done 2");
                }
                i++;
            }
            if (!nodito.isLeaf()) {
                BTreeSearch(nodito.getHijos().get(i), keys, positions);
             //   System.out.println("done 3");
            }
        }

    }

    public void imprimir_arbol(int ina, int num) {
        Node nodo_actual = nodos.get(ina);
        //se debe iniciar num en 0 a la hora de llamar el metodo
        String indent = new String(new char[1024]).replace('\0', ' ');
        for (int i = 0; i < nodo_actual.getN(); i++) {
            if (nodo_actual.isLeaf() == false && nodo_actual.getHijos().get(i) != null) {
                imprimir_arbol(nodo_actual.getHijos().get(i), num + 1);
            }
            if (nodo_actual.getLlaves().get(i) != null) {
                System.out.println(indent.substring(0, num * 4) + "llave: " + nodo_actual.getLlaves().get(i) + ", nivel:" + num);
            }

        }
        //si no es hoja, se llama el metodo recursivo pero ahora con su hijo
        if (nodo_actual.isLeaf() == false) {
            imprimir_arbol(nodo_actual.getHijos().get(nodo_actual.getN()), num + 1);
        }
    }
}
