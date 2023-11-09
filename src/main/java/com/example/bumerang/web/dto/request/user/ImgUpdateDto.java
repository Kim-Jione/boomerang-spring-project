package com.example.bumerang.web.dto.request.user;


import com.example.bumerang.web.dto.response.user.UserRespDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImgUpdateDto{
    private String userProfileImg;
    private String userId;
}
