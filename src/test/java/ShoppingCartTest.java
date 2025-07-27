import static java.lang.Thread.sleep;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ShoppingCartTest {

  @Test
  public void checkDiscount0() throws InterruptedException {
    SoftAssert softAssert = new SoftAssert();
    ChromeOptions options = new ChromeOptions();
    HashMap<String, Object> chromePrefs = new HashMap<>();
    chromePrefs.put("credentials_enable_service", false);
    chromePrefs.put("profile.password_manager_enabled", false);
    options.setExperimentalOption("prefs", chromePrefs);
    options.addArguments("--incognito");
    options.addArguments("--disable-notifications");
    options.addArguments("--disable-popup-blocking");
    options.addArguments("--disable-infobars");
    WebDriver driver = new ChromeDriver(options);

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get("https://www.sharelane.com/cgi-bin/register.py?page=2&"
        + "zip_code=12234&first_name=test&last_name=test&email=test%40test.cd&"
        + "password1=123456789&password2=123456789");
    String email = driver.findElement(By.xpath("/html/body/center/table/tbody/"
        + "tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/b")).getText();
    String password = driver.findElement(By.xpath("//html/body/center/"
        + "table/tbody/tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]")).getText();
    driver.get("https://www.sharelane.com/cgi-bin/main.py");
    driver.findElement(By.name("email")).sendKeys(email);
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.cssSelector("[value=Login]")).click();
    String valueAfterEquals = null;
    List<WebElement> links = driver.findElements(
        By.xpath("//a[starts-with(@href, './show_book.py?book_id')]"));

    if (!links.isEmpty()) {
      WebElement firstLink = links.get(0);
      String hrefValue = firstLink.getAttribute("href");
      System.out.println("Найденная ссылка: " + hrefValue);

      int index = hrefValue.indexOf("=");
      if (index != -1 && index + 1 < hrefValue.length()) {
        valueAfterEquals = hrefValue.substring(index + 1);
        System.out.println("Значение после '=': " + valueAfterEquals);

      } else {
        System.out.println("Значение после '=' не найдено или отсутствует.");
      }
    } else {
      System.out.println("Ссылки с указанным href не найдены.");
    }
    driver.get("https://www.sharelane.com/cgi-bin/add_to_cart.py?book_id=" + valueAfterEquals);
    driver.get("https://www.sharelane.com/cgi-bin/shopping_cart.py");
    driver.get("https://www.sharelane.com/cgi-bin/shopping_cart.py");
    driver.findElement(By.name("q")).clear();
    driver.findElement(By.name("q")).sendKeys("19");
    driver.findElement(By.cssSelector("[value=Update]")).click();
    sleep(5000);

    String discountPercent = driver.findElement(
        By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
    String discount = driver.findElement(
        By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
    String total = driver.findElement(
        By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();

    softAssert.assertEquals(discount, "0");
    softAssert.assertEquals(discountPercent, "0");
    softAssert.assertEquals(total, "190");

    softAssert.assertAll();
    driver.quit();
  }

  @Test
  public void checkDiscount4percent() throws InterruptedException {
    SoftAssert softAssert = new SoftAssert();
    ChromeOptions options = new ChromeOptions();
    HashMap<String, Object> chromePrefs = new HashMap<>();
    chromePrefs.put("credentials_enable_service", false);
    chromePrefs.put("profile.password_manager_enabled", false);
    options.setExperimentalOption("prefs", chromePrefs);
    options.addArguments("--incognito");
    options.addArguments("--disable-notifications");
    options.addArguments("--disable-popup-blocking");
    options.addArguments("--disable-infobars");
    WebDriver driver = new ChromeDriver(options);

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get("https://www.sharelane.com/cgi-bin/register.py?page=2&"
        + "zip_code=12234&first_name=test&last_name=test&email=test%40test.cd&"
        + "password1=123456789&password2=123456789");
    String email = driver.findElement(By.xpath("/html/body/center/table/tbody/"
        + "tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/b")).getText();
    String password = driver.findElement(By.xpath("//html/body/center/"
        + "table/tbody/tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]")).getText();
    driver.get("https://www.sharelane.com/cgi-bin/main.py");
    driver.findElement(By.name("email")).sendKeys(email);
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.cssSelector("[value=Login]")).click();
    String valueAfterEquals = null;
    List<WebElement> links = driver.findElements(
        By.xpath("//a[starts-with(@href, './show_book.py?book_id')]"));

    if (!links.isEmpty()) {
      WebElement firstLink = links.get(0);
      String hrefValue = firstLink.getAttribute("href");
      System.out.println("Найденная ссылка: " + hrefValue);

      int index = hrefValue.indexOf("=");
      if (index != -1 && index + 1 < hrefValue.length()) {
        valueAfterEquals = hrefValue.substring(index + 1);
        System.out.println("Значение после '=': " + valueAfterEquals);

      } else {
        System.out.println("Значение после '=' не найдено или отсутствует.");
      }
    } else {
      System.out.println("Ссылки с указанным href не найдены.");
    }
    driver.get("https://www.sharelane.com/cgi-bin/add_to_cart.py?book_id=" + valueAfterEquals);
    driver.get("https://www.sharelane.com/cgi-bin/shopping_cart.py");
    driver.get("https://www.sharelane.com/cgi-bin/shopping_cart.py");
    driver.findElement(By.name("q")).clear();
    driver.findElement(By.name("q")).sendKeys("499");
    driver.findElement(By.cssSelector("[value=Update]")).click();
    sleep(5000);

    String discountPercent = driver.findElement(
        By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
    String discount = driver.findElement(
        By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
    String total = driver.findElement(
        By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();

    softAssert.assertEquals(discount, "199.6");
    softAssert.assertEquals(discountPercent, "4");
    softAssert.assertEquals(total, "4790.40");

    softAssert.assertAll();
    driver.quit();
  }

  @Test
  public void checkDiscount7percent() throws InterruptedException {
    SoftAssert softAssert = new SoftAssert();
    ChromeOptions options = new ChromeOptions();
    HashMap<String, Object> chromePrefs = new HashMap<>();
    chromePrefs.put("credentials_enable_service", false);
    chromePrefs.put("profile.password_manager_enabled", false);
    options.setExperimentalOption("prefs", chromePrefs);
    options.addArguments("--incognito");
    options.addArguments("--disable-notifications");
    options.addArguments("--disable-popup-blocking");
    options.addArguments("--disable-infobars");
    WebDriver driver = new ChromeDriver(options);

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get("https://www.sharelane.com/cgi-bin/register.py?page=2&"
        + "zip_code=12234&first_name=test&last_name=test&email=test%40test.cd&"
        + "password1=123456789&password2=123456789");
    String email = driver.findElement(By.xpath("/html/body/center/table/tbody/"
        + "tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/b")).getText();
    String password = driver.findElement(By.xpath("//html/body/center/"
        + "table/tbody/tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]")).getText();
    driver.get("https://www.sharelane.com/cgi-bin/main.py");
    driver.findElement(By.name("email")).sendKeys(email);
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.cssSelector("[value=Login]")).click();
    String valueAfterEquals = null;
    List<WebElement> links = driver.findElements(
        By.xpath("//a[starts-with(@href, './show_book.py?book_id')]"));

    if (!links.isEmpty()) {
      WebElement firstLink = links.get(0);
      String hrefValue = firstLink.getAttribute("href");
      System.out.println("Найденная ссылка: " + hrefValue);

      int index = hrefValue.indexOf("=");
      if (index != -1 && index + 1 < hrefValue.length()) {
        valueAfterEquals = hrefValue.substring(index + 1);
        System.out.println("Значение после '=': " + valueAfterEquals);

      } else {
        System.out.println("Значение после '=' не найдено или отсутствует.");
      }
    } else {
      System.out.println("Ссылки с указанным href не найдены.");
    }
    driver.get("https://www.sharelane.com/cgi-bin/add_to_cart.py?book_id=" + valueAfterEquals);
    driver.get("https://www.sharelane.com/cgi-bin/shopping_cart.py");
    driver.findElement(By.name("q")).clear();
    driver.findElement(By.name("q")).sendKeys("500");
    driver.findElement(By.cssSelector("[value=Update]")).click();
    sleep(1000);

    String discountPercent = driver.findElement(
        By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
    String discount = driver.findElement(
        By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
    String total = driver.findElement(
        By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();

    softAssert.assertEquals(discount, "350.0");
    softAssert.assertEquals(discountPercent, "7");
    softAssert.assertEquals(total, "4650.0");

    softAssert.assertAll();
    driver.quit();
  }
}
