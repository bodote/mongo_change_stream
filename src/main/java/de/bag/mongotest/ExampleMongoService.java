package de.bag.mongotest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ChangeStreamEvent;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ExampleMongoService {
    private static final Logger logger = LoggerFactory.getLogger(ExampleMongoService.class);

    @Autowired
    private MongoDBRepo repository;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    public void saveExampleObject(ExampleDAO obj) {
        repository.save(obj).subscribe();
    }

    public Flux<ChangeStreamEvent<ExampleDAO>> subscribeToChangeStream() {
        return mongoTemplate.changeStream(ExampleDAO.class).listen();
    }
}
