package com.corewell.study.config;/*
package com.corewell.study.config;

import com.corewell.study.domain.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

*/
/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/07/13:56
 * @Description:
 *//*

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        //使用JWT，不需要csrf
        httpSecurity.csrf()
                       .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/login","/swagger-ui")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .headers()
                .cacheControl();

    }
   */
/* @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            Account account=
        }

    }*//*

}
*/
