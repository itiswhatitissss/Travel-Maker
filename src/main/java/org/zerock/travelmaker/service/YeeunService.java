package org.zerock.travelmaker.service;

import org.zerock.travelmaker.domain.SampleDomain;

import java.util.List;

public interface YeeunService {
    List<SampleDomain> getBoards();

    void register();
}
