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

    public void increaseRent() {
        int rent1 = getRent();
        int anzahl = 0;



        if(anzahl == 1){
            rent1 = (int) (rent1 * 1.1);
        }else if(anzahl == 2){
            rent1 = (int) (rent1 + 50);
        }else if(anzahl == 3){
            rent1 = (int) (rent1 + 50);
        }else if(anzahl == 4){
            rent1 = (int) (rent1 + 50);
        }else if(anzahl == 0){
            return;
        }else{
            return;
        }

        setRent(rent1);
    }
}
