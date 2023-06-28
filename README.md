# Courier-Store-Tracking-System


## About The Project

The project has two API: 
1) One of these API(courierStore/eventReport) gets courier location via Mobile application about 10second. And send event to queue to log for logging appropriate data. if this location is 100m around the store, this courier location is logged with store info.
2) Second API gets courierId and calculates total distance between the stores he visited 

## Redis

The project has cache mechanism(Redis)
The courier info is added to redis and it stay about 1minute. (Log courier and store when any courier enters radius of 100 meters from stores. Reentries to the same store's circumference over 1 minute should not count as "entrance".)

# Kafka

The project has producer, consumer
1) producer send event to kafka for to logging appropriate data.
2) consumer checks event. First of all, it is checked whether the courier is around the store. if courier is around the store, it is checked whether the courier is in redis.  
if courier is not in Redis so the courier info is logging with store info. otherwise(this means that the courier is in the same place for 1 minute.) no logging

## Technologies

* Java17
* Spring cloud(Spring webflux/Reactive API)
* Spring Boot
* Maven
* Reactive Relational Database Connectivity (H2)
* Redis
* Kafka
* lombok

## Design Pattern

* Builder Design Pattern
* Association/Junction Table Design Pattern
* Annotation-Based Functional Interfaces
* Singleton Pattern via Spring annotations. (@Service, @Component)
* Immutable Data Class Pattern (via 'Record Class') 

## Installation
1) [Clone Repo ](https://github.com/Nesrinasan/courier-store-tracking-system)


2) Run Docker-Compose file
```programlama_dili
docker-compose up
```
3) Run application

```programlama_dili
./mvnw spring-boot:run
```
