package org.zerock.travelmaker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.travelmaker.mapper.MybatisMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MainServiceImpl implements MainService{

    private final MybatisMapper mybatisMapper;

    @Override
    public List<Map<String, Object>> getParty(Long uno) {
        List<Map<String,Object>> result =mybatisMapper.getPartyList(uno);
        return result;
    }


}
