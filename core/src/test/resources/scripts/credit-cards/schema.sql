create table CreditCard
(
    creditCardId   binary       not null,
    cardNumber     varchar(100) not null,
    expirationDate varchar(100) not null,
    ownerName      varchar(100) not null,
    securityCode   varchar(100) not null,
    primary key (creditCardId)
)
