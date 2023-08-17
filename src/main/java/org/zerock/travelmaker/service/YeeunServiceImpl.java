package org.zerock.travelmaker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.travelmaker.domain.SampleDomain;
import org.zerock.travelmaker.mapper.SampleMapper;

import java.util.List;

@Service
public class YeeunServiceImpl implements YeeunService {
    @Autowired
    private SampleMapper sampleMapper;
    @Override
    public List<SampleDomain> getBoards() {
        return sampleMapper.getBoards();
    }

    @Override
    public void register() {

    }


}
