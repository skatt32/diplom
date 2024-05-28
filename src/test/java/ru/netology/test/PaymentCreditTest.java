package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.MainPage;

public class PaymentCreditTest {

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
        var CardInfo = DataHelper.getFirstCardNumberAndStatus();
        var CreditPage = MainPage.openCreditPage(CardInfo);
        CreditPage.validPayCardAndInValidPayCard(CardInfo);
        CreditPage.verifySuccessPayVisibility();
    }

    @Test
    @DisplayName("Should show error message when pay on credit from DECLINED card")
    void shouldShowErrorWhenPayОnСreditFromDeclinedCard() {
        var CardInfo = DataHelper.getSecondCardNumberAndStatus();
        var CreditPage = MainPage.openCreditPage(CardInfo);
        CreditPage.validPayCardAndInValidPayCard(CardInfo);
        CreditPage.verifyDeclinePayVisibility();
    }

    @Test
    @DisplayName("Should show error message when pay on credit and cardnumber field empty")
    void shouldShowErrorWhenPayОnСreditAndCardNumberFieldEmpty() {
        var CardInfo = DataHelper.getFirstCardNumberAndStatus();
        var CreditPage = MainPage.openCreditPage(CardInfo);
        CreditPage.emptyField(CardInfo);
        CreditPage.verifyEmptyField();
    }

    @Test
    @DisplayName("Should show error message when pay on credit with invalid cardnumber ")
    void shouldShowErrorWhenОnСreditCardWithInvalidCardNumber() {
        var CardInfo = DataHelper.getFirstCardNumberAndStatus();
        var CreditPage = MainPage.openCreditPage(CardInfo);
        CreditPage.validPayCardAndInValidPayCard(CardInfo);
        CreditPage.verifyDeclinePayVisibility();
    }

    @Test
    @DisplayName("Should get MySQL status when pay on credit from APPROVED card")
    void shouldGetMySQLStatusWhenPayOnCreditFromApprovedCard() {
        var CardInfo = DataHelper.getFirstCardNumberAndStatus();
        var CreditPage = MainPage.openCreditPage(CardInfo);
        CreditPage.validPayCardAndInValidPayCard(CardInfo);
        var CreditStatus = SQLHelper.getPaymentStatus();
        Assertions.assertEquals("APPROVED", CreditStatus);

    }

    @Test
    @DisplayName("Should get MySQL status when pay on credit from DECLINED card")
    void shouldGetMySQLStatusWhenPayOnCreditFromDeclinedCard() {
        var CardInfo = DataHelper.getSecondCardNumberAndStatus();
        var CreditPage = MainPage.openCreditPage(CardInfo);
        CreditPage.validPayCardAndInValidPayCard(CardInfo);
        var CreditStatus = SQLHelper.getPaymentStatus();
        Assertions.assertEquals("DECLINED", CreditStatus);

    }

    @Test
    @DisplayName("Should get PostgreSQL status when pay on credit from APPROVED card")
    void shouldGetPostgreSQLStatusWhenPayOnCreditFromApprovedCard() {
        var CardInfo = DataHelper.getFirstCardNumberAndStatus();
        var CreditPage = MainPage.openCreditPage(CardInfo);
        CreditPage.validPayCardAndInValidPayCard(CardInfo);
        var CreditStatus = SQLHelper.getPaymentStatus();
        Assertions.assertEquals("APPROVED", CreditStatus);

    }
    @Test
    @DisplayName("Should get PostgreSQL status when pay on credit from DECLINED card")
    void shouldGetPostgreSQLStatusWhenPayOnCreditFromDeclinedCard() {
        var CardInfo = DataHelper.getSecondCardNumberAndStatus();
        var CreditPage = MainPage.openCreditPage(CardInfo);
        CreditPage.validPayCardAndInValidPayCard(CardInfo);
        var CreditStatus = SQLHelper.getPaymentStatus();
        Assertions.assertEquals("DECLINED", CreditStatus);

    }

}
