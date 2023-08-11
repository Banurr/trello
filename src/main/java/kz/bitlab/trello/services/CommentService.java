package kz.bitlab.trello.services;

import kz.bitlab.trello.models.Comment;
import kz.bitlab.trello.models.Task;
import kz.bitlab.trello.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskService taskService;

    public List<Comment> allComments(){
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id){
        return commentRepository.findById(id).orElse(null);
    }

    public void addComment(Long id,Comment comment){
        Task task = taskService.getTaskById(id);
        comment.setTask(task);
        commentRepository.save(comment);
    }
}
