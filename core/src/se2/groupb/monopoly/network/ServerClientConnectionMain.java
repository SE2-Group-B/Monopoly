package se2.groupb.monopoly.network;

public class ServerClientConnectionMain {
    public static void main(String[] args) {
        ServerFoundation instance = new ServerFoundation();
        ClientFoundation clients = new ClientFoundation();
        clients.getClient().sendUDP("Hallo ich bin ein Client");
        clients.getClient().stop();
        instance.getServer().close();
    }
}
