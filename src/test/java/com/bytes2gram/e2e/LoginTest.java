package com.bytes2gram.e2e;

import static com.bytes2gram.config.ConfigurationManager.config;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.bytes2gram.annotation.DataSource;
import com.bytes2gram.annotation.Smoke;
import com.bytes2gram.annotation.Validation;
import com.bytes2gram.dto.LoginDto;
import com.bytes2gram.testng.BaseTest;
import com.bytes2gram.ui.page.LoginPage;
import com.bytes2gram.ui.page.ProductsPage;
import com.microsoft.playwright.Browser;
import io.github.artsok.ParameterizedRepeatedIfExceptionsTest;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * @author Dinesh
 */
@Feature("Login Test")
public class LoginTest extends BaseTest {

  private static final String CSV_PATH = "login.csv";
  private static final String VIDEO_PATH = "login/";

  @BeforeMethod
  public void createBrowserContextAndPageAndLoginPageInstances(Method method) {
    String methodName = method.getName();

    if (config().video()) {
      browserContext =
          browser.newContext(
              new Browser.NewContextOptions()
                  .setRecordVideoDir(
                      Paths.get(config().baseTestVideoPath() + VIDEO_PATH + methodName)));
    } else {
      browserContext = browser.newContext();
    }

    page = browserContext.newPage();
    loginPage = createInstance(LoginPage.class);
  }

  @Attachment(value = "Failed Test Case Screenshot", type = "image/png")
  protected byte[] captureScreenshotOnFailure() {
    return loginPage.captureScreenshot();
  }

  @AfterMethod
  public void closeBrowserContextSession() {
    browserContext.close();
  }

  @Smoke
  @Story("User enters correct login credentials")
  @Description(
      "Test that verifies user gets redirected to 'Products' page after submitting correct login credentials")
  @ParameterizedRepeatedIfExceptionsTest
  @DataSource(id = "TC-1", fileName = CSV_PATH, clazz = LoginDto.class)
  public void testCorrectLoginCredentials(final LoginDto data) {
    ProductsPage productsPage = loginPage.loginAs(data.getUsername(), data.getPassword());
    assertThat(productsPage.getTitle()).hasText("Products");
  }

  @Validation
  @Story("User enters incorrect login credentials")
  @Description(
      "Test that verifies user gets error message after submitting incorrect login credentials")
  @ParameterizedRepeatedIfExceptionsTest
  @DataSource(id = "TC-2", fileName = CSV_PATH, clazz = LoginDto.class)
  public void testIncorrectLoginCredentials(final LoginDto data) {
    loginPage.loginAs(data.getUsername(), data.getPassword());

    assertThat(loginPage.getErrorMessage()).hasText(data.getErrorMessage());
  }

  @Validation
  @Story("User keeps the username blank")
  @Description(
      "Test that verifies user gets error message after submitting login credentials where the username is blank")
  @ParameterizedRepeatedIfExceptionsTest
  @DataSource(id = "TC-3", fileName = CSV_PATH, clazz = LoginDto.class)
  public void testBlankUserName(final LoginDto data) {
    loginPage.open().typePassword(data.getPassword()).submitLogin();

    assertThat(loginPage.getErrorMessage()).hasText(data.getErrorMessage());
  }

  @Validation
  @Story("User keeps the password blank")
  @Description(
      "Test that verifies user gets error message after submitting login credentials where the password is blank")
  @ParameterizedRepeatedIfExceptionsTest
  @DataSource(id = "TC-4", fileName = CSV_PATH, clazz = LoginDto.class)
  public void testBlankPassword(final LoginDto data) {
    loginPage.open().typeUsername(data.getUsername()).submitLogin();

    assertThat(loginPage.getErrorMessage()).hasText(data.getErrorMessage());
  }

  @Validation
  @Story("User is locked out")
  @Description(
      "Test that verifies user gets error message after submitting login credentials for locked out user")
  @ParameterizedRepeatedIfExceptionsTest
  @DataSource(id = "TC-5", fileName = CSV_PATH, clazz = LoginDto.class)
  public void testLockedOutUser(final LoginDto data) {
    loginPage.loginAs(data.getUsername(), data.getPassword());

    assertThat(loginPage.getErrorMessage()).hasText(data.getErrorMessage());
  }
}
