package org.zerock.travelmaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.travelmaker.domain.Party;
import org.zerock.travelmaker.domain.PartyDetail;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.mapper.MybatisMapper;
import org.zerock.travelmaker.repository.PartyDetailRepository;
import org.zerock.travelmaker.repository.PartyRepository;
import org.zerock.travelmaker.repository.PlanRepository;

import java.io.File;
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

        /*확인하고 폴더를 생성 (만약 폴더가 없다면)*/
        File filesFolder = new File(projectPath);
        if (!filesFolder.exists()) {
            filesFolder.mkdirs();
        }

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

}
