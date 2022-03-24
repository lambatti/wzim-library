package pl.sggw.wzimlibrary.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sggw.wzimlibrary.model.User;
import pl.sggw.wzimlibrary.model.constant.Role;
import pl.sggw.wzimlibrary.model.dto.UserRegistrationDto;
import pl.sggw.wzimlibrary.service.cache.UserCacheService;
import pl.sggw.wzimlibrary.util.JwtUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserCacheService userCacheService;

    @Async
    public CompletableFuture<Optional<User>> findByEmail(String email) {
        return CompletableFuture.completedFuture(userCacheService.findByEmail(email));
    }

    @Async
    public CompletableFuture<List<User>> findAll() {
        return CompletableFuture.completedFuture(userCacheService.findAll());
    }

    @Async
    public CompletableFuture<User> save(User user) {
        return CompletableFuture.completedFuture(userCacheService.save(user));
    }

    private String extractEmailFromToken(String token) {
        token = jwtUtil.removeBearer(token);
        return jwtUtil.extractEmail(token);
    }

    public Optional<User> registerUser(UserRegistrationDto userRegistrationDto) throws ExecutionException, InterruptedException {

        if (findByEmail(userRegistrationDto.getEmail()).get().isPresent()) {
            return Optional.empty();
        }

        userRegistrationDto.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));

        return Optional.of(save(modelMapper.map(userRegistrationDto, User.class)).get());
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
