import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;

public class SeleniumRunner {

    public static void main(String[] args) {
        runGecko(args);
        runChrome(args);
    }

    private static void runGecko(String[] args) {
        System.setProperty("webdriver.gecko.driver",
                args.length > 0
                        ? args[0]
                        : "F:\\work\\selenium drivers\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions()
                .addArguments("--headless");
        WebDriver driver = new FirefoxDriver(options);
        checkGoogle(driver);
    }

    private static void runChrome(String[] args) {
        System.setProperty("webdriver.chrome.driver",
                args.length > 0
                        ? args[0]
                        : "F:\\work\\selenium drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions()
                .addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        checkGoogle(driver);
    }

    private static void checkGoogle(WebDriver driver) {
        driver.get("https://www.google.com");
        List<WebElement> elements = driver.findElements(By.tagName("input"));
        System.out.println(elements.size());
        elements.stream()
                .filter(element -> element.getAttribute("type").equals("text"))
                .forEach(element -> System.out.println(element.getAttribute("outerHTML")));
        driver.close();
    }
}
