package org.zerock.travelmaker.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.travelmaker.domain.Users;
import org.zerock.travelmaker.repository.UserRepository;

import java.util.Optional;

@Service
@Log4j2
@Transactional
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public CustomUserDetailService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> result = userRepository.findById(username);
        Users users = result.orElseThrow();

        if(users == null)
            throw new UsernameNotFoundException(username);

        UserDetails userDetails = User.builder()
                .username(users.getId())
                .password(passwordEncoder.encode(users.getPassword()))
                .authorities("ROLE_USER")
                .build();

        return userDetails;
    }
}
