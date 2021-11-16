drop table if exists bank_transactions;
drop table if exists bank_accounts cascade;
drop table if exists app_users;

create table app_users (
    user_id varchar check (user_id <> ''),
    first_name varchar(25) not null check (first_name <> ''),
    last_name varchar(25) not null check (last_name <> ''),
    email varchar(255) unique not null check (email <> ''),
    username varchar(20) unique not null check (username <> ''),
    password varchar(255) not null check (password <> ''),
    date_added timestamp NOT NULL DEFAULT LOCALTIMESTAMP,

    constraint app_users_pk
    primary key (user_id)
);

create table bank_accounts (
    bank_account_id varchar check (bank_account_id <> ''),
    account_name varchar not null check (account_name <> ''),
    account_type varchar not null check (account_type <> ''),
    creator_id varchar not null check (creator_id <> ''),
    balance DOUBLE PRECISION default 0.00 not null ,
    date_added timestamp NOT NULL DEFAULT LOCALTIMESTAMP,

    constraint bank_account_pk
    primary key (bank_account_id),

    constraint bank_account_creator_fk
    foreign key (creator_id)
    references app_users
);

create table bank_transactions (
    bank_transaction_id varchar check (bank_transaction_id <> ''),
    bank_account_id_from varchar check (bank_account_id_from <> ''),
    bank_account_id_to varchar check (bank_account_id_to <> ''),
    trader_id varchar not null check (trader_id <> ''),
    amount DOUBLE PRECISION default 0.00 not null ,
    date_added timestamp NOT NULL DEFAULT LOCALTIMESTAMP,

    constraint bank_transaction_pk
    primary key (bank_transaction_id),

    constraint bank_transaction_creator_fk
    foreign key (trader_id)
    references app_users,

    constraint bank_account_id_from_fk
    foreign key (bank_account_id_from)
    references bank_accounts,

    constraint bank_account_id_to_fk
    foreign key (bank_account_id_to)
    references bank_accounts
);

-- test --