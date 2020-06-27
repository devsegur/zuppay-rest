create table Payment
(
    paymentId                 binary        not null,
    currency                  integer       not null,
    description               varchar(100)  not null,
    dueDate                   date          not null,
    money                     decimal(2, 2) not null,
    productId                 varchar(100)  not null,
    creditCard_creditCardId   binary,
    transaction_transactionId binary,
    primary key (paymentId)
)


