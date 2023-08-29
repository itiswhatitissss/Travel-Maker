package org.zerock.travelmaker.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.travelmaker.domain.Attend;
import org.zerock.travelmaker.domain.Plan;
import org.zerock.travelmaker.domain.Users;

public interface AttendRepository extends JpaRepository<Attend,Long> {
    @Query("select a.Attender from Attend a where a.unoByAttend= :uno and a.plnoByAttend= :plno")
    Long selectAttend(Users uno, Plan plno);

    @Transactional
    @Modifying
    @Query("UPDATE Attend a SET a.Attender = :de, a.Attender = :modify where a.unoByAttend= :uno and a.plnoByAttend= :plno")
    void updateAttend(@Param("uno") Users uno, @Param("plno") Plan plno, @Param("de") Long de, @Param("modify") Long modify);
}
