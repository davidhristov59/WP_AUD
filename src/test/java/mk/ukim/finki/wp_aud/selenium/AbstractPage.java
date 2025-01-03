package mk.ukim.finki.wp_aud.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

public class AbstractPage {

    protected static WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    static void get(WebDriver driver, String relativeUrl) {
        String url = System.getProperty("geb.build.baseUrl", "http://localhost:9999") + relativeUrl;
        driver.get(url);
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
