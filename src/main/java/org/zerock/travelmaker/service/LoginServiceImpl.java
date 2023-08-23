package org.zerock.travelmaker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.travelmaker.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class LoginServiceImpl implements LoginService{

    private final UserRepository userRepository;
//    private final ModelMapper modelMapper;
//    private final SampleMapper sampleMapper;
    @Override
    public Long getUno(String username) {

        Long uno = userRepository.findUno(username);
        log.info("uno============>" + uno);
        return uno;
    }
}
