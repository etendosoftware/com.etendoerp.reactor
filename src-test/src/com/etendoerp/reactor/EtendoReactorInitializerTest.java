package com.etendoerp.reactor;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.inject.Instance;
import javax.enterprise.util.TypeLiteral;

import org.junit.jupiter.api.Test;

class EtendoReactorInitializerTest {

  @Test
  void initializeShouldInvokeInitOnEverySetup() throws Exception {
    EtendoReactorSetup first = mock(EtendoReactorSetup.class);
    EtendoReactorSetup second = mock(EtendoReactorSetup.class);
    EtendoReactorInitializer initializer = new EtendoReactorInitializer();

    setSetups(initializer, instanceOf(first, second));

    initializer.initialize();

    verify(first, times(1)).init();
    verify(second, times(1)).init();
  }

  @Test
  void initializeShouldDoNothingWhenNoSetupsAreAvailable() throws Exception {
    EtendoReactorInitializer initializer = new EtendoReactorInitializer();

    setSetups(initializer, instanceOf());

    assertDoesNotThrow(initializer::initialize);
  }

  private Instance<EtendoReactorSetup> instanceOf(EtendoReactorSetup... setups) {
    List<EtendoReactorSetup> values = Arrays.asList(setups);
    return new Instance<>() {
      @Override
      public Instance<EtendoReactorSetup> select(Annotation... qualifiers) {
        throw new UnsupportedOperationException();
      }

      @Override
      public <U extends EtendoReactorSetup> Instance<U> select(Class<U> subtype,
          Annotation... qualifiers) {
        throw new UnsupportedOperationException();
      }

      @Override
      public <U extends EtendoReactorSetup> Instance<U> select(TypeLiteral<U> subtype,
          Annotation... qualifiers) {
        throw new UnsupportedOperationException();
      }

      @Override
      public boolean isUnsatisfied() {
        return values.isEmpty();
      }

      @Override
      public boolean isAmbiguous() {
        return false;
      }

      @Override
      public void destroy(EtendoReactorSetup instance) {
      }

      @Override
      public Iterator<EtendoReactorSetup> iterator() {
        return values.iterator();
      }

      @Override
      public EtendoReactorSetup get() {
        return values.get(0);
      }

    };
  }

  private void setSetups(EtendoReactorInitializer initializer, Instance<EtendoReactorSetup> setups)
      throws Exception {
    Field field = EtendoReactorInitializer.class.getDeclaredField("setups");
    field.setAccessible(true);
    field.set(initializer, setups);
  }
}
