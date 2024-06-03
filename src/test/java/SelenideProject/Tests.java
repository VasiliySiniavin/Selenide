package SelenideProject;

import com.codeborne.selenide.Configuration;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import io.qameta.allure.*;

@Epic("Тестирование сайта Победа")
public class Tests {

    HomePage homePage;
    BlockSearchTicket blockSearchTicket;
    BookingManagement bookingManagement;
    ViewOrder viewOrder;

    @BeforeEach
    public void setUp() {
        //Selenide.open(Configuration.baseUrl);
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://www.pobeda.aero";
        Configuration.timeout = 20000;
        open(Configuration.baseUrl);
    }

    @Feature("Заголовок, логотип и блоки в разделе 'Информация'")
    @Test
    @Description("Позитивный. Проверка заголовка страницы, логотипа и наличия разделов" +
            " Подготовка к полёту, Полезная информация, О компании")
    public void testCheckTitilePage() {
        //Создаем экземпляр объекта страницы логина и страницы профиля
        homePage = new HomePage();
        //Проверяем заголовок страницы
        String actualResult = homePage.getTitlePage();
        String expectedResult = "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками";
        Assertions.assertEquals(expectedResult, actualResult);
        //Проверяем наличие логотипа Победы
        //  SelenideElement waiterLogo = homePage.logo.shouldBe(Condition.text("Победа"), Duration.ofSeconds(8));
        Assertions.assertTrue(homePage.getLogo());
        //Проверяем, что всплывающее окно появилось
        homePage.hoverInformation();
        Assertions.assertTrue(homePage.downMenuIsDisplayed());
        Assertions.assertTrue(homePage.verifyItemsInformation());
    }

    @Feature("Красная обводка при незаполнении даты вылета")
    @Test
    @Description("Позитивный. Проверка, что вокруг поля «Туда» появилась красная обводка")
    public void testBlockSearchTicket() {
//Создаем экземпляр объекта страницы логина и страницы профиля
        homePage = new HomePage();
        blockSearchTicket = new BlockSearchTicket();
        //Проверяем заголовок страницы
        String actualResult = homePage.getTitlePage();
        String expectedResult = "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками";
        Assertions.assertEquals(expectedResult, actualResult);
        //Проверяем наличие логотипа Победы
        Assertions.assertTrue(homePage.getLogo());
        //Прокручиваем страницу
        blockSearchTicket.scrollPage();
        //Проверяем наличие полей
        Assertions.assertTrue(blockSearchTicket.getFieldFrom());
        Assertions.assertTrue(blockSearchTicket.getFieldTo());
        Assertions.assertTrue(blockSearchTicket.getFieldDateThere());
        Assertions.assertTrue(blockSearchTicket.getFieldDateBack());
        //Вводим данные в поле откуда и куда
        blockSearchTicket.setFieldFrom("Москва");
        blockSearchTicket.setFieldTo("Санкт-Петербург");
        //Нажимаем поиск
        blockSearchTicket.clickSearch();
        //Проверяем, что вокруг поля «Туда» появилась красная обводка
        Assertions.assertTrue(blockSearchTicket.checkFieldRed());
    }

    @Feature("Несуществующий номер заказа")
    @Test
    @Description("Позитивный. Проверка поиска несуществующего номера заказа")
    public void testBookingTickets()  {
        //Создаем экземпляр объекта страницы логина и страницы управления бронированием
        homePage = new HomePage();
        bookingManagement= new BookingManagement();
        viewOrder = new ViewOrder();
        //Проверяем заголовок страницы
        //String actualResult = homePage.getTitlePage();
        String expectedResult = "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками";
        Assertions.assertEquals(expectedResult, homePage.getTitlePage());
        //Проверяем наличие логотипа Победы
        Assertions.assertTrue(homePage.getLogo());
        //Прокручиваем страницу и кликаем на пункт «Управление бронированием».
        bookingManagement.scrollPage();
        bookingManagement.clickBookingManagement();
        //Проверяем, что открылась необходимая страница
        Assertions.assertEquals("Управление бронированием, скачать маршрутную квитанцию, билет", bookingManagement.getTitlePage());
        //Проверяем, что есть поля: Фамилия клиента
        Assertions.assertEquals("Фамилия клиента", bookingManagement.getFamilyClient());
        Assertions.assertEquals("Номер бронирования или билета", bookingManagement.getBookingNumber());
        Assertions.assertEquals("ПОИСК", bookingManagement.getButtonSearch());
        //Вводим данные в поле Фамилия клиента и Номер бронирования или билета
        bookingManagement.searchBookingManagement("Qwerty", "XXXXXX");
        //Проверяем, что открылась новая вкладка - Просмотр заказов
        bookingManagement.switchNewTab();
        viewOrder.iFameFind();
        Assertions.assertEquals("Заказ с указанными параметрами не найден", viewOrder.getErrorMessage());
    }

    @Feature("Заголовок в разделе главной страницы")
    @Test
    @Description("Негативный. Проверка заголовка главной страницы")
    public void testCheckTitilePageNegative() {
        //Создаем экземпляр объекта страницы логина и страницы профиля
        homePage = new HomePage();
        //Проверяем заголовок страницы
        String actualResult = homePage.getTitlePage();
        String expectedResult = "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы";
        Assertions.assertEquals(expectedResult, homePage.getTitlePage());
    }
}