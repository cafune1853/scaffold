package com.doggy.groupId.doggy.module.web.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author doggy
 * Created on 2017-08-08.
 */
public class WebMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { com.doggy.groupId.doggy.module.web.config.RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { com.doggy.groupId.doggy.module.web.config.DispatcherServletConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        super.customizeRegistration(registration);
        // /为文件的临时落盘路径， 单文件大小限制为2M, 整个请求大小限制为4M, 只有大于1kb的文件才要临时落盘
        registration.setMultipartConfig(new MultipartConfigElement("/", 2 * 1024 * 1024, 4 * 1024 * 1024, 1024));
    }
}
