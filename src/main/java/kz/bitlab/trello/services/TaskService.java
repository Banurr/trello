package kz.bitlab.trello.services;

import kz.bitlab.trello.models.Comment;
import kz.bitlab.trello.models.Folder;
import kz.bitlab.trello.models.Task;
import kz.bitlab.trello.repositories.CommentRepository;
import kz.bitlab.trello.repositories.FolderRepository;
import kz.bitlab.trello.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private FolderService folderService;

    @Autowired
    private CommentRepository commentRepository;

    public List<Task> allTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id){
        return taskRepository.findById(id).orElse(null);
    }

    public void addTask(Long id,Task task){
        Folder folder = folderService.getFolderById(id);
        task.setFolder(folder);
        taskRepository.save(task);
    }

    public void deleteTaskByFolderId(Long id){
        taskRepository.deleteTaskByFolderId(id);
    }

    public void deleteTaskById(Long id){
        deleteCommentByTaskId(id);
        taskRepository.deleteById(id);

    }

    public void deleteCommentByTaskId(Long id){
        commentRepository.deleteCommentByTaskId(id);
    }

    public void editTask(Task task){
        taskRepository.save(task);
    }
    public List<Comment> getCommentsByTaskId(Long id){ return commentRepository.getCommentsByTaskId(id);}
}
