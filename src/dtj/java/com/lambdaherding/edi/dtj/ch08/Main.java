package com.lambdaherding.edi.dtj.ch08;

import java.io.IOException;

import static com.lambdaherding.edi.dtj.ch08.InteractiveResponseBuilder.ask;
import static com.lambdaherding.edi.dtj.ch08.InteractiveResponseBuilder.reply;

public class Main {

    public static void main(String[] args) throws IOException {
        ChatBot dtjBot = new ChatBot();

        dtjBot.welcomeMessage("I seem to have been rewritten in Java");
        dtjBot.onUnknownMessage("Ask red-bot, it can normally help with that sort of thing");
        dtjBot.terminalExpression("There's food in the kitchen");
        dtjBot.shutDownResponse("Gotta get there fast");

        dtjBot.whenMessageContains("Hello").then(reply("Hi!"));
        dtjBot.whenMessageContains("I want a doughnut please")
                .then(ask("What flavour would you like?")
                        .withAnswer("Okay I'll get you a {} doughnut"));

        dtjBot.run();
    }
}
