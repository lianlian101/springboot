package com.test.springboot.general.config;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class FastJsonAutoConfig {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        //定义一个converters转换消息的对象
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //添加fastjson的配置信息，比如: 是否需要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                //结果是否格式化,默认为false
                SerializerFeature.PrettyFormat,
                //List字段如果为null,输出为[],而非null
                //SerializerFeature.WriteNullListAsEmpty,
                //字符类型字段如果为null,输出为"",而非null
                //SerializerFeature.WriteNullStringAsEmpty,
                //Boolean字段如果为null,输出为false,而非null
                //SerializerFeature.WriteNullBooleanAsFalse,
                //是否输出值为null的字段,默认为false
                SerializerFeature.WriteMapNullValue,
                //消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
                SerializerFeature.DisableCircularReferenceDetect
        );
        //在converter中添加配置信息
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        //将converter赋值给HttpMessageConverter
        HttpMessageConverter<?> httpMessageConverter = fastJsonHttpMessageConverter;
        //返回HttpMessageConverters对象
        return new HttpMessageConverters(httpMessageConverter);
    }

}
