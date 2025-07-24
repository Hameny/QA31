import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Sharelane {

  @Test
  public void testEnterInvalidValueIntoTheFieldZipCode() {
    WebDriver driver = new ChromeDriver();
    String zipcode = "1234";

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get("https://www.sharelane.com/cgi-bin/register.py");
    driver.findElement(By.name("zip_code")).sendKeys(zipcode);
    driver.findElement(By.cssSelector("[value=Continue]")).click();
    WebElement error_message = driver.findElement(By.cssSelector(".error_message"));
    String errorMessage = error_message.getText();

    Assert.assertEquals(errorMessage, "Oops, error on page. ZIP code should have 5 digits");

    driver.quit();
  }

  @Test
  public void testEnterValidValueIntoTheFieldZipCode() {
    WebDriver driver = new ChromeDriver();
    String zipcode = "12345";
    String expectedUrl = "https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=" + zipcode;

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get("https://www.sharelane.com/cgi-bin/register.py");
    driver.findElement(By.name("zip_code")).sendKeys(zipcode);
    driver.findElement(By.cssSelector("[value=Continue]")).click();
    WebElement firstName = driver.findElement(By.name("first_name"));
    WebElement lastName = driver.findElement(By.name("last_name"));
    WebElement email = driver.findElement(By.name("email"));
    WebElement password1 = driver.findElement(By.name("password1"));
    WebElement password2 = driver.findElement(By.name("password2"));
    String currentUrl = driver.getCurrentUrl();

    Assert.assertTrue(firstName.isDisplayed());
    Assert.assertTrue(lastName.isDisplayed());
    Assert.assertTrue(email.isDisplayed());
    Assert.assertTrue(password1.isDisplayed());
    Assert.assertTrue(password2.isDisplayed());
    Assert.assertTrue(expectedUrl.contains(currentUrl));

    driver.quit();
  }
}
