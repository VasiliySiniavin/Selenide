package SelenideProject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import static com.codeborne.selenide.Selenide.*;

public class ViewOrder {

    SelenideElement bookingNumber = $x("//input[@placeholder=\"Номер брони или билета\"]");
    SelenideElement familyClient = $x("//input[@placeholder=\"Фамилия\"]");
    SelenideElement buttonSearch = $x("//form/div[1]/div/div/button");
    SelenideElement errorMessage = $x("/html/body/div[1]/section/div[1]/div/div/div[2]");

    @Step("Поиск поля 'Фамилия'")
    public String getFamilyClient() {
        return familyClient.getAttribute("placeholder");
    }
    @Step("Получение заголовка страницы")
    public String getTitlePage() {
        return title();
    }
    @Step("Поиск поля 'Номер брони или билета'")
    public String getBookingNumber() {
        return bookingNumber.getAttribute("placeholder");
    }
    @Step("Поиск кнопки 'Поиск'")
    public String getButtonSearch() {
        return buttonSearch.getAttribute("button");
    }
    @Step("Получение текста ошибки")
    public String getErrorMessage() {
       return errorMessage.getText();
    }
    @Step("Поиск iframe")
    public void  iFameFind() {
    try {
    SelenideElement iframe = $x("//iframe[@data-testid='checkbox-iframe']");
           if (iframe.shouldBe(Condition.visible).isDisplayed()) {
               //exists()
               iframe.click();

           }
       } catch (NoSuchElementException ex) {
            System.out.println("Iframe не найден, тест продолжает выполняться");
        }
    }
}
