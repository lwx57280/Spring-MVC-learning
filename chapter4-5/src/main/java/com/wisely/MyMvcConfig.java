package com.wisely;

import com.wisely.interceptor.DemoInterceptor;
import com.wisely.messageconverter.MyMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

@Configuration
@EnableWebMvc   //1、@EnableWebMvc注解，开启对SpringMVC的配置支持
@ComponentScan("com.wisely")
public class MyMvcConfig extends WebMvcConfigurerAdapter {  //2、继承WebMvcConfigurerAdapter类，重写其方法可对Spring MVC进行配置
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //3、addResourceLocations指的是文件放置的目录，addResourceHandler指的是对外暴露的访问路径。
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }


    /**
     * 通过重写configurePathMatch方法可不忽略“.”后面的参数
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    /**
     * 添加转向upload页面的ViewControllers,这样实现代码更简洁，管理更集中
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/toUpload").setViewName("/upload");
        registry.addViewController("/converter").setViewName("/converter");
        // 添加转向sse.jsp页面映射
        //registry.addViewController("/sse").setViewName("/sse");
        //registry.addViewController("/async").setViewName("/async");
    }

    /**
     * MultipartResolver配置
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return  multipartResolver;
    }


    //配置拦截器的Bean
    @Bean
    public DemoInterceptor demoInterceptor(){
        return new DemoInterceptor();
    }
    //重写addInterceptors方法，注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor());
    }

    /**
     *重写extendMessageConverters,并配置MyMessageConverter，
     * 仅添加一个自定义的HttpMessageConverter，不覆盖默注册的HttpMessageConverter。
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        converters.add(converter());
    }

    @Bean
    public MyMessageConverter converter() {
        return new MyMessageConverter();
    }
}

