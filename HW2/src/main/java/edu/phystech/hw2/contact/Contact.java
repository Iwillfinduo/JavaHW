package edu.phystech.hw2.contact;
import java.util.regex.Pattern;
import java.util.regex.Matcher;




record Contact (String username, String email) implements Comparable<Contact> {
    public static final String UNKNOWN_EMAIL = "unknown";

    Contact {
        if (!isValidName(username)) {
            throw new InvalidContactFieldException("username");
        }
        if (!isValidEmailAddress(email)) {
            throw new InvalidContactFieldException("email");
        }

    }

    static private boolean isValidName(String username) {
        return !username.replaceAll("\\s+", "").isBlank();
    }

    static private boolean isValidEmailAddress(String email) {
        if (email.equals(UNKNOWN_EMAIL)){
            return true;
        }
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@gmail.com";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    Contact(String username) { this(username, UNKNOWN_EMAIL); }


    @Override
    public int compareTo(Contact o) {
        int cmp = Integer.compare(this.username.length(), o.username.length());

        if(cmp == 0) {
            cmp = Integer.compare(this.email.length(), o.email.length());
        }
        return cmp;
    }
}