package com.amd.internal.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan({"com.amd.internal.project"})
public class AmdInternalProjectApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AmdInternalProjectApplication.class, args);
	}

//	 @Bean
//	    public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
//	        FacesServlet servlet = new FacesServlet();
//	        return new ServletRegistrationBean<FacesServlet>(servlet, "*.jsf");
//	    }
//
//	    @Bean
//	    public FilterRegistrationBean<RewriteFilter> rewriteFilter() {
//	        FilterRegistrationBean<RewriteFilter> rwFilter = new FilterRegistrationBean<RewriteFilter>(new RewriteFilter());
//	        rwFilter.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST,
//	                DispatcherType.ASYNC, DispatcherType.ERROR));
//	        rwFilter.addUrlPatterns("/*");
//	        return rwFilter;
//	    }
}

