package edu.pitt.cs;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import src.D3Test;
import src.D3BuggierTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class D3Test {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void tEST1LINKS() {
    driver.get("https://cs1632.appspot.com/");
    js.executeScript("document.cookie = \"1=false\";document.cookie = \"2=false\";document.cookie = \"3=false\";");
    {
      List<WebElement> elements = driver.findElements(By.xpath("//a[text()=\'Reset\']"));
      assert(elements.size() > 0);
    }
    System.out.println("exists");
  }
  @Test
  public void dEFECT1FEEDACAT() {
    driver.get("https://cs1632.appspot.com/");
    js.executeScript("document.cookie = \"1=false\";document.cookie = \"2=false\";document.cookie = \"3=false\";");
    driver.findElement(By.xpath("//a[text()=\'Feed-A-Cat\']")).click();
    {
      WebDriverWait wait = new WebDriverWait(driver, 10);
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input")));
    }
    driver.findElement(By.xpath("//input")).sendKeys("-3");
    driver.findElement(By.xpath("//button[text()=\'Feed\']")).click();
    {
      WebDriverWait wait = new WebDriverWait(driver, 30);
      wait.until(ExpectedConditions.presenceOfElementLocated(By.id("feedResult")));
    }
    assertThat(driver.findElement(By.id("feedResult")).getText(), is("Cat fight!"));
  }
  @Test
  public void dEFECT2GREETACAT() {
    driver.get("https://cs1632.appspot.com/");
    js.executeScript("document.cookie = \"1=true\";document.cookie = \"2=false\";document.cookie = \"3=false\";");
    driver.findElement(By.linkText("Greet-A-Cat")).click();
    assertThat(driver.findElement(By.xpath("//*[@id=\"greeting\"]/h4")).getText(), is("Meow!Meow!"));
  }
  @Test
  public void dEFECT3FEEDACAT() {
    driver.get("https://cs1632.appspot.com/");
    js.executeScript("document.cookie = \"1=false\";document.cookie = \"2=false\";document.cookie = \"3=false\";");
    driver.findElement(By.xpath("//a[text()=\'Feed-A-Cat\']")).click();
    {
      WebDriverWait wait = new WebDriverWait(driver, 10);
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input")));
    }
    driver.findElement(By.xpath("//input")).sendKeys("0");
    driver.findElement(By.xpath("//button[@onclick=\'setTimeout(feedSubmit, 1000)\']")).click();
    {
      WebDriverWait wait = new WebDriverWait(driver, 30);
      wait.until(ExpectedConditions.presenceOfElementLocated(By.id("feedResult")));
    }
    assertThat(driver.findElement(By.id("feedResult")).getText(), is("Cat fight!"));
  }
  @Test
  public void tEST2LOGOEXISTS() {
    driver.get("https://cs1632.appspot.com/");
    js.executeScript("document.cookie = \"1=true\";document.cookie = \"2=true\";document.cookie = \"3=true\";");
    driver.findElement(By.linkText("Reset")).click();
    assertThat(driver.findElement(By.xpath("//*[@id=\"cat-id1\"]")).getText(), is("ID 1. Jennyanydots"));
    assertThat(driver.findElement(By.xpath("//*[@id=\"cat-id2\"]")).getText(), is("ID 2. Old Deuteronomy"));
    assertThat(driver.findElement(By.xpath("//*[@id=\"cat-id3\"]")).getText(), is("ID 3. Mistoffelees"));
  }
  @Test
  public void tEST3CATALOG() {
    driver.get("https://cs1632.appspot.com/");
    js.executeScript("document.cookie = \"1=false\";document.cookie = \"2=false\";document.cookie = \"3=false\";");
    driver.findElement(By.linkText("Catalog")).click();
    {
      WebElement element = driver.findElement(By.xpath("(//img)[2]"));
      String attribute = element.getAttribute("src");
      vars.put("secondImageSrc", attribute);
    }
    System.out.println(vars.get("secondImageSrc").toString());
    assertEquals(vars.get("secondImageSrc").toString(), "/images/cat2.jpg");
  }
  @Test
  public void tEST4LISTING() {
    driver.get("https://cs1632.appspot.com/");
    js.executeScript("document.cookie = \"1=false\";document.cookie = \"2=false\";document.cookie = \"3=false\";");
    driver.findElement(By.linkText("Catalog")).click();
    {
      List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"cat-id3\"]"));
      assert(elements.size() > 0);
    }
    assertThat(driver.findElement(By.xpath("//*[@id=\"cat-id3\"]")).getText(), is("ID 3. Mistoffelees"));
  }
  @Test
  public void tEST5RENTACAT() {
    driver.get("https://cs1632.appspot.com/");
    js.executeScript("document.cookie = \"1=false\";document.cookie = \"2=false\";document.cookie = \"3=false\";");
    driver.findElement(By.linkText("Rent-A-Cat")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".form-group:nth-child(3) .btn"));
      assert(elements.size() > 0);
    }
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".form-group:nth-child(4) .btn"));
      assert(elements.size() > 0);
    }
  }
  @Test
  public void tEST6RENT() {
    driver.get("https://cs1632.appspot.com/");
    js.executeScript("document.cookie = \"1=false\";document.cookie = \"2=false\";document.cookie = \"3=false\";");
    driver.findElement(By.linkText("Rent-A-Cat")).click();
    driver.findElement(By.id("rentID")).click();
    driver.findElement(By.id("rentID")).sendKeys("1");
    driver.findElement(By.cssSelector(".form-group:nth-child(3) .btn")).click();
    assertThat(driver.findElement(By.xpath("//*[@id=\"cat-id1\"]")).getText(), is("Rented out"));
    assertThat(driver.findElement(By.xpath("//*[@id=\"cat-id2\"]")).getText(), is("ID 2. Old Deuteronomy"));
    assertThat(driver.findElement(By.xpath("//*[@id=\"cat-id3\"]")).getText(), is("ID 3. Mistoffelees"));
    assertThat(driver.findElement(By.xpath("//*[@id=\"rentResult\"]")).getText(), is("Success!"));
  }
  @Test
  public void tEST7RETURN() {
    driver.get("https://cs1632.appspot.com/");
    js.executeScript("document.cookie = \"1=false\";document.cookie = \"2=true\";document.cookie = \"3=false\";");
    driver.findElement(By.linkText("Rent-A-Cat")).click();
    driver.findElement(By.id("returnID")).click();
    driver.findElement(By.id("returnID")).sendKeys("2");
    driver.findElement(By.cssSelector(".form-group:nth-child(4) .btn")).click();
    assertThat(driver.findElement(By.xpath("//*[@id=\"cat-id1\"]")).getText(), is("ID 1. Jennyanydots"));
    assertThat(driver.findElement(By.xpath("//*[@id=\"cat-id2\"]")).getText(), is("ID 2. Old Deuteronomy"));
    assertThat(driver.findElement(By.xpath("//*[@id=\"cat-id3\"]")).getText(), is("ID 3. Mistoffelees"));
    assertThat(driver.findElement(By.xpath("//*[@id=\"returnResult\"]")).getText(), is("Success!"));
  }
  @Test
  public void tEST8FEEDACAT() {
    driver.get("https://cs1632.appspot.com/");
    js.executeScript("document.cookie = \"1=false\";document.cookie = \"2=false\";document.cookie = \"3=false\";");
    driver.findElement(By.linkText("Feed-A-Cat")).click();
    {
      List<WebElement> elements = driver.findElements(By.xpath("//button[contains(.,\'Feed\')]"));
      assert(elements.size() > 0);
    }
  }
  @Test
  public void tEST9FEED() {
    driver.get("https://cs1632.appspot.com/");
    js.executeScript("document.cookie = \"1=false\";document.cookie = \"2=false\";document.cookie = \"3=false\";");
    driver.findElement(By.linkText("Feed-A-Cat")).click();
    driver.findElement(By.id("catnips")).click();
    driver.findElement(By.id("catnips")).sendKeys("6");
    driver.findElement(By.xpath("//button[@onclick=\'setTimeout(feedSubmit, 1000)\']")).click();
    {
      WebDriverWait wait = new WebDriverWait(driver, 30);
      wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id=\"feedResult\"]"), "Nom, nom, nom."));
    }
  }
  @Test
  public void tEST10GREETACAT() {
    driver.get("https://cs1632.appspot.com/");
    js.executeScript("document.cookie = \"1=false\";document.cookie = \"2=false\";document.cookie = \"3=false\";");
    driver.findElement(By.linkText("Greet-A-Cat")).click();
    assertThat(driver.findElement(By.xpath("//*[@id=\"greeting\"]/h4")).getText(), is("Meow!Meow!Meow!"));
  }
  @Test
  public void tEST11GREETACATWITHNAME() {
    driver.get("https://cs1632.appspot.com/");
    js.executeScript("document.cookie = \"1=false\";document.cookie = \"2=false\";document.cookie = \"3=false\";");
    driver.get("https://cs1632.appspot.com/greet-a-cat/Jennyanydots");
    assertThat(driver.findElement(By.xpath("//*[@id=\"greeting\"]/h4")).getText(), is("Meow! from Jennyanydots."));
  }
}
