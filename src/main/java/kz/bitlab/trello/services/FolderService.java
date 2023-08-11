package kz.bitlab.trello.services;

import groovy.lang.Category;
import kz.bitlab.trello.models.Comment;
import kz.bitlab.trello.models.Folder;
import kz.bitlab.trello.models.Task;
import kz.bitlab.trello.models.TaskCategory;
import kz.bitlab.trello.repositories.CommentRepository;
import kz.bitlab.trello.repositories.FolderRepository;
import kz.bitlab.trello.repositories.TaskCategoryRepository;
import kz.bitlab.trello.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class FolderService {

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    @Autowired
    private TaskService taskService;

    public List<Folder> allFolders(){
        return folderRepository.findAll();
    }

    public Folder getFolderById(Long id){
        return folderRepository.findById(id).orElse(null);
    }

    public void addFolder(Folder folder){
        folderRepository.save(folder);
    }

    public void removeCategory(Long id,Long cat_id){
        Folder folder = folderRepository.findById(id).orElse(null);
        TaskCategory taskCategory = taskCategoryRepository.findById(cat_id).orElse(null);
        assert folder != null;
        Set<TaskCategory> set = folder.getCategories();
        set.remove(taskCategory);
        folder.setCategories(set);
        folderRepository.save(folder);
    }
    public void addCategory(Long id,Long cat_id){
        Folder folder = folderRepository.findById(id).orElse(null);
        TaskCategory taskCategory = taskCategoryRepository.findById(cat_id).orElse(null);
        assert folder != null;
        Set<TaskCategory> set = folder.getCategories();
        set.add(taskCategory);
        folder.setCategories(set);
        folderRepository.save(folder);
    }

    public List<Task> allTasksFromFolder(Long id)
    {
        return taskRepository.getTasksByFolderId(id);
    }

    public void deleteFolder(Long id){
        List<Task> list = allTasksFromFolder(id);
        for(Task i : list){
            taskService.deleteCommentByTaskId(i.getId());
        }
        taskService.deleteTaskByFolderId(id);
        folderRepository.deleteById(id);
    }

    public void editFolder(Folder folder){
        folderRepository.save(folder);
    }
}
