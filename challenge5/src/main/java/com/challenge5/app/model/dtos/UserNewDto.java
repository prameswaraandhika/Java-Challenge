package com.challenge5.app.model.dtos;


import com.challenge5.app.model.OrderDetail;

import java.util.List;

public record UserNewDto(
		 String username,
		 String emailAdress,
		 String password
) {

}
