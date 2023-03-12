CREATE TABLE users (
    id BIGINT NOT NULL,
    first_name VARCHAR(255) NOT NULL ,
    last_name VARCHAR(255) NOT NULL ,
    email VARCHAR(255) NOT NULL ,
    birthday DATE NOT NULL,
    CONSTRAINT users_PK PRIMARY KEY (id),
    CONSTRAINT users_email_AK UNIQUE (email)
);

create table profiles (
    user_id BIGINT NOT NULL,
    city VARCHAR(255) NOT NULL ,
    job_position VARCHAR(255) NOT NULL ,
    company VARCHAR(255) NOT NULL,
    education VARCHAR(255) NOT NULL,
    CONSTRAINT profiles_PK PRIMARY KEY (user_id),
);

ALTER TABLE profiles
ADD CONSTRAINT profiles_users_FK FOREIGN KEY (user_id) REFERENCES users (id)