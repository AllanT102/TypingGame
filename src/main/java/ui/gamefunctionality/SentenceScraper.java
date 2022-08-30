package ui.gamefunctionality;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class SentenceScraper {
    private final String url = "https://www.thewordfinder.com/random-sentence-generator/";
    private final ChromeDriver driver;
    private String paragraph;

    public SentenceScraper() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\allan\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    public List<WebElement> scrape() {
        driver.get("https://www.thewordfinder.com/random-sentence-generator/");
        WebElement numInput = driver.findElement(By.id("sentence_count"));
        WebElement generateBtn = driver.findElement(By.className("btn-lg"));
        numInput.sendKeys(Keys.RIGHT, Keys.BACK_SPACE);
        numInput.sendKeys("9");
        generateBtn.click();
        List<WebElement> sentences = driver.findElements(By.cssSelector("a[href='#']"));
        List<WebElement> filtered = filter(sentences);
        return filtered;
    }

    public List<WebElement> filter(List<WebElement> sentences) {
        List<WebElement> filteredSentences = new ArrayList<>();
        sentences.forEach(webElement -> {
            if (webElement.getAttribute("onclick").equals("bookmarkSentence(this);return(false);")) {
                filteredSentences.add(webElement);
            }
        });
        filteredSentences.forEach(webElement -> System.out.println(webElement.getText()));
        return filteredSentences;
    }

    public List<String> convertToStringList(List<WebElement> elements) {
        List<String> sentences = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        elements.forEach(element -> sentences.add(element.getText()));
        sentences.forEach(sentence -> sb.append(sentence));
        this.paragraph = sb.toString();
        return sentences;
    }

    public String getParagraph() {
        return paragraph;
    }
}
