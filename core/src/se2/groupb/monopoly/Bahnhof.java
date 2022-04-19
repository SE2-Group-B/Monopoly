package se2.groupb.monopoly;

public class Bahnhof extends Grundstueck{
    private boolean verkauft;
    private int miete1Bahnhof;
    private int miete2Bahnhof;
    private int miete3Bahnhof;
    private int miete4Bahnhof;

    public Bahnhof(int id, String name, boolean verkauft, int miete1Bahnhof, int miete2Bahnhof, int miete3Bahnhof, int miete4Bahnhof) {
        super(id, name);
        this.verkauft = verkauft;
        this.miete1Bahnhof = miete1Bahnhof;
        this.miete2Bahnhof = miete2Bahnhof;
        this.miete3Bahnhof = miete3Bahnhof;
        this.miete4Bahnhof = miete4Bahnhof;
    }

    public boolean isVerkauft() {
        return verkauft;
    }

    public void setVerkauft(boolean verkauft) {
        this.verkauft = verkauft;
    }

    public int getMiete1Bahnhof() {
        return miete1Bahnhof;
    }

    public void setMiete1Bahnhof(int miete1Bahnhof) {
        this.miete1Bahnhof = miete1Bahnhof;
    }

    public int getMiete2Bahnhof() {
        return miete2Bahnhof;
    }

    public void setMiete2Bahnhof(int miete2Bahnhof) {
        this.miete2Bahnhof = miete2Bahnhof;
    }

    public int getMiete3Bahnhof() {
        return miete3Bahnhof;
    }

    public void setMiete3Bahnhof(int miete3Bahnhof) {
        this.miete3Bahnhof = miete3Bahnhof;
    }

    public int getMiete4Bahnhof() {
        return miete4Bahnhof;
    }

    public void setMiete4Bahnhof(int miete4Bahnhof) {
        this.miete4Bahnhof = miete4Bahnhof;
    }
}
