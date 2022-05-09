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
        //Spielfigur.decreaseMoney(getHausPreis());
        increasemiete();
    }

    public int getHotel() {
        return hotel;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
        //Spielfigur.decreaseMoney(getHausPreis()*2);
        increasemiete();
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

    public void verkauft() {if(isVerkauft() == false) {setVerkauft(true);}else{errormsg();}}

    //number weg - man kann immer nur 1 Haus kaufen,
    //Es gibt max nur 2 Häuser
    //Haus bezahlen?
    //increasemiete aufrufen
    public void buyhouse(int number) {if(getHaus() < 4){setHaus(number);}else{errormsg();}}

    //Nur 2 Häuser
    //bezahlen?
    //Häuser 0 setzten
    //increasemiete aufrufen
    public void buyhotel() {if(getHaus() == 4 && getHotel() == 0) {setHotel(1);}else{errormsg();}}

    public boolean errormsg() {return false;}
    //Fehlermeldung als eigenes Fenster(Screen) im nächsten Sprint einfügen



    public void increasemiete() {
        int miete1 = getMiete();
        int haus = getHaus();
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

        setMiete(miete1);
    }

}
