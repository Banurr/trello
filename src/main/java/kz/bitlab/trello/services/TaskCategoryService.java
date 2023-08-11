package kz.bitlab.trello.services;

import kz.bitlab.trello.models.Folder;
import kz.bitlab.trello.models.TaskCategory;
import kz.bitlab.trello.repositories.TaskCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCategoryService {

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    @Autowired
    private FolderService folderService;

    public List<TaskCategory> allCategories(){
        return taskCategoryRepository.findAll();
    }

    public TaskCategory getCategoryById(Long id){
        return taskCategoryRepository.findById(id).orElse(null);
    }

    public void addCategory(TaskCategory taskCategory)
    {
        taskCategoryRepository.save(taskCategory);
    }

    public void deleteCategory(Long id){
        List<Folder> folders = folderService.allFolders();
        for(Folder i : folders){
            folderService.removeCategory(i.getId(),id);
        }
        taskCategoryRepository.deleteById(id);
    }
    public void updateCategory(TaskCategory taskCategory){
        taskCategoryRepository.save(taskCategory);
    }
}
