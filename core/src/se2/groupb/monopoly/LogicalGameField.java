package se2.groupb.monopoly;

public class LogicalGameField {
    private Property[] gameField;

    public LogicalGameField() {
        this.gameField = new Property[40];
        gameField[0]=new Property("Los");
        gameField[1]=new Street("Badstraße", 40,false, 0, 0, 10,  50);
        gameField[2]=new Property("Gemeinschaftsfeld");
        gameField[3]=new Street("Turmstraße", 80,false, 0, 0, 20,  50);
        gameField[4]=new PenaltyField("Einkommenssteuer", 200);
        gameField[5]=new Trainstation("Südbahnhof", false, 50);
        gameField[6]=new Street("Chausseestraße", 120,false, 0, 0, 30,  50);
        gameField[7]=new Property("Ereignisfeld");
        gameField[8]=new Street("Elisenstraße", 120,false, 0, 0, 30,  50);
        gameField[9]=new Street("Poststraße", 160,false, 0, 0, 35,  50);
        gameField[10]=new Property("Gefängnis");
        gameField[11]=new Street("Seestraße", 200,false, 0, 0, 60,  100);
        gameField[12]=new Property("Ereignisfeld");
        gameField[13]=new Street("Hafenstraße", 200,false, 0, 0, 70,  100);
        gameField[14]=new Street("Neue Straße", 240,false, 0, 0, 80,  100);
        gameField[15]=new Trainstation("Westbahnhof", false, 50);
        gameField[16]=new Street("Münchner Straße", 280,false, 0, 0, 85,  100);
        gameField[17]=new Property("Gemeinschaftsfeld");
        gameField[18]=new Street("Wiener Straße", 280,false, 0, 0, 90,  100);
        gameField[19]=new Street("Berliner Straße", 320,false, 0, 0, 95,  100);
        gameField[20]=new Property("Sofa");
        gameField[21]=new Street("Theater Straße", 360,false, 0, 0, 100,  150);
        gameField[22]=new Property("Ereignisfeld");
        gameField[23]=new Street("Museumsstraße", 360,false, 0, 0, 110,  150);
        gameField[24]=new Street("Opernplatz", 400,false, 0, 0, 115,  150);
        gameField[25]=new Trainstation("Nordbahnhof", false, 50);
        gameField[26]=new Street("Lessingstraße", 440,false, 0, 0, 120,  150);
        gameField[27]=new Street("Schillerstraße", 440,false, 0, 0, 122,  150);
        gameField[28]=new Property("Gemeinschaftsfeld");
        gameField[29]=new Street("Goethestraße", 480,false, 0, 0, 130,  150);
        gameField[30]=new Property("Gehe ins Gefängnis");
        gameField[31]=new Street("Rathausplatz", 520,false, 0, 0, 150,  200);
        gameField[32]=new Street("Hauptstraße", 520,false, 0, 0, 155,  200);
        gameField[33]=new Property("Gemeinschaftsfeld");
        gameField[34]=new Street("Bahnhofstraße", 560,false, 0, 0, 160,  200);
        gameField[35]=new Trainstation("Hauptbahnhof", false, 50);
        gameField[36]=new Property("Ereignisfeld");
        gameField[37]=new Street("Parkstraße", 650,false, 0, 0, 250,  200);
        gameField[38]= new PenaltyField("Zusatzsteuer", 200);
        gameField[39]=new Street("Schlossallee", 800,false, 0, 0, 350,  200);
    }

    public Property[] getGameField() {
        return gameField;
    }

    public void setGameField(Property[] gameField) {
        this.gameField = gameField;
    }

}
