package de.bag.mongotest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.awaitility.Awaitility.await;

@SpringBootTest
@Slf4j
class MongotestApplicationTests {

    @Autowired
    private ExampleMongoService service;

    @Test
    void contextLoads() {}

    @Test
    void testChangeStream() {
        ExampleDAO obj = new ExampleDAO();
        obj.setData("test data");

        service.saveExampleObject(obj);
        AtomicInteger counter = new AtomicInteger();
        service.subscribeToChangeStream().subscribe(changeEvent -> {
            log.info("Received change event: " + changeEvent);
            counter.addAndGet(1);
        });
        IntStream.range(0, 3).forEach(i -> {
            ExampleDAO obj2 = new ExampleDAO();
            obj.setData("test data" + i);
            service.saveExampleObject(obj);
        });

        service.saveExampleObject(obj);

        await().atMost(5, TimeUnit.SECONDS).until(() -> counter.get() >= 3);
        // Due to the nature of the reactive streams, this test might need additional logic to properly assert changes.
    }
}
