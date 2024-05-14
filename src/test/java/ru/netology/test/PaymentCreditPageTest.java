package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.junit5.TextReportExtension;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.CreditPage;
import ru.netology.page.MainPage;
import io.qameta.allure.Allure;
import ru.netology.page.PaymentPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.getLastPayUserStatusMySQL;

public class PaymentCreditPageTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Should successfully pay from APPROVED card")
    void shouldSuccessfullyPayFromApprovedCard() {
        var CardInfo = DataHelper.getFirstCardNumberAndStatus();
        var PaymentPage = MainPage.openPaymentPage(CardInfo);
        PaymentPage.validPayCard(CardInfo);
        PaymentPage.verifySuccessPayVisibility();
    }

    @Test
    @DisplayName("Should show error message when pay from DECLINED card")
    void shouldShowErrorWhenPayFromDeclinedCard() {
        var CardInfo = DataHelper.getSecondCardNumberAndStatus();
        var PaymentPage = MainPage.openPaymentPage(CardInfo);
        PaymentPage.validPayCard(CardInfo);
        PaymentPage.verifyDeclinePayVisibility();
    }


    @Test
    @DisplayName("Should successfully pay on credit from APPROVED card")
    void shouldSuccessfullyРayОnСreditFromApprovedCard() {
        var CardInfo = DataHelper.getFirstCardNumberAndStatus();
        var CreditPage = MainPage.openCreditPage(CardInfo);
        CreditPage.validPayCard(CardInfo);
        CreditPage.verifySuccessPayVisibility();
    }

    @Test
    @DisplayName("Should show error message when pay on credit from DECLINED card")
    void shouldShowErrorWhenPayОnСreditFromDeclinedCard() {
        var CardInfo = DataHelper.getSecondCardNumberAndStatus();
        var CreditPage = MainPage.openCreditPage(CardInfo);
        CreditPage.validPayCard(CardInfo);
        CreditPage.verifyDeclinePayVisibility();
    }


    @Test
    @DisplayName("Should show error message when pay card and cardnumber field empty")
    void shouldShowErrorWhenPayCardAndCardNumberFieldEmpty() {
        var CardInfo = DataHelper.getFirstCardNumberAndStatus();
        var PaymentPage = MainPage.openPaymentPage(CardInfo);
        PaymentPage.emptyField(CardInfo);
        PaymentPage.verifyEmptyField();
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
    @DisplayName("Should show error message when pay card with invalid cardnumber ")
    void shouldShowErrorWhenPayCardWithInvalidCardNumber() {
        var CardInfo = DataHelper.getFirstCardNumberAndStatus();
        var PaymentPage = MainPage.openPaymentPage(CardInfo);
        PaymentPage.inValidPayCard(CardInfo);
        PaymentPage.verifyDeclinePayVisibility();
    }

    @Test
    @DisplayName("Should show error message when pay on credit with invalid cardnumber ")
    void shouldShowErrorWhenОnСreditCardWithInvalidCardNumber() {
        var CardInfo = DataHelper.getFirstCardNumberAndStatus();
        var CreditPage = MainPage.openCreditPage(CardInfo);
        CreditPage.inValidPayCard(CardInfo);
        CreditPage.verifyDeclinePayVisibility();
    }

    @Test
    @DisplayName("Should get MySQL status when pay from APPROVED card")
    void shouldGetMySQLStatusWhenPayFromApprovedCard() {
        var CardInfo = DataHelper.getFirstCardNumberAndStatus();
        var PaymentPage = MainPage.openPaymentPage(CardInfo);
        PaymentPage.validPayCard(CardInfo);
        var PaymentStatus = SQLHelper.getLastPayUserStatusMySQL();
        Assertions.assertEquals("APPROVED", PaymentStatus);

    }

    @Test
    @DisplayName("Should get MySQL amount when pay from APPROVED card")
    void shouldGetMySQLStatusAmountWhenPayFromApprovedCard() {
        var CardInfo = DataHelper.getFirstCardNumberAndStatus();
        var PaymentPage = MainPage.openPaymentPage(CardInfo);
        PaymentPage.validPayCard(CardInfo);
        var PaymentAmount = (SQLHelper.getLastPayUserAmountMySQL());
        Assertions.assertEquals(45000, PaymentAmount);
    }

    @Test
    @DisplayName("Should get MySQL status when pay from DECLINED card")
    void shouldGetMySQLStatusWhenPayFromDeclinedCard() {
        var CardInfo = DataHelper.getSecondCardNumberAndStatus();
        var PaymentPage = MainPage.openPaymentPage(CardInfo);
        PaymentPage.validPayCard(CardInfo);
        var PaymentStatus = SQLHelper.getLastPayUserStatusMySQL();
        Assertions.assertEquals("DECLINED", PaymentStatus);

    }

    @Test
    @DisplayName("Should get MySQL amount when pay from DECLINED card")
    void shouldGetMySQLStatusAndRightAmountWhenPayFromApprovedCard() {
        var CardInfo = DataHelper.getSecondCardNumberAndStatus();
        var PaymentPage = MainPage.openPaymentPage(CardInfo);
        PaymentPage.validPayCard(CardInfo);
        var PaymentAmount = (SQLHelper.getLastPayUserAmountMySQL());
        Assertions.assertEquals(0, PaymentAmount);

    }

    @Test
    @DisplayName("Should get MySQL status when pay on credit from APPROVED card")
    void shouldGetMySQLStatusWhenPayOnCreditFromApprovedCard() {
        var CardInfo = DataHelper.getFirstCardNumberAndStatus();
        var CreditPage = MainPage.openCreditPage(CardInfo);
        CreditPage.validPayCard(CardInfo);
        var CreditStatus = SQLHelper.getLastPayOnCreditUserStatusMySQL();
        Assertions.assertEquals("APPROVED", CreditStatus);

    }

    @Test
    @DisplayName("Should get MySQL status when pay on credit from DECLINED card")
    void shouldGetMySQLStatusWhenPayOnCreditFromDeclinedCard() {
        var CardInfo = DataHelper.getSecondCardNumberAndStatus();
        var CreditPage = MainPage.openCreditPage(CardInfo);
        CreditPage.validPayCard(CardInfo);
        var CreditStatus = SQLHelper.getLastPayOnCreditUserStatusMySQL();
        Assertions.assertEquals("DECLINED", CreditStatus);

    }

    @Test
    @DisplayName("Should get PostgreSQL status when pay from APPROVED card")
    void shouldGetPostgreSQLStatusWhenPayFromApprovedCard() {
        var CardInfo = DataHelper.getFirstCardNumberAndStatus();
        var PaymentPage = MainPage.openPaymentPage(CardInfo);
        PaymentPage.validPayCard(CardInfo);
        var PaymentStatus = SQLHelper.getLastPayUserStatusPostgreSQL();
        Assertions.assertEquals("APPROVED", PaymentStatus);

    }

    @Test
    @DisplayName("Should get PostgreSQL amount when pay from APPROVED card")
    void shouldGetPostgreSQLStatusAmountWhenPayFromApprovedCard() {
        var CardInfo = DataHelper.getFirstCardNumberAndStatus();
        var PaymentPage = MainPage.openPaymentPage(CardInfo);
        PaymentPage.validPayCard(CardInfo);
        var PaymentAmount = (SQLHelper.getLastPayUserAmountPostgreSQL());
        Assertions.assertEquals(45000, PaymentAmount);
    }

    @Test
    @DisplayName("Should get PostgreSQL status when pay from DECLINED card")
    void shouldGetPostgreSQLStatusWhenPayFromDeclinedCard() {
        var CardInfo = DataHelper.getSecondCardNumberAndStatus();
        var PaymentPage = MainPage.openPaymentPage(CardInfo);
        PaymentPage.validPayCard(CardInfo);
        var PaymentStatus = SQLHelper.getLastPayUserStatusPostgreSQL();
        Assertions.assertEquals("DECLINED", PaymentStatus);

    }

    @Test
    @DisplayName("Should get PostgreSQL amount when pay from DECLINED card")
    void shouldGetPostgreSQLStatusAndRightAmountWhenPayFromApprovedCard() {
        var CardInfo = DataHelper.getSecondCardNumberAndStatus();
        var PaymentPage = MainPage.openPaymentPage(CardInfo);
        PaymentPage.validPayCard(CardInfo);
        var PaymentAmount = (SQLHelper.getLastPayUserAmountPostgreSQL());
        Assertions.assertEquals(0, PaymentAmount);

    }

    @Test
    @DisplayName("Should get PostgreSQL status when pay on credit from APPROVED card")
    void shouldGetPostgreSQLStatusWhenPayOnCreditFromApprovedCard() {
        var CardInfo = DataHelper.getFirstCardNumberAndStatus();
        var CreditPage = MainPage.openCreditPage(CardInfo);
        CreditPage.validPayCard(CardInfo);
        var CreditStatus = SQLHelper.getLastPayOnCreditUserStatusPostgreSQL();
        Assertions.assertEquals("APPROVED", CreditStatus);

    }

    @Test
    @DisplayName("Should get PostgreSQL status when pay on credit from DECLINED card")
    void shouldGetPostgreSQLStatusWhenPayOnCreditFromDeclinedCard() {
        var CardInfo = DataHelper.getSecondCardNumberAndStatus();
        var CreditPage = MainPage.openCreditPage(CardInfo);
        CreditPage.validPayCard(CardInfo);
        var CreditStatus = SQLHelper.getLastPayOnCreditUserStatusPostgreSQL();
        Assertions.assertEquals("DECLINED", CreditStatus);

    }

}
