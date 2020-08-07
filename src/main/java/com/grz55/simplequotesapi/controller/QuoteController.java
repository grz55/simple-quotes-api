package com.grz55.simplequotesapi.controller;

import com.grz55.simplequotesapi.exception.QuoteNotFoundException;
import com.grz55.simplequotesapi.model.Quote;
import com.grz55.simplequotesapi.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/all")
    public List<Quote> getAllQuotes() {
        return quoteService.findAll();
    }

    @GetMapping("/{id}")
    public Quote getQuoteById(@PathVariable long id) {
        return quoteService.findById(id).orElseThrow(() -> new QuoteNotFoundException(id));
    }

    @PostMapping
    public Quote addQuote(@RequestBody Quote quote) {
        return quoteService.save(quote);
    }

    @PutMapping("/{id}")
    public Quote updateQuote(@RequestBody Quote quote, @PathVariable Long id) {
        return quoteService.findById(id)
                .map(element -> {
                    element.setAuthor(quote.getAuthor());
                    element.setQuote(quote.getQuote());
                    return quoteService.save(element);
                })
                .orElseThrow(() -> new QuoteNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public void deleteQuote(@PathVariable long id) {
        if (quoteService.findById(id).isPresent()) {
            quoteService.deleteQuote(id);
        } else {
            throw new QuoteNotFoundException(id);
        }
    }
}
