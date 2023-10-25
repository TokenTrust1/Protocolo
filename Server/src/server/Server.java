package server;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(23456);
            System.out.println("Servidor esperando conexiones en el puerto 12345...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress().getHostAddress());

                // Crear un hilo para manejar la comunicación con el cliente
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    // Mapping para el cifrado y descifrado
    private static final Map<String, String> mapping = new HashMap<>();
    private static final Map<String, String> reverseMapping = new HashMap<>();

    static {
        mapping.put("a", "hola");
        mapping.put("b", "si");
        mapping.put("c", "no");
        mapping.put("d", "estas");
        mapping.put("e", "bien");
        mapping.put("f", "y");
        mapping.put("g", "tu");
        mapping.put("h", "gracias");
        mapping.put("i", "adios");
        mapping.put("j", "hasta");
        mapping.put("k", "buenos");
        mapping.put("l", "love");
        mapping.put("m", "puedo");
        mapping.put("n", "hacerte");
        mapping.put("ñ", "comer");
        mapping.put("o", "voy");
        mapping.put("p", "viajar");
        mapping.put("q", "bienvenido");
        mapping.put("r", "igualmente");
        mapping.put("s", "la");
        mapping.put("t", "show");
        mapping.put("u", "otro");
        mapping.put("v", "estas");
        mapping.put("w", "wow");
        mapping.put("x", "que");
        mapping.put("y", "de");
        mapping.put("z", "tu");
        mapping.put("A", "comimos");
        mapping.put("B", "el");
        mapping.put("C", "ella");
        mapping.put("D", "nosotros");
        mapping.put("E", "hacemos");
        mapping.put("F", "hacer");
        mapping.put("G", "telefono");
        mapping.put("H", "va");
        mapping.put("I", "como");
        mapping.put("J", "pronto");
        mapping.put("K", "come");
        mapping.put("L", "a");
        mapping.put("M", "trabajo");
        mapping.put("N", "huele");
        mapping.put("Ñ", "le");
        mapping.put("O", "estaba");
        mapping.put("P", "buscando");
        mapping.put("Q", "su");
        mapping.put("R", "poco");
        mapping.put("S", "tiempo");
        mapping.put("T", "amigo");
        mapping.put("U", "ven");
        mapping.put("V", "te");
        mapping.put("W", "invito");
        mapping.put("X", "una");
        mapping.put("Y", "copa");
        mapping.put("Z", "tomo");
        mapping.put("1", "hombre");
        mapping.put("2", "mujer");
        mapping.put("3", "señor");
        mapping.put("4", "señora");
        mapping.put("5", "niño");
        mapping.put("6", "niña");
        mapping.put("7", "chavo");
        mapping.put("8", "chava");
        mapping.put("9", "boca");
        mapping.put("0", "cabeza");
        mapping.put(".", "pie");
        mapping.put(",", "mano");
        mapping.put("(", "uña");
        mapping.put(")", "pegue");
        mapping.put("=", "pegar");
        mapping.put("¿", "esta");
        mapping.put("?", "rico");
        mapping.put("¡", "rica");
        mapping.put("!", "estas");
        mapping.put("&", "amiga");
        mapping.put("$", "conocido");
        mapping.put("#", "conocida");
        mapping.put("+", "colega");
        mapping.put("*", "olvido");
        mapping.put("<", "ser");
        mapping.put(">", "perro");
        mapping.put("-", "entre ");
        mapping.put("_", "finde ");
        mapping.put(";", "hoy");
        mapping.put("/", "año");

        // Crea un mapeo inverso para descifrar
        for (Map.Entry<String, String> entry : mapping.entrySet()) {
            reverseMapping.put(entry.getValue(), entry.getKey());
        }
    }


    public ClientHandler(Socket socket) {
        clientSocket = socket;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Función para cifrar un mensaje
    private String encrypt(String message) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (char character : message.toCharArray()) {
            if (mapping.containsKey(character)) {
                encryptedMessage.append(mapping.get(character));
            } else {
                encryptedMessage.append(character);
            }
        }
        return encryptedMessage.toString();
    }

    // Función para descifrar un mensaje
    private String decrypt(String encryptedMessage) {
        StringBuilder decryptedMessage = new StringBuilder();
        StringBuilder currentWord = new StringBuilder();
        boolean insideWord = false;

        for (char character : encryptedMessage.toCharArray()) {
            if (Character.isWhitespace(character)) {
                if (insideWord) {
                    if (reverseMapping.containsKey(currentWord.toString())) {
                        decryptedMessage.append(reverseMapping.get(currentWord.toString()));
                    } else {
                        decryptedMessage.append(currentWord.toString());
                    }
                    currentWord.setLength(0);
                }
                decryptedMessage.append(character);
                insideWord = false;
            } else {
                currentWord.append(character);
                insideWord = true;
            }
        }

        if (insideWord && reverseMapping.containsKey(currentWord.toString())) {
            decryptedMessage.append(reverseMapping.get(currentWord.toString()));
        } else {
            decryptedMessage.append(currentWord.toString());
        }

        return decryptedMessage.toString();
    }


    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Cliente (cifrado): " + message);

                // Enviar el mensaje cifrado de vuelta al cliente
                out.println(message);

                // Descifrar el mensaje y mostrarlo en el servidor
                String decryptedMessage = decrypt(message);
                decryptedMessage = decryptedMessage.replaceAll(" ", " "); // Eliminar espacios innecesarios
                System.out.println("Cliente (descifrado): " + decryptedMessage);
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
