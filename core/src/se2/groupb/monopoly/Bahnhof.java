package se2.groupb.monopoly;

public class Bahnhof extends Grundstueck{
    private boolean verkauft;
    private int kaufpreis;
    private int miete;

    public Bahnhof(String name, boolean verkauft, int miete) {
        super(name);
        this.verkauft = verkauft;
        this.kaufpreis=500;
        this.miete=miete;
    }

    public boolean isVerkauft() {
        return verkauft;
    }

    public void setVerkauft(boolean verkauft) {
        this.verkauft = verkauft;
    }

    public int getKaufpreis() {
        return kaufpreis;
    }

    public void setKaufpreis(int kaufpreis) {
        this.kaufpreis = kaufpreis;
    }

    public int getMiete() {
        return miete;
    }

    public void setMiete(int miete) {
        this.miete = miete;
    }
}
