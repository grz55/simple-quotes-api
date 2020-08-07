package com.grz55.simplequotesapi.gui;

import com.grz55.simplequotesapi.controller.QuoteController;
import com.grz55.simplequotesapi.model.Quote;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("gui/quotes")
@StyleSheet("/css/style.css")
public class QuoteGui extends VerticalLayout {

    private static final String QUOTE_ADDED_MESSAGE = "Quote has been added";
    private static final int NOTIFICATION_DURATION = 3000;


    private TextField textFieldAuthor;
    private TextField textFieldQuote;
    private Button buttonSave;
    private QuoteController quoteController;
    private Notification notificationAdded;
    private Grid<Quote> quotesGrid;

    @Autowired
    public QuoteGui(QuoteController quoteController) {
        init(quoteController);
    }

    private void init(QuoteController quoteController) {
        this.quoteController = quoteController;
        textFieldAuthor = new TextField("Add author");
        textFieldQuote = new TextField("Add quote");
        buttonSave = new Button("Save");
        notificationAdded = new Notification(QUOTE_ADDED_MESSAGE, NOTIFICATION_DURATION);
        quotesGrid = new Grid<>(Quote.class);

        quotesGrid.setItems(quoteController.getAllQuotes());
        quotesGrid.removeColumnByKey("id");
        quotesGrid.setColumns("author", "quote");

        initActions();

        add(textFieldAuthor, textFieldQuote, buttonSave, quotesGrid);
    }

    private void initActions() {
        buttonSave.addClickListener(buttonClickEvent -> {
            Quote quote = new Quote(textFieldAuthor.getValue(), textFieldQuote.getValue());
            quoteController.addQuote(quote);
            notificationAdded.open();
            quotesGrid.setItems(quoteController.getAllQuotes());
        });
    }
}
