
import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author eagui
 */
public class Keys implements Serializable{
    private String llave;
    private long pos;

    public Keys(String llave, long pos) {
        this.llave = llave;
        this.pos = pos;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public long getPos() {
        return pos;
    }

    public void setPos(long pos) {
        this.pos = pos;
    }
    
    
    @Override
    public String toString() {
        return "{" + "llave=" + llave + ", pos=" + pos + '}';
    }
}
