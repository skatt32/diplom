package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class PaymentCreditTest {
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
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Should successfully pay on credit from APPROVED card")
    void shouldSuccessfullyРayОnСreditFromApprovedCard() {
        var cardInfo = DataHelper.getFirstCardNumberAndStatus();
        var creditPage = MainPage.openCreditPage(cardInfo);
        creditPage.validPayCardAndInValidPayCard(cardInfo);
        creditPage.verifySuccessPayVisibility();
    }

    @Test
    @DisplayName("Should show error message when pay on credit from DECLINED card")
    void shouldShowErrorWhenPayОnСreditFromDeclinedCard() {
        var cardInfo = DataHelper.getSecondCardNumberAndStatus();
        var creditPage = MainPage.openCreditPage(cardInfo);
        creditPage.validPayCardAndInValidPayCard(cardInfo);
        creditPage.verifyDeclinePayVisibility();
    }

    @Test
    @DisplayName("Should show error message when pay on credit and cardnumber field empty")
    void shouldShowErrorWhenPayОnСreditAndCardNumberFieldEmpty() {
        var cardInfo = DataHelper.getFirstCardNumberAndStatus();
        var creditPage = MainPage.openCreditPage(cardInfo);
        creditPage.verifyEmptyField();
    }

    @Test
    @DisplayName("Should show error message when pay on credit with invalid cardnumber ")
    void shouldShowErrorWhenОnСreditCardWithInvalidCardNumber() {
        var cardInfo = DataHelper.getFirstCardNumberAndStatus();
        var creditPage = MainPage.openCreditPage(cardInfo);
        creditPage.validPayCardAndInValidPayCard(cardInfo);
        creditPage.verifyDeclinePayVisibility();
    }

    @Test
    @DisplayName("Should get MySQL status when pay on credit from APPROVED card")
    void shouldGetMySQLStatusWhenPayOnCreditFromApprovedCard() {
        var cardInfo = DataHelper.getFirstCardNumberAndStatus();
        var creditPage = MainPage.openCreditPage(cardInfo);
        creditPage.validPayCardAndInValidPayCard(cardInfo);
        var creditStatus = SQLHelper.getCreditStatus();
        Assertions.assertEquals("APPROVED", creditStatus);

    }

    @Test
    @DisplayName("Should get MySQL status when pay on credit from DECLINED card")
    void shouldGetMySQLStatusWhenPayOnCreditFromDeclinedCard() {
        var cardInfo = DataHelper.getSecondCardNumberAndStatus();
        var creditPage = MainPage.openCreditPage(cardInfo);
        creditPage.validPayCardAndInValidPayCard(cardInfo);
        var creditStatus = SQLHelper.getCreditStatus();
        Assertions.assertEquals("DECLINED", creditStatus);

    }

    @Test
    @DisplayName("Should get PostgreSQL status when pay on credit from APPROVED card")
    void shouldGetPostgreSQLStatusWhenPayOnCreditFromApprovedCard() {
        var cardInfo = DataHelper.getFirstCardNumberAndStatus();
        var creditPage = MainPage.openCreditPage(cardInfo);
        creditPage.validPayCardAndInValidPayCard(cardInfo);
        var creditStatus = SQLHelper.getCreditStatus();
        Assertions.assertEquals("APPROVED", creditStatus);

    }
    @Test
    @DisplayName("Should get PostgreSQL status when pay on credit from DECLINED card")
    void shouldGetPostgreSQLStatusWhenPayOnCreditFromDeclinedCard() {
        var cardInfo = DataHelper.getSecondCardNumberAndStatus();
        var creditPage = MainPage.openCreditPage(cardInfo);
        creditPage.validPayCardAndInValidPayCard(cardInfo);
        var creditStatus = SQLHelper.getCreditStatus();
        Assertions.assertEquals("DECLINED", creditStatus);

    }

}
