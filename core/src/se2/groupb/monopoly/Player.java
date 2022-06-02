package se2.groupb.monopoly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.JsonReader;

import java.util.ArrayList;

public class Player {
    private int id;
    private String name;
    private int position;
    private int bankBalance;
    private ArrayList<Property> myProperties;
    private int NumOfTrainstaitions;
    private Color color;
    ModelInstance modInstance;
    Vector3 fieldPos;
    private boolean prison = false;
    private int prisoncount = 0;

    private String buildingPath = "Spielfeld\\char.g3dj";
    private CreateGameField field;



    public Player(int id, String name, int bankBalance, ArrayList<Property> myProperties, int NumOfTrainstaitions, Color color) {
        this.id = id;
        this.name = name;
        this.bankBalance = bankBalance;
        this.myProperties = myProperties;
        this.NumOfTrainstaitions = NumOfTrainstaitions;
        this.position = 0;
        this.color = color;
    }



    public void createSpielfigur() { //Methode noch nicht getestet
        Model model = new G3dModelLoader(new JsonReader()).loadModel(Gdx.files.internal(buildingPath));
        modInstance = new ModelInstance(model);
        modInstance.materials.get(0).set(new ColorAttribute(ColorAttribute.Diffuse, getColor()));
        fieldPos = new Vector3(0, 3.2f, 0);
        modInstance.transform.translate(fieldPos);
    }

    public String payToOtherPlayer(Player p, int value){
        String output = "";
        if(this.getBankBalance() < value){
            output = this.getName() + " ist Bankrott.";
            //Auscheiden aus dem Spiel?! So kann er einfach weiter spielen
            this.setBankBalance(0);
            p.changeMoney(value);
        }else{
            this.changeMoney(-value);
            p.changeMoney(value);
            output = this.getName() + " stepped on " + p.getName() + "'s Property and payed " + value + " rent.";
        }
        return output;
    }

    public void move(Vector3 vector3) {
        this.fieldPos = vector3;
        modInstance.transform.setTranslation(vector3);
    }

    public Color getColor() { return color; }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(int bankBalance) {
        this.bankBalance = bankBalance;
    }

    public ArrayList<Property> getMyProperties() {
        return myProperties;
    }

    public void setMyProperties(ArrayList<Property> myProperties) {
        this.myProperties = myProperties;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumOfTrainstaitions() {
        return NumOfTrainstaitions;
    }

    public void setNumOfTrainstaitions(int numOfTrainstaitions) {
        this.NumOfTrainstaitions = numOfTrainstaitions;
    }

    public void changeMoney(int betrag) {
        setBankBalance(getBankBalance() + betrag);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public boolean getPrison() {
        return prison;
    }

    public void setPrison(boolean prison) {
        this.prison = prison;
    }

    public int getPrisonCount() {
        return prisoncount;
    }

    public void setPrisonCount(int prisoncount) {
        this.prisoncount = prisoncount;
    }

    public void move(int augenzahl) {
        if(!this.getPrison()) {
            if ((this.getPosition() + augenzahl) > 39) {
                roundmoney();
            }
            this.setPosition((this.getPosition() + augenzahl) % 40);
        }else{
            this.setPrisonCount(this.getPrisonCount()+1);
        }
        if(this.getPrisonCount() == 4){
            this.setPrison(false);
            this.setPrisonCount(0);

        }
    }

    public void goToJail(){
        this.setPosition(10);
        this.setPrison(true);
    }

    public void roundmoney() {
        this.setBankBalance((this.getBankBalance() + 200));
    }


    public Texture drawCard(Deck ks) {
        Card k = ks.getNextCard();
        Texture kartenbild = null;

        switch (k.getId()) {
            case 1:
                kartenbild = new Texture("images/KartenImages/Karte1.png");
                move(-3);
                break;
            case 2:
                kartenbild = new Texture("images/KartenImages/Karte2.png");
                setPosition(0);
                break;
            case 3:
                kartenbild = new Texture("images/KartenImages/Karte3.png");
                move(-10);
                break;
            case 4:
                kartenbild = new Texture("images/KartenImages/Karte4.png");
                move(10);
                break;
            case 5:
                kartenbild = new Texture("images/KartenImages/Karte5.png");
                move(6);
                break;
            case 6:
                kartenbild = new Texture("images/KartenImages/Karte6.png");
                changeMoney(-100);
                break;
            case 7:
                kartenbild = new Texture("images/KartenImages/Karte7.png");
                changeMoney(-100);
                break;
            case 8:
                kartenbild = new Texture("images/KartenImages/Karte8.png");
                changeMoney(-20);
                break;
            case 9:
                kartenbild = new Texture("images/KartenImages/Karte9.png");
                changeMoney(-40);
                break;
            case 10:
                kartenbild = new Texture("images/KartenImages/Karte10.png");
                changeMoney(-200);
                break;
            case 11:
                kartenbild = new Texture("images/KartenImages/Karte11.png");
                changeMoney(250);
                break;
            case 12:
                kartenbild = new Texture("images/KartenImages/Karte12.png");
                changeMoney(100);
                break;
            case 13:
                kartenbild = new Texture("images/KartenImages/Karte13.png");
                changeMoney(100);
                break;
            case 14:
                kartenbild = new Texture("images/KartenImages/Karte14.png");
                changeMoney(100);
                break;
            case 15:
                kartenbild = new Texture("images/KartenImages/Karte15.png");
                changeMoney(500);
                break;
            case 16:
                kartenbild = new Texture("images/KartenImages/Karte16.png");
                goToJail();
                break;
            case 17:
                kartenbild = new Texture("images/KartenImages/Karte17.png");
                setPosition(16);
                break;
            case 18:
                kartenbild = new Texture("images/KartenImages/Karte18.png");
                setPosition(24);
                break;
            case 19:
                kartenbild = new Texture("images/KartenImages/Karte19.png");
                setPosition(3);
                break;
            case 20:
                kartenbild = new Texture("images/KartenImages/Karte20.png");
                goToJail();
                break;
            case 21:
                kartenbild = new Texture("images/KartenImages/Karte21.png");
                changeMoney(220);
                break;
            case 22:
                kartenbild = new Texture("images/KartenImages/Karte22.png");
                changeMoney(170);
                break;
            case 23:
                kartenbild = new Texture("images/KartenImages/Karte23.png");
                changeMoney(550);
                break;
            case 24:
                kartenbild = new Texture("images/KartenImages/Karte24.png");
                changeMoney(350);
                break;
            case 25:
                kartenbild = new Texture("images/KartenImages/Karte25.png");
                changeMoney(200);
                break;
            case 26:
                kartenbild = new Texture("images/KartenImages/Karte26.png");
                setPosition(0);
                break;
            case 27:
                kartenbild = new Texture("images/KartenImages/Karte27.png");
                changeMoney(300);
                break;
            case 28:
                kartenbild = new Texture("images/KartenImages/Karte28.png");
                setPosition(37);
                break;
            case 29:
                kartenbild = new Texture("images/KartenImages/Karte29.png");
                changeMoney(510);
                break;
            case 30:
                kartenbild = new Texture("images/KartenImages/Karte30.png");
                changeMoney(350);
                break;
            case 31:
                kartenbild = new Texture("images/KartenImages/Karte31.png");
                changeMoney(222);
                break;
            case 32:
                kartenbild = new Texture("images/KartenImages/Karte32.png");
                changeMoney(-250);
                break;
            case 33:
                kartenbild = new Texture("images/KartenImages/Karte33.png");
                changeMoney(-100);
                break;
            case 34:
                kartenbild = new Texture("images/KartenImages/Karte34.png");
                changeMoney(-250);
                break;
            case 35:
                kartenbild = new Texture("images/KartenImages/Karte35.png");
                changeMoney(-200);
                break;
            case 36:
                kartenbild = new Texture("images/KartenImages/Karte36.png");
                changeMoney(-100);
                break;
            default:
                break;

        }
        return kartenbild;
    }
}
