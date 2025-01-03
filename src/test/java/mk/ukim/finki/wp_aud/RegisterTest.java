package mk.ukim.finki.wp_aud;

import mk.ukim.finki.wp_aud.model.User;
import mk.ukim.finki.wp_aud.model.enumerations.Role;
import mk.ukim.finki.wp_aud.model.exceptions.InvalidArgumentExceptions;
import mk.ukim.finki.wp_aud.model.exceptions.PasswordDoNotMatchException;
import mk.ukim.finki.wp_aud.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp_aud.repository.jpa.UserRepository;
import mk.ukim.finki.wp_aud.service.ServiceImpl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RegisterTest { //register od UserServiceImpl

    //zavisnostite od koi sto zavisi nasiot UserServiceImpl

    @Mock //simulirani ke bidat implementaciite, nema da gi korisime kako vistinski, nema vistinski da se izvrsat kon bazata na pod, odnosno ke se simuliraat
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl userService;

    /**
     * Use Mock: For dependencies like UserRepository and PasswordEncoder, where you don’t want to execute real methods.
     * Use Spy: For the service (UserServiceImpl) when you want to test its real behavior while retaining control
     *          over specific methods or monitoring interactions.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        User user = new User("username", "password", "name", "surname", Role.ROLE_USER);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        this.userService = Mockito.spy(new UserServiceImpl(this.userRepository, this.passwordEncoder));
    }

    @Test
    public void testSuccessfulRegister() {
        User user = this.userService.register("username", "password", "password", "Name", "Surname", Role.ROLE_USER);
        Mockito.verify(this.userService).register("username", "password", "password", "Name", "Surname", Role.ROLE_USER);

        Assert.assertNotNull("User is null", user);
        Assert.assertEquals("Username does not match", "username", user.getUsername());
    }

    @Test
    public void testNullUsername() {
        String username = null;
        String password = "password";
        Assert.assertThrows("InvalidArgException expected", InvalidArgumentExceptions.class, () -> {
            User user = this.userService.register(username, password, password, "Name", "Surname", Role.ROLE_USER);
        });

        Mockito.verify(this.userService).register(username, password, password, "Name", "Surname", Role.ROLE_USER);
    }

    @Test
    public void testEmptyUsername() {
        String username = "";
        String password = "password";
        Assert.assertThrows("InvalidArgException expected", InvalidArgumentExceptions.class, () -> {
            User user = this.userService.register(username, password, password, "Name", "Surname", Role.ROLE_USER);
        });

        Mockito.verify(this.userService).register(username, password, password, "Name", "Surname", Role.ROLE_USER);
    }

    @Test
    public void testNullPassword() {
        String username = "username";
        String password = null;
        Assert.assertThrows("InvalidArgException expected", InvalidArgumentExceptions.class, () -> {
            User user = this.userService.register(username, password, password, "Name", "Surname", Role.ROLE_USER);
        });

        Mockito.verify(this.userService).register(username, password, password, "Name", "Surname", Role.ROLE_USER);
    }

    @Test
    public void testEmptyPassword() {
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidArgException expected", InvalidArgumentExceptions.class, () -> {
            User user = this.userService.register(username, password, password, "Name", "Surname", Role.ROLE_USER);
        });

        Mockito.verify(this.userService).register(username, password, password, "Name", "Surname", Role.ROLE_USER);
    }

    @Test
    public void testPasswordsDoNotMatch() {
        String password = "password";
        String passwordConfirm = "otherPassword";

        Assert.assertThrows("PasswordsDoNotMatchException expected", PasswordDoNotMatchException.class, () -> {
            User user = this.userService.register("username", password, passwordConfirm, "Name", "Surname", Role.ROLE_USER);
        });

        Mockito.verify(this.userService).register("username", password, passwordConfirm, "Name", "Surname", Role.ROLE_USER);
    }

    @Test
    public void testUsernameAlreadyExists() {
        User existingUser = new User("username", "password", "name", "surname", Role.ROLE_USER);
        Mockito.when(this.userRepository.findByUsername("username")).thenReturn(Optional.of(existingUser));

        Assert.assertThrows("UsernameAlreadyExistsException expected", UsernameAlreadyExistsException.class, () -> {
            User user = this.userService.register("username", "password", "password", "Name", "Surname", Role.ROLE_USER);
        });

        Mockito.verify(this.userService).register("username", "password", "password", "Name", "Surname", Role.ROLE_USER);
    }



}
