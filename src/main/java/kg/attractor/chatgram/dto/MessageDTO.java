package kg.attractor.chatgram.dto;

import kg.attractor.chatgram.model.Message;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class MessageDTO {

    public static MessageDTO from(Message message){
        return MessageDTO.builder()
                .id(message.getId())
                .text(message.getText())
                .dateTime(message.getDateTime())
                .author(UserDTO.from(message.getAuthor()))
                .build();
    }
    private int id;
    private String text;
    private LocalDateTime dateTime;
    private UserDTO author;
}
