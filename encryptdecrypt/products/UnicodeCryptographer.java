package encryptdecrypt.products;

public class UnicodeCryptographer extends Cryptographer {

    /**
     * The constructor passes the array to the super class constructor
     * @param chars symbols array
     * @throws NullPointerException if chars is null
     */
    public UnicodeCryptographer(char[] chars) {
        super(chars);
    }

    /**
     * Performs encoding by shifting within the unicode range
     * @param key key for encoding
     * @return encoded array of symbols
     */
    @Override
    public char[] encode(int key) {
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (key + (int) chars[i]);
        }
        return chars.clone();
    }

    /**
     * Performs decoding by shifting within the unicode range
     * @param key key for decoding
     * @return decoded array of symbols
     */
    @Override
    public char[] decode(int key) {
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (-key + (int) chars[i]);
        }
        return chars.clone();
    }
}
