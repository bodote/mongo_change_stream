package de.bag.mongotest;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoDBRepo extends ReactiveMongoRepository<ExampleDAO, String> {}
