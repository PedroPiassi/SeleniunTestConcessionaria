package br.edu.ifsp.scl.tc1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.assertj.core.api.Assertions.*;

import java.time.Duration;
import java.util.List;

class MainTest {
    private WebDriver driver;

    final String HOME_PAGE_URL = "http://localhost:5173/";
    final String LIST_PAGE_URL = "http://localhost:5173/list";
    final String CREATE_PAGE_URL = "http://localhost:5173/create";

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
        driver.get(HOME_PAGE_URL);
        Thread.sleep(Duration.ofSeconds(2));
    }

    @Test
    @DisplayName("Should open create page when nav create link clicked")
    void shouldOpenCreatePageWhenNavCreateLinkClicked(){
        driver.get(HOME_PAGE_URL);
        final WebElement navLink = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbarNav\"]/ul/li[1]/a")));
        navLink.click();

        assertThatNoException().isThrownBy(() -> driver.findElement(By.xpath("//*[@id=\"root\"]/div/form")));
    }

    @Test
    @DisplayName("Should open list page when nav list link clicked")
    void shouldOpenListPageWhenNavListLinkClicked() {
        driver.get(HOME_PAGE_URL);

        final WebElement navLink = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbarNav\"]/ul/li[2]/a")));
        navLink.click();

        assertThatNoException().isThrownBy(() -> driver.findElement(By.xpath("//*[@id=\"root\"]/table")));
    }

    @Test
    @DisplayName("Should open create page when image create link clicked")
    void ShouldOpenCreatePageWhenImageCreateLinkClicked() {
        driver.get(HOME_PAGE_URL);

        final WebElement imageLink = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/a")));
        imageLink.click();

        assertThatNoException().isThrownBy(() -> driver.findElement(By.xpath("//*[@id=\"root\"]/div/form")));
    }

    @Test
    @DisplayName("Should open ^list page when image list link clicked")
    void ShouldOpenListPageWhenImageListCLinkClicked() {
        driver.get(HOME_PAGE_URL);

        final WebElement imageLink = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/a")));
        imageLink.click();

        assertThatNoException().isThrownBy(() -> driver.findElement(By.xpath("//*[@id=\"root\"]/table")));
    }

    @Test
    @DisplayName("Should open instagram page when instagram icon clicked")
    void ShouldOpenInstagramPageWhenInstagramIconClicked() {
        driver.get(HOME_PAGE_URL);

        final WebElement iconInstagram = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/footer/div/div[2]/div[1]/a")));
        iconInstagram.click();

        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl).isEqualTo("https://www.instagram.com/center_cars_web/");
    }

    @Test
    @DisplayName("Should open linkedin page when linkedin icon clicked")
    void ShouldOpenLinkedinPageWhenLinkedinIconClicked() {
        driver.get(HOME_PAGE_URL);

        final WebElement iconLinkedin = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/footer/div/div[2]/div[2]/a")));
        iconLinkedin.click();

        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl).isEqualTo("https://www.linkedin.com/in/hdk101/");
    }

    @Test
    @DisplayName("Should open twitter page when twitter icon clicked")
    void ShouldOpenTwitterPageWhenTwitterIconClicked() {
        driver.get(HOME_PAGE_URL);

        final WebElement iconLinkedin = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/footer/div/div[2]/div[3]/a")));
        iconLinkedin.click();

        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl).isEqualTo("https://twitter.com/elonmusk");
    }

    @Test
    @DisplayName("Should open home page when car icon clicked")
    void ShouldOpenHomePageWhenCarIconClicked() {
        driver.get(LIST_PAGE_URL);

        final WebElement iconCar = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/nav/div/a")));
        iconCar.click();

        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl).isEqualTo(HOME_PAGE_URL);
    }

    @Test
    @DisplayName("Should open create page when create button clicked")
    void ShouldOpenCreatePageWhenCreateButtonClicked() {
        driver.get(LIST_PAGE_URL);

        final WebElement createButton = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/button")));
        createButton.click();

        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl).isEqualTo(CREATE_PAGE_URL);
    }

    @Nested @DisplayName("In list page")
    class TestOfListPage {
        @Test
        @DisplayName("Should open create page when nav create link clicked by list page")
        void shouldOpenCreatePageWhenNavCreateLinkClickedByListPage(){
            driver.get(LIST_PAGE_URL);
            final WebElement navLink = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbarNav\"]/ul/li[1]/a")));
            navLink.click();

            String urlPage = driver.getCurrentUrl();
            assertThat(urlPage).isEqualTo(CREATE_PAGE_URL);
        }

        @Test
        @DisplayName("Should keeps in list page when nav list link clicked by list page")
        void shouldKeepsInListPageWhenNavListLinkClickedByListPage() {
            driver.get(LIST_PAGE_URL);
            final WebElement navLink = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbarNav\"]/ul/li[2]/a")));
            navLink.click();

            String urlPage = driver.getCurrentUrl();
            assertThat(urlPage).isEqualTo(LIST_PAGE_URL);
        }

        @Test
        @DisplayName("Should open home page when nav home link clicked by list page")
        void shouldOpenHomePageWhenNavHomeLinkClickedByListPage() {
            driver.get(LIST_PAGE_URL);
            final WebElement navLink = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/nav/div/a")));
            navLink.click();

            String urlPage = driver.getCurrentUrl();
            assertThat(urlPage).isEqualTo(HOME_PAGE_URL);
        }
    }

    @Nested @DisplayName("In create page")
    class TestOfCreatePage {
        @Test
        @DisplayName("Should open list page when nav list link clicked by create page")
        void shouldOpenListPageWhenNavListLinkClickedByListPage() {
            driver.get(CREATE_PAGE_URL);
            final WebElement navLink = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbarNav\"]/ul/li[2]/a")));
            navLink.click();

            String urlPage = driver.getCurrentUrl();
            assertThat(urlPage).isEqualTo(LIST_PAGE_URL);
        }

        @Test
        @DisplayName("Should keep in create page when nav create link clicked by create page")
        void shouldKeepInCreatePageWhenNavCreateLinkClickedByCreatePage() {
            driver.get(CREATE_PAGE_URL);
            final WebElement navLink = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbarNav\"]/ul/li[1]/a")));
            navLink.click();

            String urlPage = driver.getCurrentUrl();
            assertThat(urlPage).isEqualTo(CREATE_PAGE_URL);
        }

        @Test
        @DisplayName("Should open home page when nav home link clicked by create page")
        void shouldOpenHomePageWhenNavHomeLinkClickedByHomePage() {
            driver.get(CREATE_PAGE_URL);
            final WebElement navLink = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/nav/div/a")));
            navLink.click();

            String urlPage = driver.getCurrentUrl();
            assertThat(urlPage).isEqualTo(HOME_PAGE_URL);
        }

        @Test
        @DisplayName("Should find create form")
        void shouldFindCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement form = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form")));

            assertThat(form.isDisplayed()).isTrue();
        }
    }
}