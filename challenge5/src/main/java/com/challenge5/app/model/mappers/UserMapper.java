package com.challenge5.app.model.mappers;

import com.challenge5.app.model.User;
import com.challenge5.app.model.dtos.UserNewDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User userDtoToUser(UserNewDto userNewDto);

    UserNewDto userToUserDto(User user);
}
