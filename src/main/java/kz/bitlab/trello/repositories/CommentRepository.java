package kz.bitlab.trello.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.trello.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query("select i from Comment i where i.task.id = :id")
    List<Comment> getCommentsByTaskId(Long id);

    @Transactional
    @Modifying
    @Query("delete from Comment i where i.task.id = :id")
    void deleteCommentByTaskId(Long id);
}
