package se2.groupb.monopoly;

public class Strasse extends Grundstueck{

    private int preis;
    private boolean verkauft;
    private int haus;//0,1,2
    private int hotel;//0,1
    private int miete;
    private int hausPreis;




    public Strasse(String name, int preis, boolean verkauft, int haus, int hotel, int miete, int hausPreis) {
        super(name);
        this.preis=preis;
        this.verkauft = verkauft;
        this.haus = haus;
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

    public int getHaus() {
        return haus;
    }

    public void setHaus(int haus) {
        this.haus = haus;
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

    public int getHausPreis() {
        return hausPreis;
    }

    public void setHausPreis(int hausPreis) {
        this.hausPreis = hausPreis;
    }

}
