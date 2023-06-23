package ge.shota.blog.util;


public class ValidateUtil {

    public static boolean validateUserName(String userName) {

        if (userName == null || userName.isEmpty()) {
            return false;
        }

        // Username must be at least 3 characters long
        if (userName.length() < 3) {
            return false;
        }

        // Username must consist of only alphanumeric characters (letters and digits)
        return userName.matches("[a-zA-Z0-9]+");

    }

}
