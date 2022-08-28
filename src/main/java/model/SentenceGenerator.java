package model;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class SentenceGenerator {

    public void getRandomSentences() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\allan\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.thewordfinder.com/random-sentence-generator/");

        WebElement numInput = driver.findElement(By.id("sentence_count"));
        WebElement generateBtn = driver.findElement(By.className("btn-lg"));
        numInput.sendKeys(Keys.RIGHT, Keys.BACK_SPACE);
        numInput.sendKeys("9");
        generateBtn.click();
        List<WebElement> sentences = driver.findElements(By.cssSelector("a[href='#']"));
        List<WebElement> filteredSentences = new ArrayList<>();

        sentences.forEach(webElement -> {
            if (webElement.getAttribute("onclick").equals("bookmarkSentence(this);return(false);")) {
                filteredSentences.add(webElement);
            }
        });
        filteredSentences.forEach(webElement -> System.out.println(webElement.getText()));
        driver.quit();
    }

}
