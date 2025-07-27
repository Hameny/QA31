import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SingUpTest {

  @Test
  public void checkPositiveRegistration() {
    WebDriver driver = new ChromeDriver();
    String zipcode = "12345";

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get("https://www.sharelane.com/cgi-bin/register.py");
    driver.findElement(By.name("zip_code")).sendKeys(zipcode);
    driver.findElement(By.cssSelector("[value=Continue]")).click();
    driver.findElement(By.name("first_name")).sendKeys("test");
    driver.findElement(By.name("last_name")).sendKeys("test");
    driver.findElement(By.name("email")).sendKeys("test@test.cv");
    driver.findElement(By.name("password1")).sendKeys("123456789");
    driver.findElement(By.name("password2")).sendKeys("123456789");
    driver.findElement(By.cssSelector("[value=Register]")).click();

    WebElement confirmation_message = driver.findElement(By.className("confirmation_message"));
    String confirmationMessage = confirmation_message.getText();

    Assert.assertEquals(confirmationMessage, "Account is created!");

    driver.quit();
  }
}
