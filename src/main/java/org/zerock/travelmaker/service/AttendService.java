package org.zerock.travelmaker.service;

import java.util.List;
import java.util.Map;

public interface AttendService {
    void doAttend(Long uno, Long plno ,Long attender);

    void modifyAttend(Long uno, Long plno ,Long attender);

    List<Map<String, Object>> listAttend(Long plno);
}
