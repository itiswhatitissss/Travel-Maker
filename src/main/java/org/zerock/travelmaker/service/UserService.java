package org.zerock.travelmaker.service;

import org.zerock.travelmaker.dto.UserDTO;

public interface UserService {
    Long getUno(String id);

    static class IdExistException extends Exception {

    }

    void join(UserDTO userDTO) throws IdExistException ;
}
