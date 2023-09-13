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
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findById(username);

        if(users == null)
            throw new UsernameNotFoundException(username);

        UserDetails userDetails = User.builder()
                .username(users.getId())
                .password(users.getPassword())
                .authorities("ROLE_USER")
                .build();

        return userDetails;
    }
}
