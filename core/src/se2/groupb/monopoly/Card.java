package se2.groupb.monopoly;


public class Card {
    int id;
    String name;

    public Card(){
        /**
         * not used
         */
    }

    public Card(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
