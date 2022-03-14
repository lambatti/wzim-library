package pl.sggw.wzimlibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sggw.wzimlibrary.adapter.SqlUserRepository;
import pl.sggw.wzimlibrary.model.Role;
import pl.sggw.wzimlibrary.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final SqlUserRepository userRepository;

    @Async
    public CompletableFuture<Optional<User>> findByEmail(String email) {
        return CompletableFuture.completedFuture(userRepository.findByEmail(email));
    }

    @Async
    public CompletableFuture<List<User>> findAll() {
        return CompletableFuture.completedFuture(userRepository.findAll());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> tempUser = getUserFromCompletableFuture(email);

        if (tempUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        } else {
            User user = tempUser.get();

            List<GrantedAuthority> authorities = generateAuthorities(user);

            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(),
                    authorities);
        }
    }

    private Optional<User> getUserFromCompletableFuture(String email) {
        Optional<User> user = Optional.empty();
        try {
            user = findByEmail(email).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }


    private List<GrantedAuthority> generateAuthorities(User user) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        Role userRole = user.getRole();

        switch (userRole) {
            case ADMIN: {
                authorities.add(new SimpleGrantedAuthority(Role.ADMIN.toString()));
            }
            case WORKER: {
                authorities.add(new SimpleGrantedAuthority(Role.WORKER.toString()));
            }
            case USER: {
                authorities.add(new SimpleGrantedAuthority(Role.USER.toString()));
                break;
            }
            default: {
                authorities.add(new SimpleGrantedAuthority(Role.USER.toString()));
                break;
            }
        }

        return authorities;
    }
}
