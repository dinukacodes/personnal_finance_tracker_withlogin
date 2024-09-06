package net.enjoy.springboot.registrationlogin.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transaction_id;
    @NotNull(message ="Amount required!")
    private BigDecimal amount;
    private String discriprion;
    @NotEmpty(message ="transaction date required!")
    private LocalDateTime transactionDate;
    private TransactionType transactionType;
    @ManyToOne(optional=false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Transaction(long id, BigDecimal amount, String discriprion, LocalDateTime transactionDate, TransactionType transactionType, Category category, User user) {
        this.transaction_id = id;
        this.amount = amount;
        this.discriprion = discriprion;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.category = category;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Transaction() {}
    public long getId() {
        return transaction_id;
    }

    public void setId(long id) {
        this.transaction_id = id;
    }

    public  BigDecimal getAmount() {
        return amount;
    }

    public void setAmount( BigDecimal amount) {
        this.amount = amount;
    }

    public String getDiscriprion() {
        return discriprion;
    }

    public void setDiscriprion(String discriprion) {
        this.discriprion = discriprion;
    }

    public  LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
