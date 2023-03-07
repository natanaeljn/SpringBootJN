package projeto.springboot.springbootTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EntityScan(basePackages = "projeto.springboot.model")
@ComponentScan(basePackages = {"projeto.*"})
@EnableJpaRepositories(basePackages = {"projeto.springboot.repository"})
@EnableTransactionManagement
@EnableWebMvc
public class SpringbootTestApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTestApplication.class, args);
	}

	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("/login");
		registry.setOrder(Ordered.LOWEST_PRECEDENCE);
		registry.addViewController("/logout").setViewName("/logout");
		registry.setOrder(Ordered.LOWEST_PRECEDENCE);
		
		
	}
	 @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	   registry.addResourceHandler("/webjars/**", "/resources/**", "/static/**", "/img/**", "/css/**", "/js/**",
					"classpath:/static/", "classpath:/resources/")
			.addResourceLocations("/webjars/", "/resources/",
							"classpath:/static/**", "classpath:/static/img/**", "classpath:/static/",
							"classpath:/resources/", "classpath:/static/css/", "classpath:/static/js/", "/resources/**",
							"/WEB-INF/classes/static/**");
			
	  }
	
	
	
}