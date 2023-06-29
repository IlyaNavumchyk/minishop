create table if not exists customers
(
    id                bigserial primary key,
    customer_login    varchar(20)  not null unique,
    constraint customers_customers_login_check_length_more_than_3 check (length(customer_login) > 3),
    customer_password varchar(20)  not null,
    constraint customers_customer_password_check_length_more_than_7 check (length(customer_password) > 7),
    email             varchar(100) not null unique,
    constraint customers_email_check_format check (email similar to '_%@_%.__%'),
    phone             varchar(13)  not null,
    constraint customers_phone_check_format check (phone similar to '+____________'),
    is_deleted        boolean,
    creation_date     timestamp(6),
    modification_date timestamp(6)
);