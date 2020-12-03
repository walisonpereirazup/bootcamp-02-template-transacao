create table cards (
    id uuid not null,
    email varchar(255),
    primary key (id)
);

create table recipients (
    id uuid not null,
    address varchar(255),
    city varchar(255),
    name varchar(255),
    primary key (id)
);

create table transactions (
    id uuid not null,
    amount numeric(19, 2) not null,
    created_at timestamp not null,
    card_id uuid not null,
    recipient_id uuid not null,
    primary key (id)
);

alter table transactions
    add constraint FKjxdscq0bxpy0pl465vvsqc89j
    foreign key (card_id)
    references cards;

alter table transactions
    add constraint FKeb6dvsta94443q3g2iq8pelsg
    foreign key (recipient_id)
    references recipients;
