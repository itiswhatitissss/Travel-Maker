package org.zerock.travelmaker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.travelmaker.domain.Users;
import org.zerock.travelmaker.dto.UserDTO;
import org.zerock.travelmaker.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    //    private final SampleMapper sampleMapper;
    @Override
    public Long getUno(String username) {

        Long uno = userRepository.findUno(username);
        log.info("uno============>" + uno);
        return uno;
    }

    @Override
    public void join(UserDTO userDTO) throws IdExistException {
        Long uno = userDTO.getUno();

//        boolean exist = userRepository.existsById(uno);
//        if(exist){
//            throw new IdExistException();
//        }

        Users users = modelMapper.map(userDTO, Users.class);
        users.changePassword(passwordEncoder.encode(userDTO.getPassword()));

        log.info("=======================");
        log.info(users);

        userRepository.save(users);
    }
}
