package com.phones.gui;


import com.phones.phones.AbstractPhone;
import com.phones.phones.mobilePhones.SmartPhone;
import com.phones.utils.ExtractFields;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;


@ExtendWith(ApplicationExtension.class)
class MainWindowTest{

    private Button button;

    @Start
    public void start(Stage stage) {
        stage.setTitle("Phone editor test title");
        AbstractPhone phone = new SmartPhone();
        VBox vBox = new ExtractFields(stage, phone);
        vBox.getChildren().add(button);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void should_contain_button_with_text(FxRobot robot) {
        FxAssert.verifyThat(this.button, LabeledMatchers.hasText("Click me"));
    }
}