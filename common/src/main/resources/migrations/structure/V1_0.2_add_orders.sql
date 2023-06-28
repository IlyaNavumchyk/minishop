create table if not exists orders
(
    id                bigserial primary key,
    customer_id       bigint not null
        constraint orders_customers_id_fk
            references customers,
    price decimal(10,2) not null,
    status varchar(20) not null,
    creation_date     timestamp(6),
    modification_date timestamp(6)
);