package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class PaymentPageTest {
    private static String appUrl = System.getProperty("sut.url");

    @BeforeEach
    public void setUp2() {
        open(appUrl, MainPage.class);
        SQLHelper.cleanDataBase();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");}

    @Test
    @DisplayName("Should successfully pay from APPROVED card")
    void shouldSuccessfullyPayFromApprovedCard() {
        var cardInfo = DataHelper.getFirstCardNumberAndStatus();
        var paymentPage = MainPage.openPaymentPage(cardInfo);
        paymentPage.validPayCardAndInValidPayCard(cardInfo);
        paymentPage.verifySuccessPayVisibility();
    }

    @Test
    @DisplayName("Should show error message when pay from DECLINED card")
    void shouldShowErrorWhenPayFromDeclinedCard() {
        var cardInfo = DataHelper.getSecondCardNumberAndStatus();
        var paymentPage = MainPage.openPaymentPage(cardInfo);
        paymentPage.validPayCardAndInValidPayCard(cardInfo);
        paymentPage.verifyDeclinePayVisibility();
    }
    @Test
    @DisplayName("Should show error message when pay card and cardnumber field empty")
    void shouldShowErrorWhenPayCardAndCardNumberFieldEmpty() {
        var cardInfo = DataHelper.getFirstCardNumberAndStatus();
        var paymentPage = MainPage.openPaymentPage(cardInfo);
        paymentPage.verifyEmptyField();
    }
    @Test
    @DisplayName("Should show error message when pay card with invalid cardnumber ")
    void shouldShowErrorWhenPayCardWithInvalidCardNumber() {
        var cardInfo = DataHelper.getFirstCardNumberAndStatus();
        var paymentPage = MainPage.openPaymentPage(cardInfo);
        paymentPage.validPayCardAndInValidPayCard(cardInfo);
        paymentPage.verifyDeclinePayVisibility();
    }
    @Test
    @DisplayName("Should get MySQL status when pay from APPROVED card")
    void shouldGetMySQLStatusWhenPayFromApprovedCard() {
        var cardInfo = DataHelper.getFirstCardNumberAndStatus();
        var paymentPage = MainPage.openPaymentPage(cardInfo);
        paymentPage.validPayCardAndInValidPayCard(cardInfo);
        var paymentStatus = SQLHelper.getPaymentStatus();
        Assertions.assertEquals("APPROVED", paymentStatus);
    }
    @Test
    @DisplayName("Should get MySQL amount when pay from APPROVED card")
    void shouldGetMySQLStatusAmountWhenPayFromApprovedCard() {
        var cardInfo = DataHelper.getFirstCardNumberAndStatus();
        var paymentPage = MainPage.openPaymentPage(cardInfo);
        paymentPage.validPayCardAndInValidPayCard(cardInfo);
        var paymentAmount = (SQLHelper.getPaymentStatus());
        Assertions.assertEquals(4500000, paymentAmount);
    }

    @Test
    @DisplayName("Should get MySQL status when pay from DECLINED card")
    void shouldGetMySQLStatusWhenPayFromDeclinedCard() {
        var cardInfo = DataHelper.getSecondCardNumberAndStatus();
        var paymentPage = MainPage.openPaymentPage(cardInfo);
        paymentPage.validPayCardAndInValidPayCard(cardInfo);
        var paymentStatus = SQLHelper.getPaymentStatus();
        Assertions.assertEquals("DECLINED", paymentStatus);

    }

    @Test
    @DisplayName("Should get MySQL amount when pay from DECLINED card")
    void shouldGetMySQLStatusAndRightAmountWhenPayFromApprovedCard() {
        var cardInfo = DataHelper.getSecondCardNumberAndStatus();
        var paymentPage = MainPage.openPaymentPage(cardInfo);
        paymentPage.validPayCardAndInValidPayCard(cardInfo);
        var paymentAmount = (SQLHelper.getPaymentStatus());
        Assertions.assertEquals(0, paymentAmount);

    }
    @Test
    @DisplayName("Should get PostgreSQL status when pay from APPROVED card")
    void shouldGetPostgreSQLStatusWhenPayFromApprovedCard() {
        var cardInfo = DataHelper.getFirstCardNumberAndStatus();
        var paymentPage = MainPage.openPaymentPage(cardInfo);
        paymentPage.validPayCardAndInValidPayCard(cardInfo);
        var paymentStatus = SQLHelper.getPaymentStatus();
        Assertions.assertEquals("APPROVED", paymentStatus);

    }

    @Test
    @DisplayName("Should get PostgreSQL amount when pay from APPROVED card")
    void shouldGetPostgreSQLStatusAmountWhenPayFromApprovedCard() {
        var cardInfo = DataHelper.getFirstCardNumberAndStatus();
        var paymentPage = MainPage.openPaymentPage(cardInfo);
        paymentPage.validPayCardAndInValidPayCard(cardInfo);
        var paymentAmount = (SQLHelper.getPaymentStatus());
        Assertions.assertEquals(4500000, paymentAmount);
    }

    @Test
    @DisplayName("Should get PostgreSQL status when pay from DECLINED card")
    void shouldGetPostgreSQLStatusWhenPayFromDeclinedCard() {
        var cardInfo = DataHelper.getSecondCardNumberAndStatus();
        var paymentPage = MainPage.openPaymentPage(cardInfo);
        paymentPage.validPayCardAndInValidPayCard(cardInfo);
        var paymentStatus = SQLHelper.getPaymentStatus();
        Assertions.assertEquals("DECLINED", paymentStatus);

    }

    @Test
    @DisplayName("Should get PostgreSQL amount when pay from DECLINED card")
    void shouldGetPostgreSQLStatusAndRightAmountWhenPayFromApprovedCard() {
        var cardInfo = DataHelper.getSecondCardNumberAndStatus();
        var paymentPage = MainPage.openPaymentPage(cardInfo);
        paymentPage.validPayCardAndInValidPayCard(cardInfo);
        var paymentAmount = (SQLHelper.getPaymentStatus());
        Assertions.assertEquals(0, paymentAmount);

    }

}