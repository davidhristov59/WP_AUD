package mk.ukim.finki.wp_aud.service.ServiceImpl;

import mk.ukim.finki.wp_aud.model.User;
import mk.ukim.finki.wp_aud.model.exceptions.InvalidArgumentExceptions;
import mk.ukim.finki.wp_aud.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp_aud.model.exceptions.PasswordDoNotMatchException;
import mk.ukim.finki.wp_aud.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp_aud.repository.impl.InMemoryUserRepository;
import mk.ukim.finki.wp_aud.repository.jpa.UserRepository;
import mk.ukim.finki.wp_aud.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {

        if(username == null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentExceptions();
        }

        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatedPassword, String name, String surname) {

        // Check if the username, password, name and surname are not null or empty
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || repeatedPassword == null || repeatedPassword.isEmpty() || name == null || name.isEmpty() || surname == null || surname.isEmpty()) {
            throw new InvalidArgumentExceptions();
        }

        if(!password.equals(repeatedPassword)){
            throw new PasswordDoNotMatchException();
        }

        //if the username exists
        if(userRepository.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExistsException(username);
        }

        User user = new User(username, password, name, surname);

        return userRepository.save(user);
    }
}
