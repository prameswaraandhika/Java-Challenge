package com.challenge6.app.model.mappers;

import com.challenge6.app.model.Order;
import com.challenge6.app.model.dtos.OrderNewDto;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    Order orderNewToOrderDto(OrderNewDto orderNewDto);
}
