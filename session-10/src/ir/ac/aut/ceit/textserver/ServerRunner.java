package ir.ac.aut.ceit.textserver;

public class ServerRunner {
    public static void main(String[] args) {
        Thread server = new Thread(new Server(), "server");
        server.start();
    }
}
