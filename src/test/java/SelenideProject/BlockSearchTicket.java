package SelenideProject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;
import static com.codeborne.selenide.Selenide.$x;

public class BlockSearchTicket {
    SelenideElement scrollPage = $x("//div/div[2]/div[3]/form");
    SelenideElement  fieldFrom = $x("//input[@placeholder=\"Откуда\"]");
    SelenideElement fieldTo = $x( "//input[@placeholder=\"Куда\"]");
    SelenideElement fieldDateThere = $x("//input[@placeholder=\"Туда\"]");
    SelenideElement fieldDateBack = $x( "//input[@placeholder=\"Обратно\"]");
    SelenideElement buttonSearch = $x("//button[@class=\"dp-yuxlxj-root-root\"]");
    SelenideElement fieldRedFrame = $x("//div[3]/form/div/div[1]/div/div[2]/div[2]/div/div[1]/div");

    @Step("Скролл страницы")
    public void scrollPage() {
        scrollPage.hover();
    }
    @Step("Проверка, что поле Откуда отображается")
    public boolean getFieldFrom() {
        return fieldFrom.shouldBe(Condition.visible, Duration.ofSeconds(3)).isDisplayed();
    }
    @Step("Проверка, что поле Куда отображается")
    public boolean getFieldTo() {
        return fieldTo.shouldBe(Condition.visible, Duration.ofSeconds(3)).isDisplayed();
    }
    @Step("Проверка, что поле Туда отображается")
    public boolean getFieldDateThere() {
        return fieldDateThere.shouldBe(Condition.visible, Duration.ofSeconds(3)).isDisplayed();
    }
    @Step("Проверка, что поле Обратно отображается")
    public boolean getFieldDateBack() {
        return fieldDateBack.shouldBe(Condition.visible, Duration.ofSeconds(3)).isDisplayed();
    }
    @Step("Установка в поле Откуда значения и нажатие на Enter")
    public void setFieldFrom(String strFieldFrom) {
        fieldFrom.setValue(strFieldFrom).pressEnter();
    }
    @Step("Установка в поле Откуда значения и нажатие на Enter")
    public void setFieldTo(String strFieldTo) {
        fieldTo.setValue(strFieldTo).pressEnter();
    }
    @Step("Клик на кнопку Поиск")
    public void clickSearch() {
        buttonSearch.shouldBe(Condition.enabled).click();
    }
    @Step("Проверка, что поле обведено красной рамкой")
    public boolean checkFieldRed() {
        String borderColor = fieldRedFrame.getCssValue("border-color");
        return borderColor.equals("rgb(213, 0, 98)") || borderColor.equals("#d50062");
    }
}

