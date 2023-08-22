package org.zerock.travelmaker.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.travelmaker.domain.Friend;
import org.zerock.travelmaker.domain.Users;
import org.zerock.travelmaker.dto.FriendDTO;
import org.zerock.travelmaker.repository.FriendRepository;

@SpringBootTest
@Slf4j
public class FriendServiceImplTest {
    @Autowired
    private FriendService friendService;


    @Test
    public void testRegister(){
        Long uno = 2L;
        Long fno = 1L;
        FriendDTO friendDTO = FriendDTO.builder()
                .fno(fno)
                .uno(uno)
                .build();
        log.info("friendDTO.getFno : {}",friendDTO.getFno());
        friendService.insertFriend(friendDTO);


    }

}
