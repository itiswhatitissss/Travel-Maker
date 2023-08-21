package org.zerock.travelmaker.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.travelmaker.domain.Friend;
import org.zerock.travelmaker.dto.FriendDTO;

@SpringBootTest
@Slf4j
public class FriendServiceImplTest {
    @Autowired
    private FriendService friendService;

//    @Test
//    public void testRegister(){
//        Friend friend = Friend.builder().unoByFriend().fnoByFriend().build();
//        friendService.insertFriend();
//    }

}
