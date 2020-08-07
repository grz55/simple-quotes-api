package com.grz55.simplequotesapi.service;

import com.grz55.simplequotesapi.model.Quote;
import com.grz55.simplequotesapi.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public List<Quote> findAll() {
        return quoteRepository.findAll();
    }

    public Optional<Quote> findById(Long id) {
        return quoteRepository.findById(id);
    }

    public Quote save(Quote quote) {
        return quoteRepository.save(quote);
    }

    public Quote updateQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    public void deleteQuote(Long id) {
        quoteRepository.deleteById(id);
    }
}
