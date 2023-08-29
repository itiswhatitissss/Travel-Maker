package org.zerock.travelmaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.travelmaker.domain.Party;
import org.zerock.travelmaker.domain.PartyDetail;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.domain.Scheduler;
import org.zerock.travelmaker.mapper.MybatisMapper;
import org.zerock.travelmaker.repository.PartyDetailRepository;
import org.zerock.travelmaker.repository.PartyRepository;
import org.zerock.travelmaker.repository.PlanRepository;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;
    private final PartyRepository partyRepository;
    private final PartyDetailRepository partyDetailRepository;
    private final MybatisMapper mybatisMapper;

    //글작성처리
    /*MultipartFile file 추가*//*예외처리*/
    public void write(Plan plan , MultipartFile file, Long pno) throws Exception{
        /*우리의 프로젝트경로를 담아주게 된다 - 저장할 경로를 지정*/
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        /*식별자 . 랜덤으로 이름 만들어줌*/
        UUID uuid = UUID.randomUUID();

        /*랜덤식별자_원래파일이름 = 저장될 파일이름 지정*/
        String fileName = uuid + "_" + file.getOriginalFilename();

        /*빈 껍데기 생성*/
        /*File을 생성할건데, 이름은 "name" 으로할거고, projectPath 라는 경로에 담긴다는 뜻*/
        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);


        /*디비에 파일 넣기*/
        plan.setFilename(fileName);
        /*저장되는 경로*/
        plan.setFilepath("/files/" + fileName); /*저장된파일의이름,저장된파일의경로*/

        /*파일 저장*/
        Long plno =planRepository .save(plan).getPlno();
        Plan byplno =findByPlno(plno); //만들어진 plno값 받기
        Party byPno=findByPno(pno); //RequestParam 으로 받은 pno값 받기
        PartyDetail partyDetail = PartyDetail.builder().plnoByPartyDetail(byplno).pnoByPartyDetail(byPno).build();//partyDetail 따로 생성 why? prepersist로 생성하면 plno값만 던져주기 때문, pno도 필요
        partyDetailRepository.save(partyDetail);

    }

    public void planmodify(String title, String start, String end,Long plno){
        mybatisMapper.planUpdate(title, start, end, plno);
    }
    public Party findByPno(Long pno) {
        Optional<Party> id = partyRepository.findById(pno);
        Party byPno = id.orElseThrow();
        return byPno;
    }

    public Plan findByPlno(Long plno) {
        Optional<Plan> id = planRepository.findById(plno);
        Plan byPlno = id.orElseThrow();
        return byPlno;
    }

    //게시글리스트처리
    public List<Plan> planList(){
        //findAll : 테스트보드라는 클래스가 담긴 List를 반환하는것을 확인할수있다
        return planRepository.findAll();
    }

    //특정 게시글 불러오기
    public Plan planview(Long id){
        return planRepository.findById(id).get(); //어떤게시글을 불러올지 지정을해주어야한다 (Integer값으로)
    }

    //특정게시글삭제
    public void planDelete(Long id){ /*id값 1번을 넣어주면 1번을 삭제한다*/
        planRepository.deleteById(id);
    }

}
