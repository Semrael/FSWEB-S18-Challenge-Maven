package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")//isteği istediğimiz her yerden atabilmek için
@RestController
@RequestMapping("/cards")
public class CardController {
    private final CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @PostMapping
    public Card save(@RequestBody Card card){
        CardValidation.validateCard(card);
        return cardRepository.save(card);
    }

    @GetMapping
    public List<Card> findAll(){
        return cardRepository.findAll();
    }
    @GetMapping("/byColor/{color}")
    public List<Card> findByColor(@PathVariable ("color") String color){
     return cardRepository.findByColor(color);
    }
    @PutMapping("/{id}")
    public Card update(@PathVariable("id") Long id,@RequestBody Card card){
        Optional<Card> opCard = cardRepository.findById(id);
        if(!opCard.isPresent()) {
            throw new CardException("id yok" + id, HttpStatus.NOT_FOUND);
        }
        card.setId(id);
        return cardRepository.save(card);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)//204
    public void delete(@PathVariable("id") Long id){
        Optional<Card> opCard = cardRepository.findById(id);
        if(!opCard.isPresent()) {
            throw new CardException("id yok" + id, HttpStatus.NOT_FOUND);
        }
        cardRepository.delete(opCard.get());
    }

    @GetMapping("/byValue/{value}")
    public List<Card> findByValue(@PathVariable ("value") Integer value){
        return cardRepository.findByValue(value);
    }
    @GetMapping("/byType/{type}")
    public List<Card> findByValue(@PathVariable ("type") String type){
        return cardRepository.findByType(type);
    }

}
