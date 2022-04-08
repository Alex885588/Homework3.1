import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
//import org.testng.annotations.Test;


public class Homework {
    public static WebDriver webDriver;

    @BeforeClass
    public static void initWebDriver() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://time.am");
    }

    @AfterClass
    public static void afterAll() {
        webDriver.quit();
    }

    @Test
    public void didAccessoriesOpen() {
        webDriver.get("https://time.am");
        webDriver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/ul/li[5]/a")).click();
        Assert.assertEquals("'accessories' did not open", webDriver.getCurrentUrl(), "https://time.am/catalog/accessories/");
    }

    @Test
    public void didSeacrhOpen() {
        webDriver.get("https://time.am");
        webDriver.findElement(By.cssSelector("#title-search-input")).sendKeys("Casio", Keys.ENTER);
        Assert.assertEquals("Searching for an item did not work.", webDriver.findElement(By.cssSelector("#page-content > div > div.catalog-wrap > section > div > div.products__title")).getText(), "Որոնման արդյունքները “Casio”");
    }
    @Test
    public void didLanguageLogoOpen() throws InterruptedException {
        webDriver.get("https://time.am");
        webDriver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div[1]/div/a")).click();
        Thread.sleep(1000);
        Assert.assertEquals(webDriver.findElement(By.cssSelector("#language > div > div > div.modal-title")).getText(),"Ընտրեք Լեզուն և Տարածաշրջանը");
    }
    @Test
    public void isOrderAddedInBox() throws InterruptedException {
        webDriver.get("https://time.am");
        webDriver.findElement(By.cssSelector("#title-search-input")).sendKeys("Casio", Keys.ENTER);
        Thread.sleep(1000);
        webDriver.findElement(By.className("products__item-image-first")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"page-content\"]/div[2]/div/div[2]/section/div[2]/div/div[6]/a")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/ul/li[4]/a")).click();
        Thread.sleep(1000);
        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/ul/li[4]/div[2]/div[2]/div/span[1]")).getText(),"1");
    }
    @Test
    public void isPageChange() throws InterruptedException{
        webDriver.get("https://time.am");
        webDriver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/ul/li[3]/a")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"page-content\"]/div/div[3]/section/div/div[3]/div[2]/div/div[1]/a[2]")).click();
        Assert.assertEquals( webDriver.findElement(By.xpath("//*[@id=\"page-content\"]/div/div[3]/section/div/div[3]/div[2]/div/div[1]/a[2]")).getText(),"2" );
    }
}
