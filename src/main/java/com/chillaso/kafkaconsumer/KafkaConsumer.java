package com.chillaso.kafkaconsumer;

import com.chillaso.kafkaconsumer.model.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
@Slf4j
public class KafkaConsumer {

    /**
     * Consumer function will be exposed to Spring context as a message stream listener
     * in this case, for kafka due we have kafka binder in the configuration.
     *
     * We accept a list of {@link Todo} objects cause we want to process in batch mode. See configuration.
     */
    @Bean
    private Consumer<List<Todo>> process() {
        return todoList -> {
            todoList.forEach(todo -> {
                todo.setTask(todo.getTask().toUpperCase());
                log.info(todo.toString());
            });
        };
    }
}
