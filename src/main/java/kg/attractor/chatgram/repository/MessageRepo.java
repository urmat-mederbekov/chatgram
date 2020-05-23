package kg.attractor.chatgram.repository;

import kg.attractor.chatgram.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepo extends JpaRepository<Message, Integer> {

    @Query(value = "SELECT * FROM messages ORDER BY date_time DESC", nativeQuery = true)
    Page<Message> findAll(Pageable pageable);
}
