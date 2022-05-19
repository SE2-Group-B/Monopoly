package se2.groupb.monopoly;

public class GameLogic {
    Property[] spielfeld = new Property[40];
    public void setLogicArray() {
        //erzeuge Spielfeld:
        spielfeld[0] = new Property("Los");

        spielfeld[1] = new Street("Badstraße", 40,false, 0, 0, 10,  50);

        Property Gemeinschaftsfeld=new Property("Gemeinschaftsfeld");
        spielfeld[2]=Gemeinschaftsfeld;
        Property Turmstraße=new Street("Turmstraße", 80,false, 0, 0, 20,  50);
        spielfeld[3]=Turmstraße;
        Property Einkommensteuer=new PenaltyField("Einkommenssteuer", 200);
        spielfeld[4]=Einkommensteuer;
        Property Südbahnhof=new Trainstation("Südbahnhof", false, 50);
        spielfeld[5]=Südbahnhof;
        Property Chausseestraße=new Street("Chausseestraße", 120,false, 0, 0, 30,  50);
        spielfeld[6]=Chausseestraße;
        Property Ereignisfeld=new Property("Ereignisfeld");
        spielfeld[7]=Ereignisfeld;
        Property Elisenstraße=new Street("Elisenstraße", 120,false, 0, 0, 30,  50);
        spielfeld[8]=Elisenstraße;
        Property Poststraße=new Street("Poststraße", 160,false, 0, 0, 35,  50);
        spielfeld[9]=Poststraße;
        Property Gefängnis=new Property("Gefängnis");
        spielfeld[10]=Gefängnis;
        Property Seestraße=new Street("Seestraße", 200,false, 0, 0, 60,  100);
        spielfeld[11]=Seestraße;
        spielfeld[12]=Ereignisfeld;
        Property Hafenstraße=new Street("Hafenstraße", 200,false, 0, 0, 70,  100);
        spielfeld[13]=Hafenstraße;
        Property NeueStraße=new Street("Neue Straße", 240,false, 0, 0, 80,  100);
        spielfeld[14]=NeueStraße;
        Property Westbahnhof=new Trainstation("Westbahnhof", false, 50);
        spielfeld[15]=Westbahnhof;
        Property MünchnerStraße=new Street("Münchner Straße", 280,false, 0, 0, 85,  100);
        spielfeld[16]=MünchnerStraße;
        spielfeld[17]=Gemeinschaftsfeld;
        Property WienerStraße=new Street("Wiener Straße", 280,false, 0, 0, 90,  100);
        spielfeld[18]=WienerStraße;
        Property BerlinerStraße=new Street("Berliner Straße", 320,false, 0, 0, 95,  100);
        spielfeld[19]=BerlinerStraße;
        Property Sofa=new Property("Sofa");
        spielfeld[20]=Sofa;
        Property TheaterStraße=new Street("Theater Straße", 360,false, 0, 0, 100,  150);
        spielfeld[21]=TheaterStraße;
        spielfeld[22]=Ereignisfeld;
        Property Museumsstraße=new Street("Museumsstraße", 360,false, 0, 0, 110,  150);
        spielfeld[23]=Museumsstraße;
        Property Opernplatz=new Street("Opernplatz", 400,false, 0, 0, 115,  150);
        spielfeld[24]=Opernplatz;
        Property Nordbahnhof=new Trainstation("Nordbahnhof", false, 50);
        spielfeld[25]=Nordbahnhof;
        Property Lessingstraße=new Street("Lessingstraße", 440,false, 0, 0, 120,  150);
        spielfeld[26]=Lessingstraße;
        Property Schillerstraße=new Street("Schillerstraße", 440,false, 0, 0, 122,  150);
        spielfeld[27]=Schillerstraße;
        spielfeld[28]=Gemeinschaftsfeld;
        Property Goethestraße=new Street("Goethestraße", 480,false, 0, 0, 130,  150);
        spielfeld[29]=Goethestraße;
        Property GeheInsGefaengnis=new Property("Gehe ins Gefängnis");
        spielfeld[30]=GeheInsGefaengnis;
        Property Rathausplatz=new Street("Rathausplatz", 520,false, 0, 0, 150,  200);
        spielfeld[31]=Rathausplatz;
        Property Hauptstraße=new Street("Hauptstraße", 520,false, 0, 0, 155,  200);
        spielfeld[32]=Hauptstraße;
        spielfeld[33]=Gemeinschaftsfeld;
        Property Bahnhofstraße=new Street("Bahnhofstraße", 560,false, 0, 0, 160,  200);
        spielfeld[34]=Bahnhofstraße;
        Property Hauptbahnhof=new Trainstation("Hauptbahnhof", false, 50);
        spielfeld[35]=Hauptbahnhof;
        spielfeld[36]=Ereignisfeld;
        Property Parkstraße=new Street("Parkstraße", 650,false, 0, 0, 250,  200);
        spielfeld[37]=Parkstraße;
        Property Zusatzsteuer = new PenaltyField("Zusatzsteuer", 200);
        spielfeld[38]=Zusatzsteuer;
        Property Schlossallee=new Street("Schlossallee", 800,false, 0, 0, 350,  200);
        spielfeld[39]=Schlossallee;


    }




}
