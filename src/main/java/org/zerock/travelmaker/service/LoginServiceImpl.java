package org.zerock.travelmaker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.travelmaker.domain.Users;
import org.zerock.travelmaker.dto.UserDTO;
import org.zerock.travelmaker.mapper.SampleMapper;
import org.zerock.travelmaker.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class LoginServiceImpl implements LoginService{

    private final UserRepository userRepository;
//    private final ModelMapper modelMapper;
//    private final SampleMapper sampleMapper;
    @Override
    public Long getUno(String id, String password) {

        Long uno = userRepository.findUno(id, password);
        log.info("uno============>" + uno);
        return uno;
    }
}
