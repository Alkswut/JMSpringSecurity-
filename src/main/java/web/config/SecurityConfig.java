package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import web.config.handler.LoginSuccessHandler;
import web.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userDetailsService")
    private final UserDetailsService userDetailsService; // сервис, с помощью которого тащим пользователя
    private final LoginSuccessHandler loginSuccessHandler;// класс, в котором описана логика перенаправления пользователей по ролям

    public SecurityConfig(@Qualifier("userDetailsServiceImpl")
                                  UserDetailsService userDetailsService,
                          LoginSuccessHandler loginSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    // конфигурация для прохождения аутентификации
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    //Конфигурация пользователя, брать из памяти
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("ADMIN").password("ADMIN").roles("ADMIN");
//        auth.userDetailsService(userDetailsServiceBean()).getUserDetailsService();
//    }

    //Конфигурация доступных login,logout и иных настроек
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // делаем страницу регистрации недоступной для авторизированных пользователей
        http.authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin().successHandler(loginSuccessHandler);
        // hasAuthority("ADMIN")

//        http.formLogin()
//                // указываем страницу с формой логина
////                .loginPage("/login")
//                //указываем логику обработки при логине
//                .successHandler(new LoginSuccessHandler())
//                // указываем action с формы логина
//                .loginProcessingUrl("/login")
//                // Указываем параметры логина и пароля с формы логина
//                .usernameParameter("j_username")
//                .passwordParameter("j_password")
//                // даем доступ к форме логина всем
//                .permitAll();

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder(11);
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }
}
//                                                          IN MEMORY
//    @Bean
//    public UserDetailsService users() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$12$DbqJAswhlC.o/ACE9X87TO7eD9FRaNi.pRUdtda/VdI5lI695uu2i")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$DbqJAswhlC.o/ACE9X87TO7eD9FRaNi.pRUdtda/VdI5lI695uu2i")
//                .roles("ADMIN", "USER")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//                                                          IN JDBC standard
//    @Bean
//    public JdbcUserDetailsManager users(DataSource dataSource) {
//                UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$12$DbqJAswhlC.o/ACE9X87TO7eD9FRaNi.pRUdtda/VdI5lI695uu2i")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$DbqJAswhlC.o/ACE9X87TO7eD9FRaNi.pRUdtda/VdI5lI695uu2i")
//                .roles("ADMIN", "USER")
//                .build();
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        if (jdbcUserDetailsManager.userExists(user.getUsername())){
//            jdbcUserDetailsManager.deleteUser(user.getUsername());
//        }
//        if (jdbcUserDetailsManager.userExists(admin.getUsername())){
//            jdbcUserDetailsManager.deleteUser(admin.getUsername());
//        }
//        jdbcUserDetailsManager.createUser(user);
//        jdbcUserDetailsManager.createUser(admin);
//        return jdbcUserDetailsManager;
//    }