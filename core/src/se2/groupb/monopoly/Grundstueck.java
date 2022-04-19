package se2.groupb.monopoly;

public class Grundstueck {
    private int id;
    private String name;
    private boolean kaufbar;
    private boolean verkauft;
    private int hauser;
    private int hotel;
    private int miete;
    private int miete1Haus;
    private int miete2Haus;
    private int mieteHotel;

    public Grundstueck(int id, String name, boolean kaufbar, boolean verkauft, int hauser, int hotel, int miete, int miete1Haus, int miete2Haus, int mieteHotel) {
        this.id = id;
        this.name = name;
        this.kaufbar = kaufbar;
        this.verkauft = verkauft;
        this.hauser = hauser;
        this.hotel = hotel;
        this.miete = miete;
        this.miete1Haus = miete1Haus;
        this.miete2Haus = miete2Haus;
        this.mieteHotel = mieteHotel;
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

    public boolean isKaufbar() {
        return kaufbar;
    }

    public void setKaufbar(boolean kaufbar) {
        this.kaufbar = kaufbar;
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

    public int getMiete1Haus() {
        return miete1Haus;
    }

    public void setMiete1Haus(int miete1Haus) {
        this.miete1Haus = miete1Haus;
    }

    public int getMiete2Haus() {
        return miete2Haus;
    }

    public void setMiete2Haus(int miete2Haus) {
        this.miete2Haus = miete2Haus;
    }

    public int getMieteHotel() {
        return mieteHotel;
    }

    public void setMieteHotel(int mieteHotel) {
        this.mieteHotel = mieteHotel;
    }
}
