package com.example.somoim.controller;

import com.example.somoim.error.ServiceError;
import com.example.somoim.error.ServiceException;
import com.example.somoim.service.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Provider implements AuthenticationProvider {
    @Autowired
    private AdminUserService adminUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException,ServiceException {
        String id = (String)authentication.getPrincipal();
        String pwd = (String)authentication.getCredentials();
        UserDetails adminUserDetails= null;

        log.debug("### ID : "+id);
        log.debug("### pwd : "+pwd);
        try{

            adminUserDetails  = adminUserService.loadUserByUsername(id);

            /*if(!pwd.equals(adminUserDetails.getPassword())){
                throw new ServiceException(ServiceError.PWD_MIS_MATCH);
            }
*/

        }catch (ServiceException e){
            log.info("PROVIDER MIS_MATCH ERROR {}",e);
            throw new ServiceException(ServiceError.PWD_MIS_MATCH);

        }catch (AuthenticationException ex){
            log.error("PROVIDER AuthenticationException {}",ex);
            throw new ServiceException(ServiceError.ID_MIS_MATCH);
        }catch (Exception exe){
            log.error("PROVIDER EXCEPTION {}",exe);
            throw new ServiceException(ServiceError.INTERNAL_SERVCER_ERROR);
        }


        return new UsernamePasswordAuthenticationToken(id,pwd,adminUserDetails.getAuthorities());

    }


    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
