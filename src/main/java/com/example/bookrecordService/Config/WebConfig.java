//package com.example.bookrecordService.Config;
//
//import jakarta.servlet.Filter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//
//    @Bean
//    public FilterRegistrationBean customFilter() {
//        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
//        filterRegistrationBean.setFilter(new CustomFilter()); // Filter 등록
//        filterRegistrationBean.setOrder(1); // Filter 순서 1 설정
//        filterRegistrationBean.addUrlPatterns("/*"); // 전체 URL에 Filter 적용
//
//        return filterRegistrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean loginFilter() {
//        FilterRegistrationBean<Filter> filterRegistrationBean= new FilterRegistrationBean<>();
//        //Filter 등록
//        filterRegistrationBean.setFilter(new CustomFilter());
//        filterRegistrationBean.setOrder(1);
//
//        return filterRegistrationBean;
//    }
//}
