package kg.attractor.chatgram.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class CustomerAlreadyRegisteredException extends RuntimeException { }
