package org.zerock.travelmaker.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.travelmaker.domain.*;
import org.zerock.travelmaker.mapper.MybatisMapper;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
                    .title("룸의정석")
                    .start(startDate)
                    .end(endDate)
                    .filepath("https://img2.daumcdn.net/thumb/R658x0.q70/?fname=https://t1.daumcdn.net/news/202105/25/BoiledMovie/20210525063226684qmjk.png")
                    .build();
            Plan plan1 = Plan.builder()
                    .title("308호 인계동 정모")
                    .start(startDate1)
                    .end(endDate1)
                    .filepath("https://mblogthumb-phinf.pstatic.net/MjAxODAzMTJfMjgw/MDAxNTIwODQwODE3NjAz.GDQLXpQf7IkutRxcKEe7U04j467M-4vgID_V___8L1wg.M8VpUcVlENCMWGVeJ0QDLuji-ka04d8tdb_lW5kaE2Ig.JPEG.bymin0418/image_3258027021520840501707.jpg?type=w800")
                    .build();
            Plan plan2 = Plan.builder()
                    .title("308호 여름휴가")
                    .start(startDate2)
                    .end(endDate2)
                    .filepath("https://cdn.huffingtonpost.kr/news/photo/202007/99196_184601.jpeg")
                    .build();

            planRepository.save(plan);
            planRepository.save(plan1);
            planRepository.save(plan2);

        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
        }

        Users user1 = Users.builder().id("pkmm").name("이성진").password(passwordEncoder.encode("1234")).email("naver").build();
        userRepository.save(user1); //이성진 회원가입

        Party party = Party.builder().partyName("러블리펜션").QR("QRcode").build();
        partyRepository.save(party); // "해병대전우회" 파티 생성 (파티를 생성하면서 user값을 UserParty에 바로 넣을 수 있게 해야 될 듯?)

        UserParty userParty = UserParty.builder().unoByUserParty(user1).pnoByUserParty(party).build();
        userPartyRepository.save(userParty); // 이성진 + "해병대전우회" 파티 참가

        PartyDetail partyDetail = PartyDetail.builder().pnoByPartyDetail(party).plnoByPartyDetail(planRepository.findById(1L).get()).build();
        partyDetailRepository.save(partyDetail); //"해병대전우회" 파티에 "해병대전우회 첫여행" 플랜 생성

        Gallery gallery = Gallery.builder().filepath("https://pds.skyedaily.com/news_data/1439896367RfRDoDi9x6cXvg46stCDK.jpg").plnoByGallery(planRepository.findById(1L).get()).build();
        galleryRepository.save(gallery); //"해병대전우회" + "강원도삼척여행" 전우회단체사진 게시

        Users user2 = Users.builder().id("harim").name("이하림").password(passwordEncoder.encode("1234")).email("naver").build();
        userRepository.save(user2); //이하림 회원가입

        UserParty userParty1 = UserParty.builder().unoByUserParty(user2).pnoByUserParty(partyRepository.findById(1L).get()).build();
        userPartyRepository.save(userParty1); // 이하림 "해병대전우회" 파티 합류

        Users user3 = Users.builder().id("yeeun").name("노예은").password(passwordEncoder.encode("1234")).email("naver").build();
        userRepository.save(user3); //노에은 회원가입

        Users user4 = Users.builder().id("sam").name("박종진").password(passwordEncoder.encode("1234")).email("naver").build();
        userRepository.save(user4); //종진 회원가입

        Users user5 = Users.builder().id("soso").name("김소휘").password(passwordEncoder.encode("1234")).email("naver").build();
        userRepository.save(user5); //소휘 회원가입

        Users user6 = Users.builder().id("jimin").name("김지민").password(passwordEncoder.encode("1234")).email("naver").build();
        userRepository.save(user6); //지민 회원가입

        Users user7 = Users.builder().id("kium").name("김기은").password(passwordEncoder.encode("1234")).email("naver").build();
        userRepository.save(user7); //기은 회원가입
        Users user8 = Users.builder().id("leeseu").name("서연주").password(passwordEncoder.encode("1234")).email("naver").build();
        userRepository.save(user8); //연주 회원가입
        Users user9 = Users.builder().id("michu").name("추명선").password(passwordEncoder.encode("1234")).email("naver").build();
        userRepository.save(user9); //명선 회원가입
        Users user10 = Users.builder().id("yuen").name("강연재").password(passwordEncoder.encode("1234")).email("naver").build();
        userRepository.save(user10); //연재 회원가입

        Party party2 = Party.builder().partyName("308호 모임").QR("QRcode").build();
        partyRepository.save(party2); //"308호 모임" 파티 생성

        UserParty userParty2 = UserParty.builder().unoByUserParty(user1).pnoByUserParty(party2).build();
        userPartyRepository.save(userParty2); //노예은 + "308호 모임" 파티 참가

        UserParty userParty10 = UserParty.builder().unoByUserParty(user2).pnoByUserParty(party2).build();
        userPartyRepository.save(userParty10); //노예은 + "308호 모임" 파티 참가

        UserParty userParty11 = UserParty.builder().unoByUserParty(user3).pnoByUserParty(party2).build();
        userPartyRepository.save(userParty11); //노예은 + "308호 모임" 파티 참가

        Friend friend= Friend.builder().unoByFriend(user1).fnoByFriend(user2).build();
        friendRepository.save(friend); //이성진 친구목록에 이하림 등록

        Friend friend1= Friend.builder().unoByFriend(user1).fnoByFriend(user3).build();
        friendRepository.save(friend1); //이성진 친구목록에 노예은 등록

        Friend friend2= Friend.builder().unoByFriend(user1).fnoByFriend(user3).build();
        friendRepository.save(friend2); //이하림 친구목록에 노예은 등록


        UserParty userParty3 = UserParty.builder().unoByUserParty(user4).pnoByUserParty(partyRepository.findById(2L).get()).build();
        userPartyRepository.save(userParty3); //종진이 "308호 모임" 파티(pno=2) 참가

        UserParty userParty4 = UserParty.builder().unoByUserParty(user5).pnoByUserParty(partyRepository.findById(2L).get()).build();
        userPartyRepository.save(userParty4); //소휘 "308호 모임" 파티(pno=2) 참가

        UserParty userParty5 = UserParty.builder().unoByUserParty(user6).pnoByUserParty(partyRepository.findById(2L).get()).build();
        userPartyRepository.save(userParty5); //지민 "308호 모임" 파티(pno=2) 참가

        UserParty userParty6 = UserParty.builder().unoByUserParty(user7).pnoByUserParty(partyRepository.findById(2L).get()).build();
        userPartyRepository.save(userParty6); //기은 "308호 모임" 파티(pno=2) 참가

        UserParty userParty7 = UserParty.builder().unoByUserParty(user9).pnoByUserParty(partyRepository.findById(1L).get()).build();
        userPartyRepository.save(userParty7); //명선 "308호 모임" 파티(pno=2) 참가

        UserParty userParty8 = UserParty.builder().unoByUserParty(user10).pnoByUserParty(partyRepository.findById(1L).get()).build();
        userPartyRepository.save(userParty8); //연재 "308호 모임" 파티(pno=2)

        UserParty userParty9 = UserParty.builder().unoByUserParty(user4).pnoByUserParty(partyRepository.findById(1L).get()).build();
        userPartyRepository.save(userParty9); //이성진이 "308호 모임" 파티(pno=2) 참가

        PartyDetail partyDetail1 = PartyDetail.builder().pnoByPartyDetail(party2).plnoByPartyDetail(planRepository.findById(2L).get()).build();
        partyDetailRepository.save(partyDetail1); //"308호 모임" 파티에 "308호 인계동 정모" 플랜 생성

        Attend attend = Attend.builder().Attender(1L).unoByAttend(user4).plnoByAttend(planRepository.findById(2L).get()).build();
        attendRepository.save(attend);//종진이가 308호 인계동 정모에 참가
        Attend attend1 = Attend.builder().Attender(1L).unoByAttend(user5).plnoByAttend(planRepository.findById(2L).get()).build();
        attendRepository.save(attend1);//소휘가 308호 인계동 정모에 참가
        Attend attend2 = Attend.builder().Attender(1L).unoByAttend(user9).plnoByAttend(planRepository.findById(2L).get()).build();
        attendRepository.save(attend2);//명선님이 308호 인계동 정모에 참가

        Gallery gallery1 = Gallery.builder().filepath("https://pds.skyedaily.com/news_data/1439896367RfRDoDi9x6cXvg46stCDK.jpg").plnoByGallery(planRepository.findById(2L).get()).build();
        galleryRepository.save(gallery1); //"308호 모임" + "인계동 정모" 단체사진 게시


        PartyDetail partyDetail2 = PartyDetail.builder().pnoByPartyDetail(party2).plnoByPartyDetail(planRepository.findById(3L).get()).build();
        partyDetailRepository.save(partyDetail2); //"308호 모임" 파티에 "308호 여름휴가" 플랜 생성

        Attend attend3 = Attend.builder().Attender(1L).unoByAttend(user10).plnoByAttend(planRepository.findById(3L).get()).build();
        attendRepository.save(attend3);


        Gallery gallery2 = Gallery.builder().filepath("https://www.ilyosisa.co.kr/data/photos/201403/60777_956_2743.jpg").plnoByGallery(planRepository.findById(3L).get()).build();
        galleryRepository.save(gallery2); //"308호 모임" + "여름휴가" 단체사진 게시
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
//    @Test
//    public void updatePartyname(){
//        partyRepository.updateParty("다시",3L);
//    }
}