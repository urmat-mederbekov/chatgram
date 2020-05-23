package kg.attractor.chatgram.service;

import kg.attractor.chatgram.dto.MessageDTO;
import kg.attractor.chatgram.model.Message;
import kg.attractor.chatgram.model.User;
import kg.attractor.chatgram.repository.MessageRepo;
import kg.attractor.chatgram.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepo messageRepo;
    private final UserRepo userRepo;

    public Page<MessageDTO> getAll(Pageable pageable){
        Page<Message> messages = messageRepo.findAll(pageable);
        return messages.map(MessageDTO::from);
    }

    public void message(MessageDTO messageDTO, String email) {

        User user = userRepo.findByEmail(email).get();
//                .orElseThrow(() -> new ResourceNotFoundException("Can't find user with the name: " + commenterUsername));

        Message message = Message.builder()
                .text(messageDTO.getText())
                .dateTime(LocalDateTime.now())
                .author(user)
                .build();

        messageRepo.save(message);
    }
}
