package com.doggy.groupId.doggy.module.provider.config;


import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author doggy
 *         Created on 2016-07-05.
 */
public class WebInitlizer extends AbstractContextLoaderInitializer {
    //Implement this method to add other filter or listener.
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        //add my servlet here.
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(SpringConfig.class);
        return annotationConfigWebApplicationContext;
    }
}
