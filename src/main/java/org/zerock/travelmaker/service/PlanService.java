//package org.zerock.travelmaker.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import org.zerock.travelmaker.controller.FileHandler;
//import org.zerock.travelmaker.domain.Plan;
//import org.zerock.travelmaker.repository.PlanRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class PlanService {
//
//    private final PlanRepository planRepository;
//    private final FileHandler fileHandler;
//
//    @Autowired
//    public PlanService(PlanRepository planRepository) {
//        this.planRepository = planRepository;
//        this.fileHandler = new FileHandler();
//    }
//
//    public Plan addPlan(
//            Plan plan,
//            List<MultipartFile> files
//    ) throws Exception {
//        // 파일을 저장하고 그 Board 에 대한 list 를 가지고 있는다
//        List<Plan> list = fileHandler.parseFileInfo(plan.getPlno(), files);
//
//        if (list.isEmpty()){
//            // TODO : 파일이 없을 땐 어떻게 해야할까.. 고민을 해보아야 할 것
//        }
//        // 파일에 대해 DB에 저장하고 가지고 있을 것
//        else{
//            List<Plan> pictureBeans = new ArrayList<>();
//            for (Plan plans : list) {
//                pictureBeans.add(planRepository.save(plans));
//            }
//        }
//
//        return planRepository.save(plan);
//    }
//
//    public List<Plan> findPlans() {
//        return planRepository.findAll();
//    }
//
//    public Optional<Plan> findPlan(Long Plno) {
//        return planRepository.findById(Plno);
//    }
//
//}