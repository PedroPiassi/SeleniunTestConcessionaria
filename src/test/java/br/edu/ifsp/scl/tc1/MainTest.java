package br.edu.ifsp.scl.tc1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.assertj.core.api.Assertions.*;

import java.time.Duration;

class MainTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Should open Concessionaria page")
    void shouldOpenConcessionariaPage() throws InterruptedException {
        driver.get("http://localhost:5173");
        Thread.sleep(Duration.ofSeconds(2));
    }

    @Test
    @DisplayName("Should open create page when nav create link clicked")
    void shouldOpenCreatePageWhenNavCreateLinkClicked(){
        driver.get("http://localhost:5173");
        final WebElement navLink = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbarNav\"]/ul/li[1]/a")));
        navLink.click();

        assertThatNoException().isThrownBy(() -> driver.findElement(By.xpath("//*[@id=\"root\"]/div/form")));
    }

    @Test
    @DisplayName("Should open list page when nav list link clicked")
    void shouldOpenListPageWhenNavListLinkClicked() {
        driver.get("http://localhost:5173");

        final WebElement navLink = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbarNav\"]/ul/li[2]/a")));
        navLink.click();

        assertThatNoException().isThrownBy(() -> driver.findElement(By.xpath("//*[@id=\"root\"]/table")));
    }
}