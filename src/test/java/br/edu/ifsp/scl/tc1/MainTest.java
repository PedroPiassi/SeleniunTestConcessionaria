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
import java.util.ArrayList;
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

        @Test
        @DisplayName("Should open edit page of selected element in table")
        void ShouldOpenEditPageOfSelectedElementInTable() {
            driver.get(LIST_PAGE_URL);

            final String stringCarID = driver.findElement(By.xpath("//*[@id=\"root\"]/table/tbody/tr[1]/td[1]")).getText();
            final Integer carID = Integer.parseInt(stringCarID);
            final WebElement editButton = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/table/tbody/tr[1]/td[9]/button")));
            editButton.click();

            final String urlPage = driver.getCurrentUrl();
            final Integer lastCharOfUrlPage = Integer.parseInt(String.valueOf(urlPage.charAt(urlPage.length() - 1)));

            assertThat(lastCharOfUrlPage).isEqualTo(carID);
        }

        private final List<String> rowData = new ArrayList<>();
        @BeforeEach
        void beforeEach() {
            driver.get(LIST_PAGE_URL);

            final List<WebElement> rowElements = driver.findElement(By.xpath("//*[@id=\"root\"]/table/tbody/tr[2]")).findElements(By.tagName("td"));
            rowData.addAll(rowElements.stream().map(WebElement::getText).toList());

            final WebElement editButton = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/table/tbody/tr[2]/td[9]/button")));
            editButton.click();
        }
        @Nested @DisplayName("Test inputs os form")
        class FormInputs {
            @Test
            @DisplayName("Should verify that input name is correctly fill")
            void shouldVerifyThatInputNameIsCorrectlyFill() {
                final String name = new WebDriverWait(driver, Duration.ofSeconds(2))
                        .until(ExpectedConditions.elementToBeClickable(By.id("name"))).getAttribute("value");

                assertThat(name).isEqualTo(rowData.get(1));
            }

            @Test
            @DisplayName("Should verify that input year is correctly fill")
            void shouldVerifyThatInputYearIsCorrectlyFill() {
                final String year = new WebDriverWait(driver, Duration.ofSeconds(2))
                        .until(ExpectedConditions.elementToBeClickable(By.id("year"))).getAttribute("value");

                assertThat(year).isEqualTo(rowData.get(2));
            }
        }
        
        @Test
        @DisplayName("Should edit to 4 year, the warranty of car with ID equals 2")
        void shouldEditTo4YearTheWarrantyOfCarWithIdEquals2() {
            driver.get(LIST_PAGE_URL);

            final WebElement goToEditPage = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@id=\"root\"]/table/tbody/tr[2]/td[9]/button")
                    ));
            goToEditPage.click();

            final WebElement warrantyInput = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(By.id("warranty")));
            warrantyInput.clear();
            warrantyInput.sendKeys("4 anos");

            final WebElement editButton = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@id=\"root\"]/div/form/button")
                    ));
            editButton.click();

            final String warranty = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@id=\"root\"]/table/tbody/tr[2]/td[7]")
                    )).getText();

            assertThat(warranty).isEqualTo("4 anos");
        }

        @Test
        @DisplayName("Should remove car where ID is equals 1")
        void shouldRemoveCarWhereIdIsEquals1() {
            driver.get(LIST_PAGE_URL);

            final WebElement removeButton = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@id=\"root\"]/table/tbody/tr[1]/td[10]/a")
                    ));
            removeButton.click();

            final String idOfFirstTableRow = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@id=\"root\"]/table/tbody/tr[1]/td[1]")
                    )).getText();

            assertThat(idOfFirstTableRow).isNotEqualTo("1");
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

        @Test
        @DisplayName("Should find name input field in create form")
        void shouldFindNameInputFieldInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement input = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"name\"]")));

            assertThat(input.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should find year input field in create form")
        void shouldFindYearInputFieldInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement input = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"year\"]")));

            assertThat(input.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should find brand input field in create form")
        void shouldFindBrandInputFieldInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement input = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"brand\"]")));

            assertThat(input.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should find price input field in create form")
        void shouldFindPriceInputFieldInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement input = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"price\"]")));

            assertThat(input.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should find warranty input field in create form")
        void shouldFindWarrantyInputFieldInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement input = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"warranty\"]")));

            assertThat(input.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should find status input field in create form")
        void shouldFindStatusInputFieldInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement input = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"status\"]")));

            assertThat(input.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should find description input field in create form")
        void shouldFindDescriptionInputFieldInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement input = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"description\"]")));

            assertThat(input.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should alert if name is blank in create form")
        void shouldAlertIfNameIsBlankInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement input = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"name\"]")));
            input.sendKeys(" ");

            final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/button")));
            button.click();

            final WebElement alert = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/div[1]/div/div[1]/div[2]")));

            assertThat(alert.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should alert if name is null in create form")
        void shouldAlertIfNameIsNullInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/button")));
            button.click();

            final WebElement alert = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/div[1]/div/div[1]/div[2]")));

            assertThat(alert.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should alert if year is null in create form")
        void shouldAlertIfYearIsNullInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/button")));
            button.click();

            final WebElement alert = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/div[1]/div/div[2]/div[2]")));

            assertThat(alert.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should alert if year is higher than actual year in create form")
        void shouldAlertIfYearIsHigherInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement input = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"year\"]")));
            input.sendKeys("2050");

            final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/button")));
            button.click();

            final WebElement alert = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/div[1]/div/div[2]/div[2]")));

            assertThat(alert.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should alert if brand is null in create form")
        void shouldAlertIfBrandIsNullInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/button")));
            button.click();

            final WebElement alert = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/div[2]/div/div[1]/div[2]")));

            assertThat(alert.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should alert if brand is blank in create form")
        void shouldAlertIfBrandIsBlankInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement input = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"brand\"]")));
            input.sendKeys(" ");

            final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/button")));
            button.click();

            final WebElement alert = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/div[2]/div/div[1]/div[2]")));

            assertThat(alert.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should alert if price is null in create form")
        void shouldAlertIfPriceIsNullInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/button")));
            button.click();

            final WebElement alert = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/div[2]/div/div[2]/div[2]")));

            assertThat(alert.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should alert if warranty is blank in create form")
        void shouldAlertIfWarrantyIsBlankInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement input = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"warranty\"]")));
            input.sendKeys(" ");

            final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/button")));
            button.click();

            final WebElement alert = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/div[2]/div/div[3]/div[2]")));

            assertThat(alert.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should alert if warranty is null in create form")
        void shouldAlertIfWarrantyIsNullInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/button")));
            button.click();

            final WebElement alert = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/div[2]/div/div[3]/div[2]")));

            assertThat(alert.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should alert if status is blank in create form")
        void shouldAlertIfStatusIsBlankInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement input = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"status\"]")));
            input.sendKeys(" ");

            final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/button")));
            button.click();

            final WebElement alert = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/div[3]/div[2]")));

            assertThat(alert.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should alert if status is null in create form")
        void shouldAlertIfStatusIsNullInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/button")));
            button.click();

            final WebElement alert = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/div[3]/div[2]")));

            assertThat(alert.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should alert if description is blank in create form")
        void shouldAlertIfDescriptionIsBlankInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement input = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"description\"]")));
            input.sendKeys(" ");

            final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/button")));
            button.click();

            final WebElement alert = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/div[4]/div[2]")));

            assertThat(alert.isDisplayed()).isTrue();
        }

        @Test
        @DisplayName("Should alert if description is null in create form")
        void shouldAlertIfDescriptionIsNullInCreateForm() {
            driver.get(CREATE_PAGE_URL);

            final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/button")));
            button.click();

            final WebElement alert = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/form/div[4]/div[2]")));

            assertThat(alert.isDisplayed()).isTrue();
        }
    }
}
/*
[{"name":"Fiesta","year":2015,"brand":"Chevrolet","price":13000,"warranty":"5 anos","status":"Antigo","description":"Antigo, porÃ©m bem cuidado.","id":1},{"message":"Not Found, id=NaN","name":"Fiat","year":2011,"brand":"Chevrolet","price":10000,"warranty":"4 anos","status":"Antigo","description":"Antigo, porÃ©m bem cuidado e com bom chaci","id":2}]
*/