package com.iFundi.config;
//package com.compulynx.compas.config;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.web.accept.ContentNegotiationManager;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
//import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
//
//import com.compulynx.compas.reports.resolver.CsvViewResolver;
//import com.compulynx.compas.reports.resolver.ExcelViewResolver;
//import com.compulynx.compas.reports.resolver.PdfResolver;
//
//@Configuration
//@EnableWebMvc
//public class ReportConfig implements WebMvcConfigurer{
//
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
////
////	@Override
////	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
////	    configurer
////	            .defaultContentType(MediaType.APPLICATION_JSON)
////	            .favorPathExtension(true);
////	}
////	
////	@Bean
////    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
////	    ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
////	    resolver.setContentNegotiationManager(manager);
////	    
////	    List<ViewResolver> resolvers = new ArrayList<>();
////	
////	    resolvers.add(csvViewResolver());
////	    resolvers.add(excelViewResolver());
////	    resolvers.add(pdfViewResolver());
////	
////	    resolver.setViewResolvers(resolvers);
////	    return resolver;
////	}
////	
////	@Bean
////	public ViewResolver excelViewResolver() {
////	    return new ExcelViewResolver();
////	}
////	
////	@Bean
////	public ViewResolver csvViewResolver() {
////	    return new CsvViewResolver();
////	}
////	
////	@Bean
////	public ViewResolver pdfViewResolver() {
////	    return new PdfResolver();
////	}
//}
