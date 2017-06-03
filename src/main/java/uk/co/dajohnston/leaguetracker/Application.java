package uk.co.dajohnston.leaguetracker;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;

@SpringBootApplication
@EnableOAuth2Client
@SuppressWarnings("PMD.UseUtilityClass")
public class Application extends WebSecurityConfigurerAdapter {

    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**").addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class).authorizeRequests()
                .antMatchers("/", "/login**").permitAll().anyRequest().authenticated().and().logout().logoutSuccessUrl("/").permitAll()
                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and();
    }

    private Filter ssoFilter() {
        OAuth2ClientAuthenticationProcessingFilter facebookFilter = createOAuth2Filter("/login/facebook", facebook(),
                facebookResource().getUserInfoUri());
        OAuth2ClientAuthenticationProcessingFilter githubFilter = createOAuth2Filter("/login/github", github(),
                githubResource().getUserInfoUri());
        OAuth2ClientAuthenticationProcessingFilter googleFilter = createOAuth2Filter("/login/google", google(),
                googleResource().getUserInfoUri());

        List<Filter> filters = new ArrayList<>();
        filters.add(facebookFilter);
        filters.add(githubFilter);
        filters.add(googleFilter);
        CompositeFilter filter = new CompositeFilter();
        filter.setFilters(filters);
        return filter;

    }

    private OAuth2ClientAuthenticationProcessingFilter createOAuth2Filter(String url, AuthorizationCodeResourceDetails resourceDetails,
            String userInfoUri) {
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(userInfoUri, resourceDetails.getClientId());
        OAuth2RestTemplate template = new OAuth2RestTemplate(resourceDetails, oauth2ClientContext);
        tokenServices.setRestTemplate(template);
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(url);
        filter.setRestTemplate(template);
        filter.setTokenServices(tokenServices);
        return filter;
    }

    @Bean
    @ConfigurationProperties("facebook.client")
    public AuthorizationCodeResourceDetails facebook() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("facebook.resource")
    public ResourceServerProperties facebookResource() {
        return new ResourceServerProperties();
    }

    @Bean
    @ConfigurationProperties("github.client")
    public AuthorizationCodeResourceDetails github() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("github.resource")
    public ResourceServerProperties githubResource() {
        return new ResourceServerProperties();
    }

    @Bean
    @ConfigurationProperties("google.client")
    public AuthorizationCodeResourceDetails google() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("google.resource")
    public ResourceServerProperties googleResource() {
        return new ResourceServerProperties();
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }
}
