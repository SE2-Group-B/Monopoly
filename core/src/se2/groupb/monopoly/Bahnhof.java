package se2.groupb.monopoly;

public class Bahnhof extends Grundstueck{
    private boolean verkauft;
    private int miete;

    public Bahnhof(int id, String name, boolean verkauft, int miete) {
        super(id, name);
        this.verkauft = verkauft;
        this.miete=miete;
    }

    public boolean isVerkauft() {
        return verkauft;
    }

    public void setVerkauft(boolean verkauft) {
        this.verkauft = verkauft;
    }

    public int getMiete() {
        return miete;
    }

    public void setMiete(int miete) {
        this.miete = miete;
    }
}
