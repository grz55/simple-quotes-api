package com.grz55.simplequotesapi.gui;

import com.grz55.simplequotesapi.model.ApplicationUser;
import com.grz55.simplequotesapi.service.ApplicationUserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("register")
@StyleSheet("/css/style.css")
public class RegisterGui extends VerticalLayout {

    private static final String USER_REGISTERED_MESSAGE = "User registered successfully";

    private TextField textFieldUsername;
    private PasswordField passwordFieldUserPassword;
    private Button buttonRegister;
    private final ApplicationUserService applicationUserService;
    private Notification notificationRegistered;

    @Autowired
    public RegisterGui(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
        init();
        initActions();
        add(textFieldUsername, passwordFieldUserPassword, buttonRegister);
    }

    private void init() {
        textFieldUsername = new TextField("Username");
        passwordFieldUserPassword = new PasswordField("Password");
        buttonRegister = new Button("Register");
        notificationRegistered = new Notification(USER_REGISTERED_MESSAGE);
    }

    private void initActions() {
        buttonRegister.addClickListener(buttonClickEvent -> {
            ApplicationUser user = new ApplicationUser(textFieldUsername.getValue(),
                    passwordFieldUserPassword.getValue(),
                    "USER");
            applicationUserService.save(user);
            notificationRegistered.open();
        });
    }


}
