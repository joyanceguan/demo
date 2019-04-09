package com.joyance.demo.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class AnnotationScannerConfigurer implements ApplicationContextAware, BeanDefinitionRegistryPostProcessor{

    private ApplicationContext applicationContext;

    private Logger LOGGER = LoggerFactory.getLogger(AnnotationScannerConfigurer.class);


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        LOGGER.info("postProcessBeanFactory() beanDefinition的个数=====>"+beanFactory.getBeanDefinitionCount());
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
         LOGGER.info("postProcessBeanDefinitionRegistry() beanDefinitionName=====>"+JSON.toJSONString(registry.getBeanDefinitionNames()).toString());
        // 需要被代理的接口
        AnnotationScanner annotationScanner = new AnnotationScanner(registry);
        annotationScanner.setResourceLoader(applicationContext);
        // "com.pepsi.annotationproxy.service"是我 接口所在的包
        annotationScanner.scan("com.joyance.demo.spring");
    }
}

