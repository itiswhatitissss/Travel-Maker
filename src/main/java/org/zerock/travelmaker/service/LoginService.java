package org.zerock.travelmaker.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginService {
    Long getUno(String id, String password);
}
