create table COURIER
(
    idcourier bigserial not null,
    NAME varchar(255) not null,
    PRIMARY KEY (idcourier)
);


create table COURIER_STORE_LOG
(
    IDCS bigserial not null,
    STORE_NAME varchar(255),
    COURIER_ID integer,
    LATITUDE NUMBER(20),
    LONGITUDE NUMBER(20),
    CREATE_DATE TIMESTAMP,
    PRIMARY KEY (IDCS)


);


create table STORE
(
    storeId bigserial not null,
    NAME varchar(255),
    LATITUDE NUMBER(20),
    LONGITUDE NUMBER(20),
    PRIMARY KEY (storeId)


);