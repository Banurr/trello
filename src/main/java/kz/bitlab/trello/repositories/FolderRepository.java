package kz.bitlab.trello.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.trello.models.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder,Long> {

}
