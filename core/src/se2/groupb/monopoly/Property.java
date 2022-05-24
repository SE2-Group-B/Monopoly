package se2.groupb.monopoly;

public class Property {
    private String name;
    private int ownerId;


    public Property(String name) {
        this.name = name;
        this.ownerId = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerId() {return ownerId; }

    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }
}
