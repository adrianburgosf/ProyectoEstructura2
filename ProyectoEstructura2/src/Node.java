
import java.io.Serializable;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author eagui
 */
public class Node implements Serializable{
    
    ArrayList<Keys> llaves;
    ArrayList<Integer> hijos;
    int n;
    boolean leaf;
    
    public Node (int x){
        llaves = new ArrayList<>();
        hijos = new ArrayList<>();
        for (int i = 0; i < x-1; i++) {
            llaves.add(null);
            hijos.add(-1);
        }
        hijos.add(-1);
        n = 0;
        leaf = true;
    }

    public ArrayList<Keys> getLlaves() {
        return llaves;
    }

    public void setLlaves(ArrayList<Keys> llaves) {
        this.llaves = llaves;
    }

    public ArrayList<Integer> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Integer> hijos) {
        this.hijos = hijos;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }
    
    
}
