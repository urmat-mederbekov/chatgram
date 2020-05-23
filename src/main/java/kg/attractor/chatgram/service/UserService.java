package kg.attractor.chatgram.service;

import kg.attractor.chatgram.dto.UserDTO;
import kg.attractor.chatgram.exception.CustomerAlreadyRegisteredException;
import kg.attractor.chatgram.model.CustomerRegisterForm;
import kg.attractor.chatgram.model.User;
import kg.attractor.chatgram.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    public void register(CustomerRegisterForm form) {
        if (userRepo.existsByEmail(form.getEmail())) {
            throw new CustomerAlreadyRegisteredException();
        }

        var user = User.builder()
                .email(form.getEmail())
                .username(form.getName())
                .password(encoder.encode(form.getPassword()))
                .build();

        userRepo.save(user);
    }

    public UserDTO getByEmail(String email) {

        var user = userRepo.findByEmail(email).get();
//                .orElseThrow(CustomerNotFoundException::new);

        return UserDTO.from(user);
    }
}
