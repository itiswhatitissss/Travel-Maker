package org.zerock.travelmaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.travelmaker.domain.Gallery;
import org.zerock.travelmaker.domain.Party;
import org.zerock.travelmaker.domain.PartyDetail;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.dto.GalleryDTO;
import org.zerock.travelmaker.mapper.MybatisMapper;
import org.zerock.travelmaker.repository.GalleryRepository;
import org.zerock.travelmaker.repository.PlanRepository;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryRepository galleryRepository;
    private final PlanRepository planRepository;
    private final MybatisMapper mybatisMapper;

    public void galleryPlus(Gallery gallery , MultipartFile file, Long plno) throws Exception{
        /*우리의 프로젝트경로를 담아주게 된다 - 저장할 경로를 지정*/
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\gallery";

        /*확인하고 폴더를 생성 (만약 폴더가 없다면)*/
        File galleryFolder = new File(projectPath);
        if (!galleryFolder.exists()) {
            galleryFolder.mkdirs();
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
        gallery.setFilename(fileName);
        /*저장되는 경로*/
        gallery.setFilepath("/gallery/" + fileName); /*저장된파일의이름,저장된파일의경로*/

        /*plno값 받아서 저장*/
        Plan byPlno =findByPlno(plno);
        gallery.setPlnoByGallery(byPlno);

        /*파일 저장*/
        galleryRepository.save(gallery);
    }

    public Plan findByPlno(Long plno) {
        Optional<Plan> id = planRepository.findById(plno);
        Plan byPlno = id.orElseThrow();
        return byPlno;
    }

    public List<GalleryDTO> galleryView(Long plno){
        List<GalleryDTO> galleryDTO=mybatisMapper.plnoByGallery(plno);
        return galleryDTO;
    }
}