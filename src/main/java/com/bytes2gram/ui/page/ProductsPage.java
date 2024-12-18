package com.bytes2gram.ui.page;

import com.bytes2gram.factory.BasePageFactory;
import com.bytes2gram.ui.component.Header;
import com.bytes2gram.ui.component.SideNavMenu;
import com.microsoft.playwright.Locator;
import io.qameta.allure.Step;

/**
 * @author Dinesh
 */
public final class ProductsPage extends BasePage {

  private Header header;
  private SideNavMenu sideNavMenu;

  @Override
  public void initComponents() {
    header = new Header(page);
    sideNavMenu = new SideNavMenu(page);
  }

  @Step("Get title of the 'Products' page")
  public Locator getTitle() {
    return page.locator(".title");
  }

  @Step("Click on 'Logout' button from side navigation menu")
  public LoginPage clickOnLogout() {
    header.clickOnHamburgerIcon();
    sideNavMenu.clickOnLogout();

    return BasePageFactory.createInstance(page, LoginPage.class);
  }
}
