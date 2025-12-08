package main.java.com.journalapp.util;

public class Cipher {
    private static final int offset = 13;

    /**
     * Decode the given string using Caesar Cipher
     * @param e string to be decoded
     * @return decoded string
     */
    public static String decode(String e) {
        return rotate(e, -offset);
    }

    /**
     * Encode the given string using Caesar Cipher
     * @param d string to be encoded
     * @return encoded string
     */
    public static String encode(String d) {
        return rotate(d, +offset);
    }

    /**
     * Perform rotation the given string.
     * @param s string to be rotated
     * @param offset positive or negative integer representing
     *              the shift value
     * @return rotated string
     */
    private static String rotate(String s, int offset) {
        StringBuilder e = new StringBuilder();
        for (char c : s.toCharArray()) {
            e.append((char)(c + offset));
        }
        return e.toString();
    }

    /**
     * Demonstrate the usage of encode and decode method,
     * and verifies that the process is reversible.
     */
    public static void main(String[] args) {
        String pass = "hello, world!";
        String encoded = encode(pass);
        System.out.println(encoded);
        System.out.println(decode(encoded));
        System.out.println(decode(encode(pass)).equals(pass));
    }
}
