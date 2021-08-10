package encryptdecrypt.fabric;

import encryptdecrypt.products.*;

/**
 * The factory class allows you to use its static methods to get a specific product class
 */
public class CryptographersFabric {

    /**
     *
     * @param type cryptographer type
     * @param chars array of symbols for initializing cryptographer
     * @return new cryptographer of interface {@code Cryptographer}
     */
    public static Cryptographer getCryptographer(CryptographerType type, char[] chars) {
        switch (type) {
            case UNICODE: return new UnicodeCryptographer(chars);
            default: return new ShiftCryptographer(chars);
        }
    }
}
