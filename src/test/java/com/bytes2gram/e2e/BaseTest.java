package com.bytes2gram.e2e;

import static com.bytes2gram.config.ConfigurationManager.config;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

import com.bytes2gram.factory.BasePageFactory;
import com.bytes2gram.ui.page.BasePage;
import com.bytes2gram.ui.page.LoginPage;
import com.bytes2gram.util.BrowserManager;
import com.google.common.collect.ImmutableMap;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.RegisterExtension;

/**
 * @author Dinesh
 */
@TestInstance(Lifecycle.PER_CLASS)
public abstract class BaseTest {

  protected Playwright playwright;
  protected Browser browser;
  protected BrowserContext browserContext;
  protected Page page;
  protected LoginPage loginPage;

  @RegisterExtension
  AfterTestExecutionCallback callback =
      context -> {
        Optional<Throwable> exception = context.getExecutionException();

        if (exception.isPresent()) {
          captureScreenshotOnFailure();
        }
      };

  protected abstract byte[] captureScreenshotOnFailure();

  protected <T extends BasePage> T createInstance(Class<T> basePage) {
    return BasePageFactory.createInstance(page, basePage);
  }

  @BeforeAll
  public void createPlaywrightAndBrowserInstancesAndSetupAllureEnvironment() {
    playwright = Playwright.create();
    browser = BrowserManager.getBrowser(playwright);

    allureEnvironmentWriter(
        ImmutableMap.<String, String>builder()
            .put("Platform", System.getProperty("os.name"))
            .put("Version", System.getProperty("os.version"))
            .put("Browser", StringUtils.capitalize(config().browser()))
            .put("Context URL", config().baseUrl())
            .build(),
        config().allureResultsDir() + "/");
  }

  @AfterAll
  public void closeBrowserAndPlaywrightSessions() {
    browser.close();
    playwright.close();
  }
}
