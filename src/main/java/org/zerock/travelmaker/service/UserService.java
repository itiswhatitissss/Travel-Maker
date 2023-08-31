package org.zerock.travelmaker.service;

import org.zerock.travelmaker.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    Long getUno(String id);

    static class IdExistException extends Exception {}

    void join(UserDTO userDTO) throws IdExistException;

    List<Map<String, Object>> userList(Long uno);
}
