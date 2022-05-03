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

    public void verkauft() {if(isVerkauft() == false) {setVerkauft(true); increaseMiete();}else{errormsg();}}

    public boolean errormsg() {return false;}
    //Fehlermeldung als eigenes Fenster(Screen) im nächsten Sprint einfügen

    public void increaseMiete() {
        int miete1 = getMiete();
        int anzahl = 0; //Spielfigur.getAnzahlBahnhoefe()

        if(anzahl == 1){
            miete1 = (int) (miete1 * 1);
        }else if(anzahl == 2){
            miete1 = (int) (miete1 + 50);
        }else if(anzahl == 3){
            miete1 = (int) (miete1 + 50);
        }else if(anzahl == 4){
            miete1 = (int) (miete1 + 50);
        }else if(anzahl == 0){
            return;
        }else{
            return;
        }

        setMiete(miete1);
    }
}
