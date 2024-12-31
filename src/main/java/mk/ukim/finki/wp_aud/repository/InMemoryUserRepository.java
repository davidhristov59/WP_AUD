package mk.ukim.finki.wp_aud.repository;

import mk.ukim.finki.wp_aud.bootstrap.DataHolder;
import mk.ukim.finki.wp_aud.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryUserRepository {

    public Optional<User> findByUsername(String username){
        return DataHolder.users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    public Optional<User> findByUsernameAndPassword(String username, String password){
        return DataHolder.users.stream()
                .filter(u -> u.getUsername().equals(username) &&
                        u.getPassword().equals(password))
                .findFirst();
    }

    public User saveOrUpdate(User user){
        DataHolder.users.removeIf(u -> u.getUsername().equals(user.getUsername()));

        DataHolder.users.add(user);

        return user;
    }

    public void deleteByUsername(String username){
        DataHolder.users.removeIf(u -> u.getUsername().equals(username));
    }

}
