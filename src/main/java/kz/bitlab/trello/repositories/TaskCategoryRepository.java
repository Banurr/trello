package kz.bitlab.trello.repositories;

import kz.bitlab.trello.models.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategory,Long> {
}
