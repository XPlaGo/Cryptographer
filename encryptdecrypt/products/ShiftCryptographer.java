package encryptdecrypt.products;

public class ShiftCryptographer extends Cryptographer {

    /**
     * @param chars an array of characters passed to the super class constructor
     * @throws NullPointerException if chars is null
     */
    public ShiftCryptographer(char[] chars) {
        super(chars);
    }

    /**
     * Performs encoding by shifting within the Latin alphabet
     * @param key key for encoding
     * @return encoded array of symbols
     */
    @Override
    public char[] encode(int key) {
        for (int i = 0; i < chars.length; i++) {
            char symbol = chars[i];
            if (((symbol <= 'z' && symbol >= 'a') || (symbol <= 'Z' && symbol >= 'A'))) {
                int startPoint = Character.toLowerCase(symbol) == symbol ? 97 : 65;
                chars[i] = (char) (startPoint + (key + (int) chars[i] - startPoint) % 26);
            }
        }
        return chars.clone();
    }

    /**
     * Performs decoding by shifting within the Latin alphabet
     * @param key key for decoding
     * @return decoded array of symbols
     */
    @Override
    public char[] decode(int key) {
        for (int i = 0; i < chars.length; i++) {
            char symbol = chars[i];
            if (((symbol <= 'z' && symbol >= 'a') || (symbol <= 'Z' && symbol >= 'A'))) {
                int startPoint = Character.toLowerCase(symbol) == symbol ? 122 : 90;
                chars[i] = (char) (startPoint + (-key + (int) chars[i] - startPoint) % 26);
            }
        }
        return chars.clone();
    }
}
