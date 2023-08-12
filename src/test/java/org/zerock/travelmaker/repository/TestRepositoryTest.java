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
    private PartyDetailRepository partyDetailRepository;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private GalleryRepository galleryRepository;
    @Autowired
    private SchedulerRepositroy schedulerRepositroy;
    @Autowired
    private ScheudlerDetailRepository scheudlerDetailRepository;

    @Test
    public void test1() {

        User user = User.builder().id("pkmm").name("이성진").password("1234").email("naver").address("화서동").phone("01035").build();
        userRepository.save(user); //이성진 회원가입

        Friend friend = Friend.builder().fnoByFriend(user).unoByFriend(user).build();
        friendRepository.save(friend); //이성진 친구목록 생성

        Party party = Party.builder().partyName("해병대전우회").QR("QRcode").build();
        partyRepository.save(party); // "해병대전우회" 파티 생성 (파티를 생성하면서 user값을 UserParty에 바로 넣을 수 있게 해야 될 듯?)

        UserParty userParty = UserParty.builder().unoByUserParty(user).pnoByUserParty(party).build();
        userPartyRepository.save(userParty); // 이성진 + "해병대전우회" 파티 참가

        Plan plan = Plan.builder().title("강원도삼척여행").build();
        planRepository.save(plan); //강원도삼척여행 생성

        PartyDetail partyDetail = PartyDetail.builder().pnoByPartyDetail(party).plnoByPartyDetail(plan).build();
        partyDetailRepository.save(partyDetail); //"해병대전우회" 파티에 강원도삼척여행 플랜 생성

        Gallery gallery = Gallery.builder().image("전우회단체사진").plnoByGallery(plan).build();
        galleryRepository.save(gallery); //"해병대전우회" + "강원도삼척여행" 전우회단체사진 게시

        Scheduler scheduler = Scheduler.builder().plnoByScheduler(plan).build();
        schedulerRepositroy.save(scheduler);

        SchedulerDetail schedulerDetail = SchedulerDetail.builder().snoBySchedulerDetail(scheduler).content("재밌게 놀자 !").build();
        scheudlerDetailRepository.save(schedulerDetail);
    }

    @Test
    public void test2(){
        User user = User.builder().id("leehal").name("이하림").password("1234").email("naver").address("화서동").phone("01044").build();
        userRepository.save(user); //1. 이하림 회원가입

        UserParty userParty = UserParty.builder().unoByUserParty(user).pnoByUserParty(partyRepository.findById(1L).get()).build();
        userPartyRepository.save(userParty); //2. 이하림 "양양가실분" 파티 합류
    }

    @Test
    public void test3(){
        User user = User.builder().id("gkdms").name("노예은").password("1234").email("naver").address("탑동").phone("010999").build();
        userRepository.save(user); //1. 노에은 회원가입

        Party party = Party.builder().partyName("한신가실분").QR("QRcode").build();
        partyRepository.save(party); //2. "한신가실분" 파티 생성

        UserParty userParty = UserParty.builder().unoByUserParty(user).pnoByUserParty(party).build();
        userPartyRepository.save(userParty); //3. 노예은 + "한신가실분" 파티 참가

        Friend friend = Friend.builder().fnoByFriend(user).unoByFriend(user).build();
        friendRepository.save(friend);
    }
    @Test
    public void test4(){
        UserParty userParty = UserParty.builder().unoByUserParty(userRepository.findById(1L).get()).pnoByUserParty(partyRepository.findById(2L).get()).build();
        userPartyRepository.save(userParty); //이성진(uno=1)이 "한신가실분" 파티(pno=2) 참가
    }
}