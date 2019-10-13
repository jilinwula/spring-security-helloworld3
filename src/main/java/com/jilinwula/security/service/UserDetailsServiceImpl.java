package com.jilinwula.security.service;

import com.jilinwula.security.entity.UserInfo;
import com.jilinwula.security.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserInfo userInfo = userInfoRepository.findByUsername(s);

        return new User(userInfo.getUsername(), "1111", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
