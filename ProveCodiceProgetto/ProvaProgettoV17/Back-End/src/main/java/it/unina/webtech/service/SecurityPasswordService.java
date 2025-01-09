package it.unina.webtech.service;
import org.mindrot.jbcrypt.BCrypt;

public class SecurityPasswordService {

    // Costruttore privato per impedire l'istanziazione
    private SecurityPasswordService() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


    public static boolean checkPassword(String passwordInseritaUtente, String storedPassword) {
        return BCrypt.checkpw(passwordInseritaUtente, storedPassword);
    }
}
