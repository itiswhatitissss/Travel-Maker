package org.zerock.travelmaker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.travelmaker.domain.SampleDomain;
import org.zerock.travelmaker.dto.SampleDTO;
import org.zerock.travelmaker.mapper.SampleMapper;

import java.util.List;
import java.util.Map;

@Service
public class SampleServiceImpl implements SampleService {
    @Autowired
    private SampleMapper sampleMapper;
    @Override
    public List<SampleDomain> getBoards() {
        return sampleMapper.getBoards();
    }
}
