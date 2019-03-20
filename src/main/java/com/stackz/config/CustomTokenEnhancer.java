package com.stackz.config;


import com.stackz.model.UserDao;
import com.stackz.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class CustomTokenEnhancer implements TokenEnhancer {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> permissions = new HashMap<>();
        UserDao userDao = (UserDao)  oAuth2Authentication.getPrincipal();
        permissions.put("permissions", roleRepository.findByUsername(userDao.getUsername()).getPermissions());
        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(permissions);
        return oAuth2AccessToken;
    }
}
