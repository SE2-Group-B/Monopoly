package se2.groupb.monopoly.network;

import com.badlogic.gdx.graphics.Color;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

import java.util.ArrayList;

import se2.groupb.monopoly.Player;

public class Network {
    // register objects that are sent over the Network
    static public void register (EndPoint endPoint){
        Kryo kryo = endPoint.getKryo();
        kryo.register(String.class);
        kryo.register(PlayerInformation.class);
        kryo.register(RoundCounter.class);
        kryo.register(Player.class);
        kryo.register(Integer.class);
        kryo.register(Boolean.class);
        kryo.register(Color.class);
        kryo.register(ArrayList.class);
    }
}
