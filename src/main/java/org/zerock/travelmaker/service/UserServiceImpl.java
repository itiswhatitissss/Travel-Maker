package org.zerock.travelmaker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.travelmaker.domain.Users;
import org.zerock.travelmaker.dto.UserDTO;
import org.zerock.travelmaker.mapper.MybatisMapper;
import org.zerock.travelmaker.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final MybatisMapper mybatisMapper;

    @Override
    public Long getUno(String username) {

        Long uno = userRepository.findUno(username);
        log.info("uno============>" + uno);
        return uno;
    }

    @Override
    public void join(UserDTO userDTO) throws IdExistException {
//        Long uno = userDTO.getUno();
//        boolean exist = userRepository.existsById(uno);
//        if(exist){
//            throw new IdExistException();
//        }

        Users users = modelMapper.map(userDTO, Users.class);
        users.changePassword(passwordEncoder.encode(userDTO.getPassword()));

        log.info("================================================");
        log.info(users.getUno());

        userRepository.save(users);
    }

    @Override
    public List<Map<String, Object>> userList(Long uno) {
        List<Map<String, Object>> result = mybatisMapper.selectUserList(uno);
        return result;
    }

    @Override
    public Users findByUsername(String username) {

        Users nameById = userRepository.findById(username);

        return nameById;
    }

    @Override
    @Transactional
    public void modifyInformation(UserDTO userDTO) throws IdExistException {
        String usersID = userDTO.getId();

        boolean exist = userRepository.existsById(Long.valueOf(usersID));

        if(!exist){
            throw new IdExistException();
        }

        Optional<Users> usersbyID = Optional.ofNullable(userRepository.findById(usersID));

        Users users = usersbyID.orElseThrow();
        users.changeName(userDTO.getName());
        users.changePassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(users);
    }


}
