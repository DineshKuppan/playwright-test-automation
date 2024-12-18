package com.bytes2gram.ui.component;

import com.microsoft.playwright.Page;

/**
 * @author Dinesh
 */
public final class Header extends BaseComponent {

  public Header(final Page page) {
    super(page);
  }

  public void clickOnHamburgerIcon() {
    page.click("#react-burger-menu-btn");
  }
}
