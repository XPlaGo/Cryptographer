package encryptdecrypt.products;

import java.util.Objects;

/**
 *
 An abstract class serves as a template for creating classes for encoding and decoding information using various algorithms.
 */
abstract public class Cryptographer {
    /**
     * Array {@code char[] chars} contains symbols for methods {@code encode(int key)} and {@code decode(int key)}
     */
    protected final char[] chars;

    /**
     * Default constructor set array {@code char[] chars}
     * @param chars symbols array
     * @throws NullPointerException if chars is null
     */
    public Cryptographer(char[] chars) {
        Objects.requireNonNull(chars);
        this.chars = chars.clone();
    }

    /**
     * @param key key for encoding
     * @return encoded array of symbols
     */
    abstract public char[] encode(int key);

    /**
     * @param key key for decoding
     * @return decoded array of symbols
     */
    abstract public char[] decode(int key);
}
