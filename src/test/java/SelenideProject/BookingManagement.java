package SelenideProject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.util.Set;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getAndCheckWebDriver;

public class BookingManagement {
    SelenideElement titlePage = $x("//head/title");
    SelenideElement manageBook = $x("//div[3]/a[@href=\"/services/booking-management\"]");
    SelenideElement scrollPage = $x("//footer");
    SelenideElement bookingNumber = $x("//input[@placeholder=\"Номер бронирования или билета\"]");
    SelenideElement familyClient = $x("//input[@placeholder=\"Фамилия клиента\"]");
    SelenideElement buttonSearch= $x("//button[@class=\"dp-12zmwrl-root-root\"]");

    @Step("Скролл страницы")
    public WebElement scrollPage() {
        return scrollPage.hover();
    }
    @Step("Получение заголовка страницы")
    public void clickBookingManagement() {
        manageBook.shouldBe(Condition.visible).click();
    }
    @Step("Получение заголовка страницы")
    public String getTitlePage() {
        titlePage.shouldBe(Condition.enabled, Duration.ofSeconds(3)).isDisplayed();
        return title();
    }
    @Step("Получение заголовка страницы")
    public String getFamilyClient() {
        return familyClient.getAttribute("placeholder");
    }
    @Step("Получение заголовка страницы")
    public String getBookingNumber() {
        return bookingNumber.getAttribute("placeholder");
    }
    @Step("Получение заголовка страницы")
    public String getButtonSearch() {
        return buttonSearch.getText();
    }
    @Step("Получение заголовка страницы")
    public void setFamilyClient(String strFamilyClient) {
        familyClient.setValue(strFamilyClient);
    }
    @Step("Получение заголовка страницы")
    public void setBookingNumber(String strBookingNumber) {
        bookingNumber.setValue(strBookingNumber);
    }
    @Step("Получение заголовка страницы")
    public void clickSearchBook() {
        buttonSearch.shouldBe(Condition.enabled).click();
    }
    @Step("Получение заголовка страницы")
    public void switchNewTab() {
        String currentTab = getAndCheckWebDriver().getWindowHandle();
        Set<String> handles = getAndCheckWebDriver().getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(currentTab)) {
                switchTo().window(handle);
                break;
            }
        }
    }

    /**
     * Метод для авторизации на сайте Pobeda
     * @param strFamilyClient
     * @param strBookingNumber
     * @return
     */
    @Step("Получение заголовка страницы")
    public void searchBookingManagement(String strFamilyClient, String strBookingNumber) {
        this.setFamilyClient(strFamilyClient);
        this.setBookingNumber(strBookingNumber);
        this.clickSearchBook();
    }
}
