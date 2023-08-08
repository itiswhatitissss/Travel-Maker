package org.zerock.travelmaker.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.zerock.travelmaker.domain.SampleDomain;
import org.zerock.travelmaker.dto.SampleDTO;

import java.util.*;

@Mapper
@Repository
public interface SampleMapper {
    List<SampleDomain> getBoards();
}
