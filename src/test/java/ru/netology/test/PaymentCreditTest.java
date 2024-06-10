package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;
import ru.netology.page.CreditPage;
import ru.netology.page.MainPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PaymentCreditTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @BeforeEach
    public void setUp() {
        String url = System.getProperty("sut.url");
        open(url);
    }
    @AfterEach
    public void deleteDB() {
        SqlHelper.deleteDataBase();
    }
    @Test
    void shouldSuccessfulPurchaseByCredit() {         // Покупка тура в кредит со статусом "APPROVED"
        var mainPage = new MainPage();
        mainPage.openCreditPage();
        var payment = new CreditPage();
        payment.completedForm(DataHelper.getApprovedCard());
        payment.expectationOperationApproved();
        assertEquals("APPROVED", SqlHelper.getCreditPaymentStatus());
    }
    @Test
    void shouldPurchaseByCardDeclined() {            // Покупка тура в кредит со статусом "DECLINED"
        var mainPage = new MainPage();
        mainPage.openCreditPage();
        var payment = new CreditPage();
        payment.completedForm(DataHelper.getDeclinedCard());
        payment.expectationError();
        assertEquals("DECLINED", SqlHelper.getCreditPaymentStatus());
    }
    @Test
    void shouldByPaymentEmptyForm() {              // Пустая форма
        var mainPage = new MainPage();
        mainPage.openCreditPage();
        var payment = new CreditPage();
        payment.completedForm(DataHelper.getEmptyForm());
        payment.expectationInvalidFormat();
        assertEquals("Неверный формат", payment.getInvalidText());
    }
    @Test
    void shouldByPaymentLastMonth() {           // Прошедший месяц
        var mainPage = new MainPage();
        mainPage.openCreditPage();
        var payment = new CreditPage();
        payment.completedForm(DataHelper.getDateLastMonth());
        payment.expectationInvalidDataCard();
        assertEquals("Неверно указан срок действия карты", payment.getInvalidText());
    }
    @Test
    void shouldByPaymentNonExistentMonth() {          // Не существующий месяц
        var mainPage = new MainPage();
        mainPage.openCreditPage();
        var payment = new CreditPage();
        payment.completedForm(DataHelper.getDateNonExistentMonth());
        payment.expectationInvalidDataCard();
        assertEquals("Неверно указан срок действия карты", payment.getInvalidText());
    }
    @Test
    void shouldByPaymentOneSymbolInMonth() {          // Одна цифра в месяце
        var mainPage = new MainPage();
        mainPage.openCreditPage();
        var payment = new CreditPage();
        payment.completedForm(DataHelper.getDateOneSymbolIntMonth());
        payment.expectationInvalidFormat();
        assertEquals("Неверный формат", payment.getInvalidText());
    }
    @Test
    void shouldByPaymentPastYear() {          // Прошлый год
        var mainPage = new MainPage();
        mainPage.openCreditPage();
        var payment = new CreditPage();
        payment.completedForm(DataHelper.getDatePastYear());
        payment.expectationCardExpired();
        assertEquals("Истёк срок действия карты", payment.getInvalidText());
    }
    @Test
    void shouldByPaymentNextYear() {          // Год не входящий в диапазон валидных (+6)
        var mainPage = new MainPage();
        mainPage.openCreditPage();
        var payment = new CreditPage();
        payment.completedForm(DataHelper.getDateNextYear());
        payment.expectationInvalidDataCard();
        assertEquals("Неверно указан срок действия карты", payment.getInvalidText());
    }
    @Test
    void shouldByPaymentOneSymbolInYear() {          // Одна цифра при указании года
        var mainPage = new MainPage();
        mainPage.openCreditPage();
        var payment = new CreditPage();
        payment.completedForm(DataHelper.getDateOneSymbolIntYear());
        payment.expectationInvalidFormat();
        assertEquals("Неверный формат", payment.getInvalidText());
    }
    @Test
    void shouldByEnterNameCyrillic() {        //Ввод имени на кириллице
        var mainPage = new MainPage();
        mainPage.openCreditPage();
        var payment = new CreditPage();
        payment.completedForm(DataHelper.getNameCyrillic());
        payment.expectationInvalidFormat();
        assertEquals("Неверный формат", payment.getInvalidText());
    }
    @Test
    void shouldByEnterSpecialSymbol() {        //Ввод в поле спец-символов
        var mainPage = new MainPage();
        mainPage.openCreditPage();
        var payment = new CreditPage();
        payment.completedForm(DataHelper.getNameSpecialSymbol());
        payment.expectationInvalidFormat();
        assertEquals("Неверный формат", payment.getInvalidText());
    }
    @Test
    void shouldByEnterNumbers() {        //Ввод в поле "Имя" цифры
        var mainPage = new MainPage();
        mainPage.openCreditPage();
        var payment = new CreditPage();
        payment.completedForm(DataHelper.getNameNumbers());
        payment.expectationInvalidFormat();
        assertEquals("Неверный формат", payment.getInvalidText());
    }
    @Test
    void shouldByEnterManySymbol() {        //Ввод в поле "Имя" >64 символов
        var mainPage = new MainPage();
        mainPage.openCreditPage();
        var payment = new CreditPage();
        payment.completedForm(DataHelper.getNameManySymbol());
        payment.expectationInvalidFormat();
        assertEquals("Неверный формат", payment.getInvalidText());
    }
    @Test
    void shouldByEnterCard15Numbers() {        //Ввод в поле "Номер карты" 15 цифр
        var mainPage = new MainPage();
        mainPage.openCreditPage();
        var payment = new CreditPage();
        payment.completedForm(DataHelper.getCard15Numbers());
        payment.expectationInvalidFormat();
        assertEquals("Неверный формат", payment.getInvalidText());
    }
    @Test
    void shouldByEnterCardAllZero() {        //Ввод в поле "Номер карты" все 0
        var mainPage = new MainPage();
        mainPage.openCreditPage();
        var payment = new CreditPage();
        payment.completedForm(DataHelper.getCardAllZero());
        payment.expectationError();
        // assertEquals("Неверный формат", payment.getInvalidText());
    }
    @Test
    void shouldByEnterCvcAllZero() {        //Ввод в поле "CVC" все 0
        var mainPage = new MainPage();
        mainPage.openCreditPage();
        var payment = new CreditPage();
        payment.completedForm(DataHelper.getCvcAllZero());
        payment.expectationError();
        // assertEquals("Неверный формат", payment.getInvalidText());
    }
    @Test
    void shouldByEnterCvcOneNumber() {        //Ввод в поле "CVC" одну цифру
        var mainPage = new MainPage();
        mainPage.openCreditPage();
        var payment = new CreditPage();
        payment.completedForm(DataHelper.getCvcOneNumber());
        payment.expectationInvalidFormat();
        assertEquals("Неверный формат", payment.getInvalidText());
    }
}