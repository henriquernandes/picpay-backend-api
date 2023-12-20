package com.picpay.api.services;

import com.picpay.api.domains.transaction.Transaction;
import com.picpay.api.domains.transaction.TransactionStatus;
import com.picpay.api.domains.user.User;
import com.picpay.api.dtos.TransactionDTO;
import com.picpay.api.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User payer = this.userService.findUserById(transaction.senderId());
        User payee = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(payer, transaction.amount());

        if(!authorizeTransaction(payer, transaction.amount())) {
            throw new Exception("Transaction not authorized");
        }

        Transaction newTransaction = new Transaction(
                null,
                payee,
                payer,
                transaction.amount(),
                TransactionStatus.CREATED,
                LocalDateTime.now()
        );

        payer.setBalance(payer.getBalance().subtract(transaction.amount()));
        payee.setBalance(payee.getBalance().add(transaction.amount()));

        this.transactionRepository.save(newTransaction);
        this.userService.save(payer);
        this.userService.save(payee);
        this.notificationService.sendNotification(payee, "You received a payment from " + payer.getFirstName() + " " + payer.getLastName());
        this.notificationService.sendNotification(payer, "You made a payment to " + payee.getFirstName() + " " + payee.getLastName());

        return newTransaction;
    }

    public boolean authorizeTransaction(User payer, BigDecimal amount) {
        ResponseEntity<Map> authroizationResponse =  restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        if(authroizationResponse.getStatusCode().is2xxSuccessful()) {
            String authorizationMessage = (String) authroizationResponse.getBody().get("message");
            return authorizationMessage.equals("Autorizado");
        }

        return false;
    }

}
