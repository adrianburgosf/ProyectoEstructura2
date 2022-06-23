
public class Campo {
    private String name;
    private String type;
    private int size;
    private boolean primKey;
    private boolean secKey;
    
    public Campo(String name, String type, int size, boolean primKey) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.primKey = primKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isPrimKey() {
        return primKey;
    }

    public void setPrimKey(boolean primKey) {
        this.primKey = primKey;
    }

    @Override
    public String toString() {
       // return name + " - " + type + " - " + size + " - " + primKey;
       return name;
    }
}
