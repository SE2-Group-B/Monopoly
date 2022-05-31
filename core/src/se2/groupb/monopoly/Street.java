package se2.groupb.monopoly;

public class Street extends Property {

    private int price;
    private boolean sold;
    private int house;//0,1,2
    private int hotel;//0,1
    private int rent;
    private int housePrice;

    public Street(String name, int price, boolean sold, int house, int hotel, int rent, int housePrice) {
        super(name);
        this.price = price;
        this.sold = sold;
        this.house = house;
        this.hotel = hotel;
        this.rent = rent;
        this.housePrice = housePrice;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
        increasemiete();
    }

    public int getHotel() {
        return hotel;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
        //player1.decreaseMoney(getHausPreis()*2);
        increasemiete();
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(int housePrice) {
        this.housePrice = housePrice;
    }

    public boolean buyhouse() {
        if(getHouse() < 2){
            setHouse(getHouse()+1); increasemiete();
            return true;
        }else if(getHouse() == 2 && getHotel() == 0) {
            setHotel(1); setHouse(0); increasemiete();
            return true;
        }
        else{return false;}
    }


    public void increasemiete() {
        int miete1 = getRent();
        int haus = getHouse();
        int hotel = getHotel();

        if(haus == 1 && hotel == 0) {
            miete1 = (int) (miete1 * 1.2);
        }else if(haus == 2 && hotel == 0){
            miete1 = (int) (miete1 * 1.4);
        }else if(hotel == 1){
            miete1 = (int) (miete1 * 1.8);
        }else{
            return;
        }

        setRent(miete1);
    }

}
