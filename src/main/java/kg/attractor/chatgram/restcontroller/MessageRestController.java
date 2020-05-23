package kg.attractor.chatgram.restcontroller;

import kg.attractor.chatgram.dto.MessageDTO;
import kg.attractor.chatgram.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@AllArgsConstructor
public class MessageRestController {

    private final MessageService messageService;

    @GetMapping
    public List<MessageDTO> findComments(Pageable pageable){
        return messageService.getAll(pageable).getContent();
    }

    @PostMapping
    public void message(@RequestBody MessageDTO messageDTO,
                              Authentication authentication) {
        String authenticationName = authentication.getName();
        messageService.message(messageDTO, authenticationName);
    }
}
