package se2.groupb.monopoly;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Model;



public class Field {

    Model model;
    Color color;
    Type type;
    int rotation;

    public Field(Model model, Color color, Type type) {
        this.model = model;
        this.color = color;
        this.type = type;
    }

    public Model getModel() {
        return model;
    }


    public Color getFieldColors() {
        return color;
    }

    public void setFieldColors(Color color) {
        this.color = color;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
