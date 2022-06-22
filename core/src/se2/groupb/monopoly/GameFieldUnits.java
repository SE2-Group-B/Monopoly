package se2.groupb.monopoly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.utils.JsonReader;

public class GameFieldUnits {
    private String buildingPath = "Spielfeld\\field.g3dj";
    private Field[] fields;

    public void createField(String n){
        switch("monopoly"){
            case "monopoly": createStandardFieldArray();
            default: break;
        }
    }

    private void createStandardFieldArray() {
        Field fields[] = new Field[40];

        Model model = new G3dModelLoader(new JsonReader()).loadModel(Gdx.files.internal(buildingPath));
        //Bot Side of Board
        //Color.Cyan = Corner -> debugging
        fields[0] = new Field(model, Color.CYAN, Type.CORNER);
        fields[1] = new Field(model, Color.BROWN, Type.BUILDING);
        fields[2] = new Field(model, null, Type.SPECIAL);
        fields[3] = new Field(model, Color.BROWN, Type.BUILDING);
        fields[4] = new Field(model, null, Type.SPECIAL);
        fields[5] = new Field(model, null, Type.SPECIAL);
        fields[6] = new Field(model, Color.WHITE, Type.BUILDING);
        fields[7] = new Field(model, null, Type.SPECIAL);
        fields[8] = new Field(model, Color.WHITE, Type.BUILDING);
        fields[9] = new Field(model, Color.WHITE, Type.BUILDING);
        fields[10] = new Field(model, Color.CYAN, Type.CORNER);
        // Left Side of Board
        fields[11] = new Field(model, Color.PURPLE, Type.BUILDING);
        fields[12] = new Field(model, null, Type.SPECIAL);
        fields[13] = new Field(model, Color.PURPLE, Type.BUILDING);
        fields[14] = new Field(model, Color.PURPLE, Type.BUILDING);
        fields[15] = new Field(model, null, Type.SPECIAL);
        fields[16] = new Field(model, Color.ORANGE, Type.BUILDING);
        fields[17] = new Field(model, null, Type.SPECIAL);
        fields[18] = new Field(model, Color.ORANGE, Type.BUILDING);
        fields[19] = new Field(model, Color.ORANGE, Type.BUILDING);
        // Top Side of Board
        fields[20] = new Field(model, Color.CYAN, Type.CORNER);
        fields[21] = new Field(model, Color.RED, Type.BUILDING);
        fields[22] = new Field(model, null, Type.SPECIAL);
        fields[23] = new Field(model, Color.RED, Type.BUILDING);
        fields[24] = new Field(model, Color.RED, Type.BUILDING);
        fields[25] = new Field(model, null, Type.SPECIAL);
        fields[26] = new Field(model, Color.YELLOW, Type.BUILDING);
        fields[27] = new Field(model, Color.YELLOW, Type.BUILDING);
        fields[28] = new Field(model, null, Type.SPECIAL);
        fields[29] = new Field(model, Color.YELLOW, Type.BUILDING);
        fields[30] = new Field(model, Color.CYAN, Type.CORNER);
        // Right Side of Board
        fields[31] = new Field(model, Color.GREEN, Type.BUILDING);
        fields[32] = new Field(model, Color.GREEN, Type.BUILDING);
        fields[33] = new Field(model, null, Type.SPECIAL);
        fields[34] = new Field(model, Color.GREEN, Type.BUILDING);
        fields[35] = new Field(model, null, Type.SPECIAL);
        fields[36] = new Field(model, null, Type.SPECIAL);
        fields[37] = new Field(model, Color.BLUE, Type.BUILDING);
        fields[38] = new Field(model, null, Type.SPECIAL);
        fields[39] = new Field(model, Color.BLUE, Type.BUILDING);
        this.fields = fields;
    }

    public Field[] getFields() {
        return fields;
    }
}
