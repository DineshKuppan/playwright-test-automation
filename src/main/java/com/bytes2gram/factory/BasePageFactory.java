package com.bytes2gram.factory;

import com.bytes2gram.ui.page.BasePage;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dinesh
 */
@Slf4j
public final class BasePageFactory {

  private BasePageFactory() {}

  public static <T extends BasePage> T createInstance(final Page page, final Class<T> clazz) {
    try {
      BasePage instance = clazz.getDeclaredConstructor().newInstance();

      instance.setAndConfigurePage(page);
      instance.initComponents();

      return clazz.cast(instance);
    } catch (Exception e) {
      log.error("BasePageFactory::createInstance", e);
    }

    throw new NullPointerException("Page class instantiation failed.");
  }
}
