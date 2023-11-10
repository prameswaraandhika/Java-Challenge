package com.challenge5.app.model.mappers;

import com.challenge5.app.model.Order;
import com.challenge5.app.model.dtos.OrderNewDto;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    Order orderNewToOrderDto(OrderNewDto orderNewDto);
}
