package se2.groupb.monopoly.network;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

import java.util.ArrayList;

import se2.groupb.monopoly.CreateGameField;
import se2.groupb.monopoly.Player;
import se2.groupb.monopoly.Property;
import se2.groupb.monopoly.network.messages.PlayerInformation;
import se2.groupb.monopoly.network.messages.RoundCounter;

public class Network {

    // register objects that are sent over the Network
    public static void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(String.class);
        kryo.register(PlayerInformation.class);
        kryo.register(RoundCounter.class);
        kryo.register(Player.class);
        kryo.register(Integer.class);
        kryo.register(Boolean.class);
        kryo.register(Color.class);
        kryo.register(ArrayList.class);
        kryo.register(ModelInstance.class);
        kryo.register(Vector3.class);
        kryo.register(CreateGameField.class);
        kryo.register(Property.class);
    }
}
