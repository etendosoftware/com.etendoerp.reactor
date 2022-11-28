package com.etendoerp.reactor;


import java.util.List;

import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openbravo.base.weld.WeldUtils;
import org.openbravo.client.kernel.KernelInitializer;

public class EtendoReactorStartup implements ServletContextListener {
  private static final Logger log = LogManager.getLogger();

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    log.info("Etendo Reactor Startup");
    WeldUtils.getInstanceFromStaticBeanManager(EtendoReactorInitializer.class).initialize();
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {

  }
}
