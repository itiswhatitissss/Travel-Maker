package org.zerock.travelmaker.alphaTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.travelmaker.domain.*;
import org.zerock.travelmaker.mapper.MybatisMapper;
import org.zerock.travelmaker.repository.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class test {
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
            LocalDate startDate1 = new Date(dateFormat.parse("2023-08-20").getTime()).toLocalDate();
            LocalDate  endDate1 = new Date(dateFormat.parse("2023-08-20").getTime()).toLocalDate();

            LocalDate  startDate2 = new Date(dateFormat.parse("2023-08-24").getTime()).toLocalDate();
            LocalDate  endDate2 = new Date(dateFormat.parse("2023-08-24").getTime()).toLocalDate();

            LocalDate  startDate3 = new Date(dateFormat.parse("2023-08-29").getTime()).toLocalDate();
            LocalDate  endDate3 = new Date(dateFormat.parse("2023-08-30").getTime()).toLocalDate();

            LocalDate  startDate4 = new Date(dateFormat.parse("2023-09-02").getTime()).toLocalDate();
            LocalDate  endDate4 = new Date(dateFormat.parse("2023-09-02").getTime()).toLocalDate();

            LocalDate  startDate5 = new Date(dateFormat.parse("2023-09-14").getTime()).toLocalDate();
            LocalDate  endDate5 = new Date(dateFormat.parse("2023-09-14").getTime()).toLocalDate();

            Plan plan1 = Plan.builder()
                    .title("VS 한양대")
                    .start(startDate1)
                    .end(endDate1)
                    .filepath("https://scontent-ssn1-1.xx.fbcdn.net/v/t39.30808-6/368763328_789504856300817_8240949612825803553_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=5614bc&_nc_ohc=nZaM-KbF2lMAX9eRn2f&_nc_ht=scontent-ssn1-1.xx&oh=00_AfDZi4BFc6Hl0JGB28GKnFjDq3MzR_yGMK7k5Ez5W4P6Ug&oe=650D84A3")
                    .build();
            planRepository.save(plan1);

            Plan plan2 = Plan.builder()
                    .title("VS 홍익대")
                    .start(startDate2)
                    .end(endDate2)
                    .filepath("https://scontent-ssn1-1.xx.fbcdn.net/v/t39.30808-6/368349085_791902316061071_4344553563442658681_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=5614bc&_nc_ohc=FuKJNgUnsv0AX-xjOK7&_nc_oc=AQkQKp5fV4VejjilpiSWUAQog1b8x5bkvX7kZ1S33fGxKhsn79OHg14VEJuflwmKN9w&_nc_ht=scontent-ssn1-1.xx&oh=00_AfBYBoDptO8f7GRwRLHbNCpGkX4cIVstu7F-qyhIQB70EQ&oe=650C96CB")
                    .build();
            planRepository.save(plan2);

            Plan plan3 = Plan.builder()
                    .title("주제: 총균쇠")
                    .start(startDate3)
                    .end(endDate3)
                    .filepath("https://mblogthumb-phinf.pstatic.net/MjAyMDAxMTNfMTgx/MDAxNTc4ODgyMTUyNDM0.-e4N7j1acrwnIcYy3K5psSxVpgIqFz011hXhNSvWU9Ig.d6ykhZtXhz28aJ6r2tXvz2oPXmTcfU_oC7v-M6kGAi0g.JPEG.mkparang/%EC%B4%9D%EA%B7%A0%EC%87%A0.jpg?type=w800")
                    .build();
            planRepository.save(plan3);

            Plan plan4 = Plan.builder()
                    .title("주제: 정의란 무엇인가")
                    .start(startDate4)
                    .end(endDate4)
                    .filepath("https://mblogthumb-phinf.pstatic.net/MjAyMDA4MzBfMTM3/MDAxNTk4NzY0MDgzNjU5.GR9bKSUJbPpJ3KQX__9I2kKmoLxiY42ked_6bc2EEDUg.Rd5kN6b8BzQJj8cUCIxbEiwV4RzhpvhafvR4wVxfKZQg.JPEG.kfx9612/SE-d50e46f3-deba-4925-b719-b6bdf9636fa1.jpg?type=w800")
                    .build();
            planRepository.save(plan4);

            Plan plan5 = Plan.builder()
                    .title("주제: 이기적 유전자")
                    .start(startDate5)
                    .end(endDate5)
                    .filepath("https://image.aladin.co.kr/product/17048/25/cover500/8932473900_1.jpg")
                    .build();
            planRepository.save(plan5);

        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
        }

        Users user1 = Users.builder().id("user1").name("사용자").password(passwordEncoder.encode("1234")).email("test@naver.com").build();
        userRepository.save(user1); //user1 회원가입

        Party party1 = Party.builder().partyName("고려대학교 축구동아리").QR("QRcode").build();
        partyRepository.save(party1); // '고려대학교 축구동아리'고려대학교 축구동아리 파티 생성 

        UserParty userParty = UserParty.builder().unoByUserParty(user1).pnoByUserParty(party1).build();
        userPartyRepository.save(userParty); // user1이 '고려대학교 축구동아리' 파티 참가

        PartyDetail partyDetail1 = PartyDetail.builder().pnoByPartyDetail(party1).plnoByPartyDetail(planRepository.findById(1L).get()).build();
        partyDetailRepository.save(partyDetail1); // '고려대학교 축구동아리' 파티에 'VS 한양대' 플랜 생성

        Users user2 = Users.builder().id("user2").name("김영희").password(passwordEncoder.encode("1234")).email("test2@naver.com").build();
        userRepository.save(user2); //user2 회원가입

        UserParty userParty1 = UserParty.builder().unoByUserParty(user2).pnoByUserParty(partyRepository.findById(1L).get()).build();
        userPartyRepository.save(userParty1); // user2 '고려대학교 축구동아리' 파티 합류

        Users user3 = Users.builder().id("user3").name("홍길동").password(passwordEncoder.encode("1234")).email("test3@naver.com").build();
        userRepository.save(user3); //user3 회원가입

        Users user4 = Users.builder().id("user4").name("이행복").password(passwordEncoder.encode("1234")).email("test4@naver.com").build();
        userRepository.save(user4); //user4 회원가입

        Users user5 = Users.builder().id("user5").name("박믿음").password(passwordEncoder.encode("1234")).email("test5@naver.com").build();
        userRepository.save(user5); //user5 회원가입

        Party party2 = Party.builder().partyName("독서토론동아리").QR("QRcode").build();
        partyRepository.save(party2); //'독서토론동아리' 파티 생성

        UserParty userParty2 = UserParty.builder().unoByUserParty(user1).pnoByUserParty(party2).build();
        userPartyRepository.save(userParty2); //user1 + '독서토론동아리' 파티 참가

        UserParty userParty3 = UserParty.builder().unoByUserParty(user3).pnoByUserParty(party2).build();
        userPartyRepository.save(userParty3); //user3 + '독서토론동아리' 파티 참가

        UserParty userParty4 = UserParty.builder().unoByUserParty(user4).pnoByUserParty(party2).build();
        userPartyRepository.save(userParty4); //user4 + '독서토론동아리' 파티 참가

        Friend friend= Friend.builder().unoByFriend(user1).fnoByFriend(user2).build();
        friendRepository.save(friend); //user1 친구목록에 user2 등록

        Friend friend1= Friend.builder().unoByFriend(user2).fnoByFriend(user1).build();
        friendRepository.save(friend1); //user2 친구목록에 user1 등록

        Friend friend2= Friend.builder().unoByFriend(user1).fnoByFriend(user3).build();
        friendRepository.save(friend2); //user1 친구목록에 user3 등록

        Friend friend3= Friend.builder().unoByFriend(user3).fnoByFriend(user1).build();
        friendRepository.save(friend3); //user3 친구목록에 user1 등록

        PartyDetail partyDetail2 = PartyDetail.builder().pnoByPartyDetail(party1).plnoByPartyDetail(planRepository.findById(2L).get()).build();
        partyDetailRepository.save(partyDetail2); //'고려대학교 축구동아리' 파티에 'VS 홍익대' 플랜 생성

        Attend attend = Attend.builder().Attender(1L).unoByAttend(user1).plnoByAttend(planRepository.findById(1L).get()).build();
        attendRepository.save(attend);//user1이 'VS한양대'에 계획에 참가
        Attend attend1 = Attend.builder().Attender(0L).unoByAttend(user2).plnoByAttend(planRepository.findById(1L).get()).build();
        attendRepository.save(attend1);//user2이 'VS한양대'에 계획에 불참
        Attend attend2 = Attend.builder().Attender(1L).unoByAttend(user3).plnoByAttend(planRepository.findById(3L).get()).build();
        attendRepository.save(attend2);//user3이 '주제: 총균쇠'에 계획에 참가
        Attend attend3 = Attend.builder().Attender(0L).unoByAttend(user4).plnoByAttend(planRepository.findById(3L).get()).build();
        attendRepository.save(attend3);//user4이 '주제: 총균쇠'에 계획에 불참


        PartyDetail partyDetail3 = PartyDetail.builder().pnoByPartyDetail(party2).plnoByPartyDetail(planRepository.findById(3L).get()).build();
        partyDetailRepository.save(partyDetail3); //'독서토론모임'에 '주제: 총균쇠' 플랜 생성

        PartyDetail partyDetail4 = PartyDetail.builder().pnoByPartyDetail(party2).plnoByPartyDetail(planRepository.findById(4L).get()).build();
        partyDetailRepository.save(partyDetail4); //'독서토론모임'에 '주제: 정의란 무엇인가' 플랜 생성

        PartyDetail partyDetail5 = PartyDetail.builder().pnoByPartyDetail(party2).plnoByPartyDetail(planRepository.findById(5L).get()).build();
        partyDetailRepository.save(partyDetail5); //'독서토론모임'에 '주제: 이기적유전자' 플랜 생성


        Gallery gallery1 = Gallery.builder().filepath("https://scontent-ssn1-1.xx.fbcdn.net/v/t39.30808-6/307022876_583004696950835_4275590593869122080_n.jpg?stp=dst-jpg_p640x640&_nc_cat=104&ccb=1-7&_nc_sid=52f669&_nc_ohc=yq04hAILAQ4AX9NZFcP&_nc_ht=scontent-ssn1-1.xx&oh=00_AfBpjYhCNqpXHhu-FRG1k7RITRtu4Yo-Try13xagd9_MHA&oe=650DC2C6").plnoByGallery(planRepository.findById(1L).get()).build();
        galleryRepository.save(gallery1); //'고려대학교 축구동아리'의 플랜 'VS한양대'에 단체사진 게시

        Gallery gallery2 = Gallery.builder().filepath("https://scontent-ssn1-1.xx.fbcdn.net/v/t39.30808-6/309467234_583004700284168_4470626865001347527_n.jpg?_nc_cat=108&ccb=1-7&_nc_sid=a2f6c7&_nc_ohc=YJVOpURCNk4AX9zzFw4&_nc_ht=scontent-ssn1-1.xx&oh=00_AfAOXVaBqY7eaoIVegCwr7rjyRYCe6ZwQ7b16C771e4tAg&oe=650BEB96").plnoByGallery(planRepository.findById(1L).get()).build();
        galleryRepository.save(gallery2); //'고려대학교 축구동아리'의 플랜 'VS한양대'에 단체사진 게시

        Gallery gallery4 = Gallery.builder().filepath("https://external-ssn1-1.xx.fbcdn.net/emg1/v/t13/3617018928027790884?url=https%3A%2F%2Fblogthumb.pstatic.net%2FMjAyMzA4MjRfMjYy%2FMDAxNjkyODY4NTMxODM0.iyrxR9cBI9YF9XJhhvepbcaXBgKChWbq9-HGIEqGivog.ZR_omIdGyV1AkXw4L-saE5y2sdICtvGFDf1HTqkD1bkg.JPEG.sportsku%2FIMG_6966.JPG%3Ftype%3Dw2&fb_obo=1&utld=pstatic.net&stp=c0.5000x0.5000f_dst-jpg_flffffff_p500x261_q75&ccb=13-1&oh=06_AbGzoMo5D9Ae59aGEbOJhOdLwYOU4Hz3yCXZ615_d6aJTw&oe=65099553&_nc_sid=dbad39").plnoByGallery(planRepository.findById(1L).get()).build();
        galleryRepository.save(gallery4); //'고려대학교 축구동아리'의 플랜 'VS홍익대'에 단체사진 게시

        Gallery gallery3 = Gallery.builder().filepath("https://external-ssn1-1.xx.fbcdn.net/emg1/v/t13/2433360969693680343?url=https%3A%2F%2Fblogthumb.pstatic.net%2FMjAyMzA4MjBfODYg%2FMDAxNjkyNTAyNDU5NTQ5.Gr93s6OfRe5kT_EmjSiqbUtK0ikapERh7FFLCiZhAMcg.bQewFAyrY3hiYwLqcfYYMYytZ0HgzEoe8nQXlgp-z4Qg.JPEG.sportsku%2F%25C7%25D1%25BE%25E7%25B4%25EB%25C0%25FC_%25BB%25E7%25C1%25F8.jpg%3Ftype%3Dw2&fb_obo=1&utld=pstatic.net&stp=c0.5000x0.5000f_dst-jpg_flffffff_p500x261_q75&ccb=13-1&oh=06_AbFbYDMspe57HonL7ptWm2oDEMUqP6HV2bVpFACpgiTiqA&oe=65097A36&_nc_sid=dbad39").plnoByGallery(planRepository.findById(2L).get()).build();
        galleryRepository.save(gallery3); //'고려대학교 축구동아리'의 플랜 'VS홍익대'에 단체사진 게시

    }

}