create table Transaction
(
    transactionId           binary       not null,
    chargeDate              date         not null,
    paymentStatus           varchar(100) not null,
    creditCard_creditCardId binary,
    payment_paymentId       binary,
    primary key (transactionId)
)
