package com.ygy.learn.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author : yanguangyuan
 * @Date : 2020/1/20 13:53
 * @Description : 路由过滤器，针对单个路由有效，或者通过配置使其全局有效
 *      两种实现方式
 *      方式一:
 *              1.实现GatewayFilter和Ordered接口
 *              2.通过配置将该filter注册到路由中 {@link com.ygy.learn.gateway.config.GateWayConfig} ,实测，此时已经可以将yml里面写路由配置去掉了
 *      方式二：{@link MyGatewayFilterFactory}
 *
 */
@Slf4j
public class MyFirstGatewayFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("路由过滤器实现一,uri:{}",exchange.getRequest().getPath().value());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
