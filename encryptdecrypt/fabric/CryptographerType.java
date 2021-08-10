package encryptdecrypt.fabric;


/**
 * An enumeration allows the factory to determine which class to return in accordance with the algorithm specified in the enumeration.
 */
public enum CryptographerType {
     SHIFT("shift"), UNICODE("unicode");

     /**
      * Algorithm string representation
      */
     private String str;

     /**
      * {@code str} field initialization
      */
     CryptographerType(String str) {
          this.str = str;
     }

     /**
      * Enumerates all the enumeration values and returns a value containing the field {@code str} value equal to the passed argument {@code sting}
      * @param string string to search for among values containing the {@code str} field equal to the given argument
      * @return a value containing the field {@code str} value equal to the passed argument {@code sting}
      */
     public static CryptographerType fromString(String string) {
          for (CryptographerType type : CryptographerType.values()) {
               if (type.str.equalsIgnoreCase(string)) return type;
          }
          return null;
     }
}
