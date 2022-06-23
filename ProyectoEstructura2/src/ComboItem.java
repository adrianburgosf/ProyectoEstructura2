/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author eagui
 */
public class ComboItem {
    private String key;
    private int value;

   

    public String getCampo() {
        return key;
    }

    public void setCampo(String campo) {
        this.key = campo;
    }

    public int getPos() {
        return value;
    }

    public void setPos(int pos) {
        this.value = pos;
    }

    public ComboItem(String campo, int pos) {
        this.key = campo;
        this.value = pos;
    }
    
    
    @Override
    public String toString()
    {
        return key;
    }
    
}
