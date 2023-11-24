package com.challenge6.app.model.mappers;

import com.challenge6.app.model.User;
import com.challenge6.app.model.dtos.UserNewDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User userDtoToUser(UserNewDto userNewDto);

    UserNewDto userToUserDto(User user);
}
