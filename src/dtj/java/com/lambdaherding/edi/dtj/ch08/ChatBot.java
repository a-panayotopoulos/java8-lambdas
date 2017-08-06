package com.lambdaherding.edi.dtj.ch08;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChatBot {

    private String welcomeMessage;
    private String terminalExpression;
    private String shutDownResponse;
    private String unknownMessageResponse;

    private Map<String, InteractiveResponseBuilder> messageMappings = new HashMap<>();
    private BufferedReader reader;

    public ChatBot() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void welcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public void terminalExpression(String terminalExpression) {
        this.terminalExpression = terminalExpression;
    }

    public void shutDownResponse(String shutDownResponse) {
        this.shutDownResponse = shutDownResponse;
    }

    public void onUnknownMessage(String unknownMessageResponse) {
        this.unknownMessageResponse = unknownMessageResponse;
    }

    public InteractiveResponseBuilder whenMessageContains(String messageContents) {
        InteractiveResponseBuilder responseBuilder = new InteractiveResponseBuilder();
        messageMappings.put(messageContents, responseBuilder);
        return responseBuilder;
    }

    private void respond(String message) {
        System.out.println(message);
    }

    private String readInput() {
        try {
            return reader.readLine();
        } catch ( IOException e ) {
            return "";
        }
    }

    public void run()  {
        if (welcomeMessage != null) {
            System.out.println(welcomeMessage);
        }

        if (terminalExpression == null) {
            System.err.println("No terminal expression has been defined, you'll have to force exit");
        } else {
            System.out.println("You can exit by saying: " + terminalExpression);
        }

        // Main logic loop
        while (true) {
            String input = readInput();
            if (terminalExpression != null && input.contains(terminalExpression)) {
                System.out.println(shutDownResponse);
                break;
            }

            Optional<String> foundMessage = messageMappings.keySet().stream().filter(input::contains).findFirst();

            if (foundMessage.isPresent()) {
                System.out.println(messageMappings.get(foundMessage.get()).build(this::respond, this::readInput));
            } else {
                String noMappingResponse = this.unknownMessageResponse == null
                        ? "Don't know how to process such a message"
                        : this.unknownMessageResponse;
                System.out.println(noMappingResponse);
            }
        }


    }

}
