package se2.groupb.monopoly;

public class GameLogic {
    Grundstueck[] spielfeld = new Grundstueck[40];
    public void setLogicArray() {
        //erzeuge Spielfeld:
        spielfeld[0] = new Grundstueck("Los");

        spielfeld[1] = new Strasse("Badstraße", 40,false, 0, 0, 10,  50);

        Grundstueck Gemeinschaftsfeld=new Grundstueck("Gemeinschaftsfeld");
        spielfeld[2]=Gemeinschaftsfeld;
        Grundstueck Turmstraße=new Strasse("Turmstraße", 80,false, 0, 0, 20,  50);
        spielfeld[3]=Turmstraße;
        Grundstueck Einkommensteuer=new Zahlfeld("Einkommenssteuer", 200);
        spielfeld[4]=Einkommensteuer;
        Grundstueck Südbahnhof=new Bahnhof("Südbahnhof", false, 50);
        spielfeld[5]=Südbahnhof;
        Grundstueck Chausseestraße=new Strasse("Chausseestraße", 120,false, 0, 0, 30,  50);
        spielfeld[6]=Chausseestraße;
        Grundstueck Ereignisfeld=new Grundstueck("Ereignisfeld");
        spielfeld[7]=Ereignisfeld;
        Grundstueck Elisenstraße=new Strasse("Elisenstraße", 120,false, 0, 0, 30,  50);
        spielfeld[8]=Elisenstraße;
        Grundstueck Poststraße=new Strasse("Poststraße", 160,false, 0, 0, 35,  50);
        spielfeld[9]=Poststraße;
        Grundstueck Gefängnis=new Grundstueck("Gefängnis");
        spielfeld[10]=Gefängnis;
        Grundstueck Seestraße=new Strasse("Seestraße", 200,false, 0, 0, 60,  100);
        spielfeld[11]=Seestraße;
        spielfeld[12]=Ereignisfeld;
        Grundstueck Hafenstraße=new Strasse("Hafenstraße", 200,false, 0, 0, 70,  100);
        spielfeld[13]=Hafenstraße;
        Grundstueck NeueStraße=new Strasse("Neue Straße", 240,false, 0, 0, 80,  100);
        spielfeld[14]=NeueStraße;
        Grundstueck Westbahnhof=new Bahnhof("Westbahnhof", false, 50);
        spielfeld[15]=Westbahnhof;
        Grundstueck MünchnerStraße=new Strasse("Münchner Straße", 280,false, 0, 0, 85,  100);
        spielfeld[16]=MünchnerStraße;
        spielfeld[17]=Gemeinschaftsfeld;
        Grundstueck WienerStraße=new Strasse("Wiener Straße", 280,false, 0, 0, 90,  100);
        spielfeld[18]=WienerStraße;
        Grundstueck BerlinerStraße=new Strasse("Berliner Straße", 320,false, 0, 0, 95,  100);
        spielfeld[19]=BerlinerStraße;
        Grundstueck Sofa=new Grundstueck("Sofa");
        spielfeld[20]=Sofa;
        Grundstueck TheaterStraße=new Strasse("Theater Straße", 360,false, 0, 0, 100,  150);
        spielfeld[21]=TheaterStraße;
        spielfeld[22]=Ereignisfeld;
        Grundstueck Museumsstraße=new Strasse("Museumsstraße", 360,false, 0, 0, 110,  150);
        spielfeld[23]=Museumsstraße;
        Grundstueck Opernplatz=new Strasse("Opernplatz", 400,false, 0, 0, 115,  150);
        spielfeld[24]=Opernplatz;
        Grundstueck Nordbahnhof=new Bahnhof("Nordbahnhof", false, 50);
        spielfeld[25]=Nordbahnhof;
        Grundstueck Lessingstraße=new Strasse("Lessingstraße", 440,false, 0, 0, 120,  150);
        spielfeld[26]=Lessingstraße;
        Grundstueck Schillerstraße=new Strasse("Schillerstraße", 440,false, 0, 0, 122,  150);
        spielfeld[27]=Schillerstraße;
        spielfeld[28]=Gemeinschaftsfeld;
        Grundstueck Goethestraße=new Strasse("Goethestraße", 480,false, 0, 0, 130,  150);
        spielfeld[29]=Goethestraße;
        Grundstueck GeheInsGefaengnis=new Grundstueck("Gehe ins Gefängnis");
        spielfeld[30]=GeheInsGefaengnis;
        Grundstueck Rathausplatz=new Strasse("Rathausplatz", 520,false, 0, 0, 150,  200);
        spielfeld[31]=Rathausplatz;
        Grundstueck Hauptstraße=new Strasse("Hauptstraße", 520,false, 0, 0, 155,  200);
        spielfeld[32]=Hauptstraße;
        spielfeld[33]=Gemeinschaftsfeld;
        Grundstueck Bahnhofstraße=new Strasse("Bahnhofstraße", 560,false, 0, 0, 160,  200);
        spielfeld[34]=Bahnhofstraße;
        Grundstueck Hauptbahnhof=new Bahnhof("Hauptbahnhof", false, 50);
        spielfeld[35]=Hauptbahnhof;
        spielfeld[36]=Ereignisfeld;
        Grundstueck Parkstraße=new Strasse("Parkstraße", 650,false, 0, 0, 250,  200);
        spielfeld[37]=Parkstraße;
        Grundstueck Zusatzsteuer = new Zahlfeld("Zusatzsteuer", 200);
        spielfeld[38]=Zusatzsteuer;
        Grundstueck Schlossallee=new Strasse("Schlossallee", 800,false, 0, 0, 350,  200);
        spielfeld[39]=Schlossallee;


    }
    public Grundstueck[] getLogicArray() {
        setLogicArray();
        return spielfeld;
    }


}
