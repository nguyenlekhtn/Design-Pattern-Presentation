package factory_method.example.factory;

import factory_method.example.buttons.Button;
import factory_method.example.buttons.HtmlButton;

public class HtmlDialog extends Dialog {

    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}
