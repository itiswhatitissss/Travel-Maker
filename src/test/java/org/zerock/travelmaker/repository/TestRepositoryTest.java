package org.zerock.travelmaker.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.travelmaker.domain.*;
import org.zerock.travelmaker.domain.VoteOption;
import org.zerock.travelmaker.mapper.MybatisMapper;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private VoteOptionRepository voteOptionRepository;
    @Autowired
    private MybatisMapper mybatisMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AttendRepository attendRepository;

    @Test
    public void test() { //이거 하나로 끝내자
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            LocalDate startDate = new Date(dateFormat.parse("2022-08-20").getTime()).toLocalDate();
            LocalDate  endDate = new Date(dateFormat.parse("2022-08-21").getTime()).toLocalDate();
            LocalDate  startDate1 = new Date(dateFormat.parse("2022-07-15").getTime()).toLocalDate();
            LocalDate  endDate1 = new Date(dateFormat.parse("2022-07-21").getTime()).toLocalDate();
            LocalDate  startDate2 = new Date(dateFormat.parse("2022-09-29").getTime()).toLocalDate();
            LocalDate  endDate2 = new Date(dateFormat.parse("2022-09-30").getTime()).toLocalDate();

            Plan plan = Plan.builder()
                    .title("해병대정기모임 1회")
                    .start(startDate)
                    .end(endDate)
                    .filepath("https://www.ilyosisa.co.kr/data/photos/201403/60777_956_2743.jpg")
                    .build();
            Plan plan1 = Plan.builder()
                    .title("308호 인계동 정모")
                    .start(startDate1)
                    .end(endDate1)
                    .filepath("https://pds.skyedaily.com/news_data/1439896367RfRDoDi9x6cXvg46stCDK.jpg")
                    .build();
            Plan plan2 = Plan.builder()
                    .title("308호 여름휴가")
                    .start(startDate2)
                    .end(endDate2)
                    .filepath("https://www.kns.tv/news/photo/201812/506982_387972_2754.jpg")
                    .build();

            planRepository.save(plan);
            planRepository.save(plan1);
            planRepository.save(plan2);

        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
        }

        Users user = Users.builder().id("pkmm").name("이성진").password(passwordEncoder.encode("1234")).email("naver").build();
        userRepository.save(user); //이성진 회원가입

        Party party = Party.builder().partyName("해병대전우회").QR("QRcode").build();
        partyRepository.save(party); // "해병대전우회" 파티 생성 (파티를 생성하면서 user값을 UserParty에 바로 넣을 수 있게 해야 될 듯?)

        UserParty userParty = UserParty.builder().unoByUserParty(user).pnoByUserParty(party).build();
        userPartyRepository.save(userParty); // 이성진 + "해병대전우회" 파티 참가

        PartyDetail partyDetail = PartyDetail.builder().pnoByPartyDetail(party).plnoByPartyDetail(planRepository.findById(1L).get()).build();
        partyDetailRepository.save(partyDetail); //"해병대전우회" 파티에 "해병대전우회 첫여행" 플랜 생성

        Gallery gallery = Gallery.builder().title("전우회단체사진").plnoByGallery(planRepository.findById(1L).get()).build();
        galleryRepository.save(gallery); //"해병대전우회" + "강원도삼척여행" 전우회단체사진 게시

        Scheduler scheduler = Scheduler.builder().plnoByScheduler(planRepository.findById(1L).get()).build();
        schedulerRepositroy.save(scheduler); //해병대전우회 첫여행에 관한 스케줄러 생성

        SchedulerDetail schedulerDetail = SchedulerDetail.builder().snoBySchedulerDetail(scheduler).content("재밌게 놀자 !").build();
        scheudlerDetailRepository.save(schedulerDetail); //해병대전우회 첫여행 스케줄러 디테일 생성

        Vote vote = Vote.builder().plnoByVote(planRepository.findById(1L).get()).vTitle("술 몇 병 살래").vCheck(1).vComplete(1).vCount(3).build();
        voteRepository.save(vote); //해병대전우회 첫여행 투표시스템 사용

        VoteOption voteOption = VoteOption.builder().vnoByVoteOption(vote).unoByVoteOption(user).voOption("띠용").build();
        voteOptionRepository.save(voteOption);

        Users user1 = Users.builder().id("harim").name("이하림").password(passwordEncoder.encode("1234")).email("naver").build();
        userRepository.save(user1); //이하림 회원가입

        UserParty userParty1 = UserParty.builder().unoByUserParty(user1).pnoByUserParty(partyRepository.findById(1L).get()).build();
        userPartyRepository.save(userParty1); // 이하림 "해병대전우회" 파티 합류

        Users user2 = Users.builder().id("yeeun").name("노예은").password(passwordEncoder.encode("1234")).email("naver").build();
        userRepository.save(user2); //노에은 회원가입

        Party party2 = Party.builder().partyName("308호 모임").QR("QRcode").build();
        partyRepository.save(party2); //"308호 모임" 파티 생성

        UserParty userParty2 = UserParty.builder().unoByUserParty(user2).pnoByUserParty(party2).build();
        userPartyRepository.save(userParty2); //노예은 + "308호 모임" 파티 참가

        UserParty userParty3 = UserParty.builder().unoByUserParty(user).pnoByUserParty(partyRepository.findById(2L).get()).build();
        userPartyRepository.save(userParty3); //이성진이 "308호 모임" 파티(pno=2) 참가

        PartyDetail partyDetail1 = PartyDetail.builder().pnoByPartyDetail(party2).plnoByPartyDetail(planRepository.findById(2L).get()).build();
        partyDetailRepository.save(partyDetail1); //"308호 모임" 파티에 "308호 인계동 정모" 플랜 생성

        Gallery gallery1 = Gallery.builder().title("인계동 단체사진").plnoByGallery(planRepository.findById(2L).get()).build();
        galleryRepository.save(gallery1); //"308호 모임" + "인계동 정모" 단체사진 게시

        Scheduler scheduler1 = Scheduler.builder().plnoByScheduler(planRepository.findById(2L).get()).build();
        schedulerRepositroy.save(scheduler1); //"308호 모임" 첫 정모에 관한 스케줄러 생성

        SchedulerDetail schedulerDetail1 = SchedulerDetail.builder().snoBySchedulerDetail(scheduler1).content("먹고죽어").build();
        scheudlerDetailRepository.save(schedulerDetail1); //"308호 모임" 첫 정모에 관한 스케줄러 디테일 생성


        PartyDetail partyDetail2 = PartyDetail.builder().pnoByPartyDetail(party2).plnoByPartyDetail(planRepository.findById(3L).get()).build();
        partyDetailRepository.save(partyDetail2); //"308호 모임" 파티에 "308호 여름휴가" 플랜 생성

        Gallery gallery2 = Gallery.builder().title("인계동 단체사진").plnoByGallery(planRepository.findById(3L).get()).build();
        galleryRepository.save(gallery2); //"308호 모임" + "여름휴가" 단체사진 게시

        Scheduler scheduler2 = Scheduler.builder().plnoByScheduler(planRepository.findById(3L).get()).build();
        schedulerRepositroy.save(scheduler2); //"308호 모임" "여름휴가"에 관한 스케줄러 생성

        SchedulerDetail schedulerDetail2 = SchedulerDetail.builder().snoBySchedulerDetail(scheduler2).content("행복한 여행 보내자").build();
        scheudlerDetailRepository.save(schedulerDetail2); //"308호 모임" 여름휴가에 관한 스케줄러 디테일 생성
    }
    @Test
    public void testAttend(){
        Optional<Users> byId = userRepository.findById(2L);
        Users uno = byId.orElseThrow();
        Optional<Plan> byId2 = planRepository.findById(1L);
        Plan plno = byId2.orElseThrow();

        Attend attend = Attend.builder()
                .Attender(0L)
                .unoByAttend(uno)
                .plnoByAttend(plno)
                .build();

        attendRepository.save(attend);
    }
    @Test
    public void testAttender(){
        Optional<Users> byId = userRepository.findById(3L);
        Users uno = byId.orElseThrow();
        Optional<Plan> byId2 = planRepository.findById(1L);
        Plan plno = byId2.orElseThrow();

        Long result = attendRepository.selectAttend(uno,plno);
        log.info("참석여부는 : "+result);
    }
    @Test
    public void updateAttender(){
        Optional<Users> byId = userRepository.findById(1L);
        Users uno = byId.orElseThrow();
        Optional<Plan> byId2 = planRepository.findById(1L);
        Plan plno = byId2.orElseThrow();

        Long result = attendRepository.selectAttend(uno,plno);
        attendRepository.updateAttend(uno,plno,result,0L);
    }
    @Test
    public void updatePartyname(){
        partyRepository.updateParty("다시",3L);
    }
}