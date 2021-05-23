package ma.emsi.ioc_demo.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {
    //@Autowired private var dataSource: DataSource? = null
    override fun configure(auth : AuthenticationManagerBuilder){
        auth.inMemoryAuthentication().withUser("MERZAK").password("\$2a\$14\$RKHXeB9JC5IhvuXiwkK/1eVRjy27yRAicEzXJeeRnBcjLzEUdrU/K").roles("ADMIN","USER")
        /*val passwordEncoder = passwordEncoder()
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username as principal, password as credentials, active from users where username=? ")
            .authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
            .passwordEncoder(passwordEncoder).rolePrefix("ROLE_")*/
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.formLogin().loginPage("/login")
        //http.httpBasic(); // form in pop up
        http.authorizeRequests().antMatchers("/admin**/**", "/save**/**", "/delete**/**", "/edit**/**", "/form**/**")
            .hasRole("ADMIN")
        //http.authorizeRequests().antMatchers("/patients**/**").hasRole("USER")
        http.authorizeRequests().antMatchers("/user**/**", "/login", "/webjars/**").permitAll() // je laisse tout
        http.authorizeRequests().anyRequest().authenticated() // authoriser toutes les requete si auth
        //http.csrf();//activer le mecanisme cross site nb -> il est activé par default mais on peut le desactiver avec http.csrf().desabled();
        http.exceptionHandling().accessDeniedPage("/notAuthorized")
    }


    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}