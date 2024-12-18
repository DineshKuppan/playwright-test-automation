package com.bytes2gram.util;

import static com.bytes2gram.config.ConfigurationManager.config;

import com.bytes2gram.factory.BrowserFactory;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

/**
 * @author Dinesh
 */
public final class BrowserManager {

  private BrowserManager() {}

  public static Browser getBrowser(final Playwright playwright) {
    return BrowserFactory.valueOf(config().browser().toUpperCase()).createInstance(playwright);
  }
}
