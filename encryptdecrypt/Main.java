package encryptdecrypt;

import encryptdecrypt.fabric.CryptographerType;
import encryptdecrypt.fabric.CryptographersFabric;
import encryptdecrypt.products.Cryptographer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Main {

    /**
     * Processes program arguments and launches functions for generating, encoding/decoding and outputting information
     * @param args arguments with starting program
     */
    public static void main(String[] args) {
        File inputFile = null;
        File outputFile = null;
        ActionType mode = ActionType.ENCODE;
        int key = 0;
        String data = null;
        CryptographerType algorithm = CryptographerType.SHIFT;
        try {
            for (int i = 0; i < args.length; i++) {
                ArgumentType argument = ArgumentType.fromString(args[i]);
                switch (Objects.requireNonNull(argument)) {
                    case INPUT:
                        inputFile = new File(args[++i]);
                        break;
                    case OUTPUT:
                        outputFile = new File(args[++i]);
                        break;
                    case MODE:
                        mode = ActionType.fromString(args[++i]);
                        break;
                    case KEY:
                        key = Integer.parseInt(args[++i]);
                        break;
                    case DATA:
                        data = args[++i];
                        break;
                    case ALGORITHM:
                        algorithm = CryptographerType.fromString(args[++i]);
                        break;
                }
            }
            char[] chars = createArray(data, inputFile);
            Cryptographer cryptographer = CryptographersFabric.getCryptographer(algorithm, chars);
            switch (Objects.requireNonNull(mode)) {
                case ENCODE:
                    chars = cryptographer.encode(key);
                    break;
                case DECODE:
                    chars = cryptographer.decode(key);
                    break;
            }
            writeArray(chars, outputFile);
        } catch (IOException | NullPointerException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * Forms an array of characters from {@code data}, if it is not {@code null}, otherwise calls {@code readStringFromFile(File input)}
     * @param data string from program argument for encoding/decoding
     * @param input file for read symbols
     * @return formed character array
     * @throws IOException raised from {@code readStringFromFile(File input)}
     */
    private static char[] createArray(String data, File input) throws IOException {
        if (data != null) {
            return data.toCharArray();
        } else {
            return readStringFromFile(input);
        }
    }

    /**
     * Forms an array of characters read from a file
     * @param input file for read symbols
     * @return formed character array
     * @throws IOException from {@code FileInputStream}
     */
    private static char[] readStringFromFile(File input) throws IOException {
        Objects.requireNonNull(input);
        FileInputStream inputStream = new FileInputStream(input);
        String str = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        inputStream.close();
        return str.toCharArray();
    }

    /**
     * The method writes an array of characters to a file or standard output if the last argument is {@code null}
     * @param chars array of symbols
     * @param output array write file, {@code null} if to standard output
     * @throws IOException from FileWriter
     */
    private static void writeArray(char[] chars, File output) throws IOException {
        if (output != null) {
            FileWriter writer = new FileWriter(output);
            writer.write(chars);
            writer.close();
        } else {
            for (char symbol : chars) System.out.print(symbol);
            System.out.println();
        }
    }
}

/**
 * Represents an enumeration for defining the type of program argument
 */
enum ArgumentType {
    INPUT("-in"), OUTPUT("-out"), MODE("-mode"), KEY("-key"), DATA("-data"), ALGORITHM("-alg");

    /**
     * Program argument string representation
     */
    private final String str;

    /**
     * {@code str} field initialization
     */
    ArgumentType(String str) {
        this.str = str;
    }

    /**
     * Enumerates all the enumeration values and returns a value containing the field {@code str} value equal to the passed argument {@code sting}
     * @param string string to search for among values containing the {@code str} field equal to the given argument
     * @return a value containing the field {@code str} value equal to the passed argument {@code sting}
     */
    static ArgumentType fromString(String string) {
        for (ArgumentType type : ArgumentType.values()) {
            if (type.str.equalsIgnoreCase(string)) return type;
        }
        return null;
    }
}

/**
 * Represents an enumeration for defining an action with a cryptographer
 */
enum ActionType {
    ENCODE("enc"), DECODE("dec");

    /**
     * Cryptographer action string representation
     */
    private final String str;

    /**
     * {@code str} field initialization
     */
    ActionType(String str) {
        this.str = str;
    }

    /**
     * Enumerates all the enumeration values and returns a value containing the field {@code str} value equal to the passed argument {@code sting}
     * @param string string to search for among values containing the {@code str} field equal to the given argument
     * @return a value containing the field {@code str} value equal to the passed argument {@code sting}
     */
    static ActionType fromString(String string) {
        for (ActionType type : ActionType.values()) {
            if (type.str.equalsIgnoreCase(string)) return type;
        }
        return null;
    }
}