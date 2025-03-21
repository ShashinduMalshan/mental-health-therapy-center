package com.service.mental_health_therapy_center.Bo.custom;

import com.service.mental_health_therapy_center.dto.LoginDto;

public interface LoginBo {


     boolean authenticateUser(LoginDto loginDto);

}
