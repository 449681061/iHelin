package me.ianhe.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextRootListener implements ServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void contextDestroyed(final ServletContextEvent event) {
        logger.debug("context is destroyed");
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        CommonConfig.init(context.getRealPath("/"), context.getContextPath());
        logger.info("context is initialized,real path: {},context path: {}",
                context.getRealPath("/"), context.getContextPath());
    }

}