package com.example.bumerang.domain.userPortfolio;

import com.example.bumerang.web.dto.SessionUserDto;
import com.example.bumerang.web.dto.request.user.LoginDto;
import com.example.bumerang.web.dto.response.user.*;

import java.util.List;

public interface UserPortfolioDao {

    public UserPortfolio findById(Integer userId);

    public List<UserPortfolio> findAll();

    public void insert(UserPortfolio user);

    public void update(UserPortfolio user);

    public void delete(Integer userId);

    public SessionUserDto findByUser(LoginDto loginDto);

    UserRespDto findByDetail(Integer userId);

    SearchPwDto findToPw(SearchPwDto searchPwDto);

    SearchIdDto findToLoginId(SearchIdDto searchIdDto);
    List<UserJobSearchDto> myJSList(Integer userId);

    List<UserPerformanceDto> myPfList(Integer userId);

}
