package br.com.hackathon.planonegocio.shared.businessrule.usercase;

import org.springframework.stereotype.Service;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;

import br.com.hackathon.planonegocio.shared.businessrule.ChatGptGenerate;

@Service
public class ChatGptGenerateUC implements ChatGptGenerate {

    private final OpenAiService openAiService;

    public ChatGptGenerateUC(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @Override
    public String generateTextGpt(String message) {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(message)
                .maxTokens(420)
                .build();

        return openAiService.createCompletion(completionRequest).getChoices().get(0).getText();
    }
}
