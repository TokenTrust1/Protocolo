package client;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Client {
    // Mapeo para el cifrado y descifrado
    private static final Map<Character, String> mapping = new HashMap<>();
    private static final Map<String, Character> reverseMapping = new HashMap<>();

    static {
        mapping.put('a', "hola ");
        mapping.put('b', "si ");
        mapping.put('c', "no ");
        mapping.put('d', "estas ");
        mapping.put('e', "bien ");
        mapping.put('f', "y ");
        mapping.put('g', "tu ");
        mapping.put('h', "gracias ");
        mapping.put('i', "adios ");
        mapping.put('j', "hasta ");
        mapping.put('k', "buenos ");
        mapping.put('l', "love ");
        mapping.put('m', "puedo ");
        mapping.put('n', "hacerte ");
        mapping.put('ñ', "comer ");
        mapping.put('o', "voy ");
        mapping.put('p', "viajar ");
        mapping.put('q', "bienvenido ");
        mapping.put('r', "igualmente ");
        mapping.put('s', "la ");
        mapping.put('t', "show ");
        mapping.put('u', "otro ");
        mapping.put('v', "estas ");
        mapping.put('w', "wow ");
        mapping.put('x', "que ");
        mapping.put('y', "de ");
        mapping.put('z', "tu ");
        mapping.put('A', "comimos ");
        mapping.put('B', "el ");
        mapping.put('C', "ella ");
        mapping.put('D', "nosotros ");
        mapping.put('E', "hacemos ");
        mapping.put('F', "hacer ");
        mapping.put('G', "telefono ");
        mapping.put('H', "va ");
        mapping.put('I', "como ");
        mapping.put('J', "pronto ");
        mapping.put('K', "come ");
        mapping.put('L', "a ");
        mapping.put('M', "trabajo ");
        mapping.put('N', "huele ");
        mapping.put('Ñ', "le ");
        mapping.put('O', "estaba ");
        mapping.put('P', "buscando ");
        mapping.put('Q', "su ");
        mapping.put('R', "poco ");
        mapping.put('S', "tiempo ");
        mapping.put('T', "amigo ");
        mapping.put('U', "ven ");
        mapping.put('V', "te ");
        mapping.put('W', "invito ");
        mapping.put('X', "una ");
        mapping.put('Y', "copa ");
        mapping.put('Z', "tomo ");
        mapping.put('1', "hombre ");
        mapping.put('2', "mujer ");
        mapping.put('3', "señor ");
        mapping.put('4', "señora ");
        mapping.put('5', "niño ");
        mapping.put('6', "niña ");
        mapping.put('7', "chavo ");
        mapping.put('8', "chava ");
        mapping.put('9', "boca ");
        mapping.put('0', "cabeza ");
        mapping.put('.', "pie ");
        mapping.put(',', "mano ");
        mapping.put('(', "uña ");
        mapping.put(')', "pegue ");
        mapping.put('=', "pegar ");
        mapping.put('¿', "esta");
        mapping.put('?', "rico ");
        mapping.put('¡', "rica ");
        mapping.put('!', "estas ");
        mapping.put('&', "amiga ");
        mapping.put('$', "conocido ");
        mapping.put('#', "conocida ");
        mapping.put('+', "colega ");
        mapping.put('*', "olvido ");
        mapping.put('<', "ser ");
        mapping.put('>', "perro ");
        mapping.put('-', "entre ");
        mapping.put('_', "finde ");
        mapping.put(';', "hoy ");
        mapping.put('/', "año ");
        for (Map.Entry<Character, String> entry : mapping.entrySet()) {
            reverseMapping.put(entry.getValue(), entry.getKey());
        }
    }

    // Función para cifrar un mensaje
    private static String encrypt(String message) {
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
    private static String decrypt(String encryptedMessage) {
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

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 23456);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String message;
            while (true) {
                System.out.print("Tú (Cliente): ");
                String originalMessage = userInput.readLine();
                originalMessage = originalMessage.replaceAll("\\s+", " "); // Eliminar espacios innecesarios
                String encryptedMessage = encrypt(originalMessage);
                out.println(encryptedMessage);

                // Recibe la respuesta del servidor y la descifra
                String response = in.readLine();
                String decryptedResponse = decrypt(response);

                System.out.println("Servidor: " + decryptedResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
