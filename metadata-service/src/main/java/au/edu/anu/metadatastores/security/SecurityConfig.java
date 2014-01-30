package au.edu.anu.metadatastores.security;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

import au.edu.anu.metadatastores.util.properties.PropertyLoader;

/**
 * <p>SecurityConfig</p>
 *
 * <p>The Australian National University</p>
 *
 * <p>Configures the methods and restrictions for logging in</p>
 *
 * @author Genevieve Turner
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@ComponentScan("au.edu.anu.metadatastores")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		Properties properties = PropertyLoader.loadProperties("ldap.properties");
		LdapUserDetailsMapper contextMapper = new LdapUserDetailsMapper();
		contextMapper.setRoleAttributes(new String[]{"affiliation"});
		contextMapper.setPasswordAttributeName("password");
		
		String ldapUri = properties.getProperty("ldap.uri");
		String baseDn = properties.getProperty("ldap.baseDn");
		
		String contextLdapUri = ldapUri + "/" + baseDn;
		
		LOGGER.debug("Ldap Url: {}", contextLdapUri);
		
		auth.ldapAuthentication()
			.userDnPatterns("uid={0}, ou=People")
				.contextSource()
					.url(contextLdapUri)
					.and()
				.userDetailsContextMapper(contextMapper);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().permitAll()
				.and()
			.formLogin().permitAll()
				.loginPage("/login")
				.and()
			.logout().logoutSuccessUrl("/")
				.and()
			.httpBasic();
	}
}
