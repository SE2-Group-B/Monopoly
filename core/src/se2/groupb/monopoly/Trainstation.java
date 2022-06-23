package se2.groupb.monopoly;


public class Trainstation extends Property {
    private boolean sold;
    private int price;
    private int rent;

    public Trainstation(){
        /**
         * not used
         */
    }

    public Trainstation(String name, boolean sold, int rent) {
        super(name);
        this.sold = sold;
        this.price =500;
        this.rent = rent;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean verkauft) {
        this.sold = verkauft;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }
}
