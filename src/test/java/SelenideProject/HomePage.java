package SelenideProject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.util.List;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    SelenideElement logo = $x( "//header/div[2]/div/a[1]/img");
    SelenideElement information = $x("//a[text()='Информация']");
    SelenideElement downmenu = $x( "//div[contains(@class, 'dp-ykckcr-root')]");
     List  <SelenideElement> itemsInformation = $$x( "//div[contains(@class, 'dp-ykckcr-root')]//a");

        @Step("Получение заголовка страницы")
        public String getTitlePage() {
            return title();
        }
        @Step("Проверка, что логотип компании отображается")
        public boolean getLogo() {
            return logo.shouldBe(Condition.visible).isDisplayed();
        }

        @Step("Проверка наведения курсора на меню 'Информация'")
        public void hoverInformation() {
        information.hover();
         }
        @Step("Проверка отображения нижнего меню")
        public boolean downMenuIsDisplayed() {
            return downmenu.shouldBe(Condition.visible).isDisplayed();
        }

        @Step("Проверка пунктов в нижнем меню 'Информация'")
        public boolean verifyItemsInformation() {
            String [] expectedItems = {"Подготовка к полёту", "Полезная информация", "О компании"};

            for (String items : expectedItems) {
                boolean found = itemsInformation.stream().anyMatch(e -> e.getText().equals(items));
                    if (!found) {
                        return false;
                    }
                }
                return true;
            }
    }

