package com.etendoerp.reactor;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.openbravo.client.kernel.ApplicationInitializer;

@ApplicationScoped
public class EtendoReactorInitializer implements ApplicationInitializer {
  @Inject
  @Any
  private Instance<EtendoReactorSetup> setups;

  public void initialize() {
    setups.forEach(EtendoReactorSetup::init);
  }
}
