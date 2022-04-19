package se2.groupb.monopoly;

public class Strasse extends Grundstueck{

    private int preis;
    private boolean verkauft;
    private int hauser;//0,1,2
    private int hotel;//0,1
    private int miete;
    private int hausPreis;



    public Strasse(int id, String name, int preis, boolean verkauft, int hauser, int hotel, int miete, int hausPreis) {
        super(id, name);
        this.preis=preis;
        this.verkauft = verkauft;
        this.hauser = hauser;
        this.hotel = hotel;
        this.miete = miete;
        this.hausPreis=hausPreis;

    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int  preis) {
        this.preis = preis;
    }
    public boolean isVerkauft() {
        return verkauft;
    }

    public void setVerkauft(boolean verkauft) {
        this.verkauft = verkauft;
    }

    public int getHauser() {
        return hauser;
    }

    public void setHauser(int hauser) {
        this.hauser = hauser;
    }

    public int getHotel() {
        return hotel;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
    }

    public int getMiete() {
        return miete;
    }

    public void setMiete(int miete) {
        this.miete = miete;
    }

}
