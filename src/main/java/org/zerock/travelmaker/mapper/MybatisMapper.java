package org.zerock.travelmaker.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MybatisMapper {
    public List<Map<String,Object>> getPartyList(Long uno);
    public List<Map<String,Object>> getPlanList(Long pno);

}
