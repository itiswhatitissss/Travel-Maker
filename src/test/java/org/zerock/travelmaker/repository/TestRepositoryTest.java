package org.zerock.travelmaker.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.travelmaker.domain.*;

@SpringBootTest
@Log4j2
class TestRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PartyRepository partyRepository;
    @Autowired
    private UserPartyRepository userPartyRepository;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private GalleryRepository galleryRepository;
    @Autowired
    private PartyDetailRepository partyDetailRepository;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private SchedulerRepository schedulerRepository;
    @Autowired
    private SchedulerDetailRepository schedulerDetailRepository;
    @Autowired
    private VoteOptionRepository voteOptionRepository;
    @Autowired
    private VoteRepository voteRepository;

    @Test
    public void test() {
        User user = User.builder().id("pkmm").name("이성진").password("1234").email("naver").address("화서동").phone("0103505").build();
        userRepository.save(user);

        Friend friend = Friend.builder().unoByFriend(user).fnoByFriend(user).build();
        friendRepository.save(friend);

        VoteOption voteOption = VoteOption.builder().unoByVoteOption(user).option("hello").build();
        voteOptionRepository.save(voteOption);

        UserParty userParty = UserParty.builder().unoByUserParty(user).build();
        userPartyRepository.save(userParty);
    }
}