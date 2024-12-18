package com.bytes2gram.ui.component;

import com.microsoft.playwright.Page;

/**
 * @author Dinesh
 */
public abstract class BaseComponent {

  protected Page page;

  protected BaseComponent(final Page page) {
    this.page = page;
  }
}
