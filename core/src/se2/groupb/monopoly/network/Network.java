package se2.groupb.monopoly.network;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

import java.util.ArrayList;

import se2.groupb.monopoly.Card;
import se2.groupb.monopoly.CreateGameField;
import se2.groupb.monopoly.Deck;
import se2.groupb.monopoly.DiceRoll;
import se2.groupb.monopoly.LogicalGameField;
import se2.groupb.monopoly.PenaltyField;
import se2.groupb.monopoly.Player;
import se2.groupb.monopoly.PlayerOperation;
import se2.groupb.monopoly.Pot;
import se2.groupb.monopoly.Property;
import se2.groupb.monopoly.Street;
import se2.groupb.monopoly.Trainstation;
import se2.groupb.monopoly.network.messages.NextTurnMessage;
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
        kryo.register(NextTurnMessage.class);
        kryo.register(PlayerOperation.class);
        kryo.register(LogicalGameField.class);
        kryo.register(Street.class);
        kryo.register(Deck.class);
        kryo.register(PenaltyField.class);
        kryo.register(Trainstation.class);
        kryo.register(Card.class);
        kryo.register(Pot.class);
        kryo.register(DiceRoll.class);
        kryo.register(Texture.class);
        kryo.register(FileTextureData.class);
//        kryo.register(Property.class);

//        kryo.register(Array.class);
        kryo.register(Property[].class);
//        kryo.register(Material.class);
//        kryo.register(ColorAttribute.class);
//        kryo.register(FloatAttribute.class);
//        kryo.register(Array.ArrayIterable.class);
    }
}
