package kz.bitlab.trello.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.trello.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("select i from Task i where i.folder.id = :id")
    List<Task> getTasksByFolderId(Long id);

    @Transactional
    @Modifying
    @Query("delete from Task i where i.folder.id = :id")
    void deleteTaskByFolderId(Long id);
}
