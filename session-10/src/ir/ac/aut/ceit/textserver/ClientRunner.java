package ir.ac.aut.ceit.textserver;

public class ClientRunner {
    public static void main(String[] args) {
        Thread client = new Thread(new Client(), "client");
        client.start();
    }
}
