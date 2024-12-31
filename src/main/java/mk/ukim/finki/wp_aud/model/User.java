package mk.ukim.finki.wp_aud.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User {

    private String username;
    private String password;
    private String name;
    private String surname;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
