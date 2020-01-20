package com.ygy.learn.gateway.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @author : yanguangyuan
 * @Date : 2020/1/20 13:53
 * @Description : 路由过滤器，针对单个路由有效，或者通过配置使其全局有效
 * 两种实现方式
 * 方式一:{@link MyFirstGatewayFilter}
 * 方式二：
 *         1.继承AbstractGatewayFilterFactory类,
 *         2.如果需要额外配置信息，新建配置类(或内部类)，工厂无参构造时supper进行初始化**
 *         3.在yml中进行配置
 */
@Component
@Slf4j
public class MyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyGatewayFilterFactory.Config> {
    /**
     * 默认构造
     */
    public MyGatewayFilterFactory(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) ->
        {
            if (config.enable) {
                log.info("路由过滤器实现二,uri:{}", exchange.getRequest().getPath().value());
            }
            return chain.filter(exchange);
        };
    }

    /**
     * 自定义配置信息
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Config {
        private Boolean enable = Boolean.TRUE;
    }
}
