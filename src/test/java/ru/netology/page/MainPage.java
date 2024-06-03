package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private static final SelenideElement buyButton = $(".button_size_m");
    private static final SelenideElement buyCreditButton = $(".button_view_extra");


    public static PaymentPage openPaymentPage(DataHelper.CardInfo info) {
        buyButton.click();
        return new PaymentPage();
    }

    public static CreditPage openCreditPage(DataHelper.CardInfo info) {
        buyCreditButton.click();
        return new CreditPage();
    }


}