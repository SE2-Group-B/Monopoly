package se2.groupb.monopoly.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

public class ClientFoundation {
    private Client client;
    static final int tcpPort = 6333;
    static final int udpPort = 6334;


    public ClientFoundation() {
        this.client = new Client();
        startConnection(client, "localhost", tcpPort, udpPort, 100);
    }

    private void startConnection(Client client, String host, int tcpPort, int udpPort, int maxBlockingTime){
        client.start();
        try {
            client.connect(maxBlockingTime, host, tcpPort, udpPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerToKryo(){
        Kryo kryo = getClient().getKryo();
        // register request and response
        kryo.register(String.class);

    }

    public Client getClient() {
        return client;
    }

}
