package se2.groupb.monopoly.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {
    // register objects that are sent over the Network
    static public void register (EndPoint endPoint){
        Kryo kryo = endPoint.getKryo();
        kryo.register(String.class);
        kryo.register(PlayerInformation.class);
    }
}
