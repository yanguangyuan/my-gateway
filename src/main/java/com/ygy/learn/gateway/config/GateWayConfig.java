package com.ygy.learn.gateway.config;

import com.ygy.learn.gateway.filter.MyFirstGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : yanguangyuan
 * @Date : 2020/1/20 14:12
 * @Description :
 */
@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator getRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes().
                route(predicateSpec -> predicateSpec
                        .path("/settlement/**")
                        .filters(f -> f.filter(new MyFirstGatewayFilter()))
                        .uri("http://127.0.0.1:7296")
                        .id("settlement)")).build();
    }
}
