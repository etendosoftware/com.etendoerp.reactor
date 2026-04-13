package com.etendoerp.reactor;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

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
