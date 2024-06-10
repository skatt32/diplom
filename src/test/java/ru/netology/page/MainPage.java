package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    private SelenideElement heading = $$("h2").find(exactText("Путешествие дня"));
    private SelenideElement payButton = $$("button").find(exactText("Купить"));
    private SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));

    public MainPage() {
        heading.shouldBe(visible);
    }

    public PaymentPage openBuyPage() {
        payButton.click();
        return new PaymentPage();
    }

    public CreditPage openCreditPage() {
        creditButton.click();
        return new CreditPage();
    }
}