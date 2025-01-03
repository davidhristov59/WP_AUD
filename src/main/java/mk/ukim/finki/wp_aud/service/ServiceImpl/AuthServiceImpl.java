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
}
