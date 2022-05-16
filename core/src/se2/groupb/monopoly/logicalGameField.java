package se2.groupb.monopoly;

public class logicalGameField {
    private Grundstueck[] gameField;

    public Grundstueck[] logicalGameField() {
        this.gameField = new Grundstueck[40];
        gameField[0]=new Grundstueck("Los");
        gameField[1]=new Strasse("Badstraße", 40,false, 0, 0, 10,  50);
        gameField[2]=new Grundstueck("Gemeinschaftsfeld");
        gameField[3]=new Strasse("Turmstraße", 80,false, 0, 0, 20,  50);
        gameField[4]=new Zahlfeld("Einkommenssteuer", 200);
        gameField[5]=new Bahnhof("Südbahnhof", false, 50);
        gameField[6]=new Strasse("Chausseestraße", 120,false, 0, 0, 30,  50);
        gameField[7]=new Grundstueck("Ereignisfeld");
        gameField[8]=new Strasse("Elisenstraße", 120,false, 0, 0, 30,  50);
        gameField[9]=new Strasse("Poststraße", 160,false, 0, 0, 35,  50);
        gameField[10]=new Grundstueck("Gefängnis");
        gameField[11]=new Strasse("Seestraße", 200,false, 0, 0, 60,  100);
        gameField[12]=new Grundstueck("Ereignisfeld");
        gameField[13]=new Strasse("Hafenstraße", 200,false, 0, 0, 70,  100);
        gameField[14]=new Strasse("Neue Straße", 240,false, 0, 0, 80,  100);
        gameField[15]=new Bahnhof("Westbahnhof", false, 50);
        gameField[16]=new Strasse("Münchner Straße", 280,false, 0, 0, 85,  100);
        gameField[17]=new Grundstueck("Gemeinschaftsfeld");
        gameField[18]=new Strasse("Wiener Straße", 280,false, 0, 0, 90,  100);
        gameField[19]=new Strasse("Berliner Straße", 320,false, 0, 0, 95,  100);
        gameField[20]=new Grundstueck("Sofa");
        gameField[21]=new Strasse("Theater Straße", 360,false, 0, 0, 100,  150);
        gameField[22]=new Grundstueck("Ereignisfeld");
        gameField[23]=new Strasse("Museumsstraße", 360,false, 0, 0, 110,  150);
        gameField[24]=new Strasse("Opernplatz", 400,false, 0, 0, 115,  150);
        gameField[25]=new Bahnhof("Nordbahnhof", false, 50);
        gameField[26]=new Strasse("Lessingstraße", 440,false, 0, 0, 120,  150);
        gameField[27]=new Strasse("Schillerstraße", 440,false, 0, 0, 122,  150);
        gameField[28]=new Grundstueck("Gemeinschaftsfeld");
        gameField[29]=new Strasse("Goethestraße", 480,false, 0, 0, 130,  150);
        gameField[30]=new Grundstueck("Gehe ins Gefängnis");
        gameField[31]=new Strasse("Rathausplatz", 520,false, 0, 0, 150,  200);
        gameField[32]=new Strasse("Hauptstraße", 520,false, 0, 0, 155,  200);
        gameField[33]=new Grundstueck("Gemeinschaftsfeld");
        gameField[34]=new Strasse("Bahnhofstraße", 560,false, 0, 0, 160,  200);
        gameField[35]=new Bahnhof("Hauptbahnhof", false, 50);
        gameField[36]=new Grundstueck("Ereignisfeld");
        gameField[37]=new Strasse("Parkstraße", 650,false, 0, 0, 250,  200);
        gameField[38]= new Zahlfeld("Zusatzsteuer", 200);
        gameField[39]=new Strasse("Schlossallee", 800,false, 0, 0, 350,  200);
        return gameField;
    }

    public Grundstueck[] getGameField() {
        return gameField;
    }

    public void setGameField(Grundstueck[] gameField) {
        this.gameField = gameField;
    }

}
