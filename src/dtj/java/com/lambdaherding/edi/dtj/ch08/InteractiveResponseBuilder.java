package com.lambdaherding.edi.dtj.ch08;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class InteractiveResponseBuilder{
    private BiFunction<Consumer<String>, Supplier<String>, String> function;

    public static BiFunction<Consumer<String>, Supplier<String>, String> reply( String message ) {
        return (unused, ignored) -> message;
    }

    public static Question ask( String question ) {
        return new Question(question);
    }

    public static class Question {
        private String question;
        public Question( String question ) {
            this.question = question;
        }

        public BiFunction<Consumer<String>, Supplier<String>, String> withAnswer( String response ) {
            return (cb, sp) -> {
                cb.accept(question);
                return response.replace("{}", sp.get());
            };
        }
    }

    public InteractiveResponseBuilder then(BiFunction<Consumer<String>, Supplier<String>, String> builder) {
        this.function = builder;
        return this;
    }

    public String build(Consumer<String> callback, Supplier<String> supplier) {
        return this.function.apply(callback, supplier);
    }


}
