CREATE TABLE broker (
    id BIGINT NOT NULL,
    username VARCHAR(255) NOT NULL ,
    first_name VARCHAR(255) NOT NULL ,
    last_name VARCHAR(255) NOT NULL,
    CONSTRAINT PK_broker PRIMARY KEY (id),
    CONSTRAINT UQ_broker_username UNIQUE (username)
);

CREATE TABLE sales_group (
    id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    transaction_type VARCHAR(255) NOT NULL,
    max_transaction_amount NUMERIC(38) NOT NULL,
    CONSTRAINT PK_sales_group PRIMARY KEY (id),
    CONSTRAINT UQ_sales_group_name UNIQUE (name)
);

CREATE TABLE broker_sales_group (
    broker_id BIGINT NOT NULL,
    sales_group_id BIGINT NOT NULL,
    CONSTRAINT PK_broker_sales_group PRIMARY KEY (broker_id, sales_group_id),
    CONSTRAINT FK_broker_sales_group_broker FOREIGN KEY (broker_id) REFERENCES broker (id),
    CONSTRAINT FK_broker_sales_group_sales_group FOREIGN KEY (sales_group_id) REFERENCES sales_group (id)
);