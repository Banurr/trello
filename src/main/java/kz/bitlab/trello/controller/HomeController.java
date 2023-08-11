package kz.bitlab.trello.controller;


import kz.bitlab.trello.models.Comment;
import kz.bitlab.trello.models.Folder;
import kz.bitlab.trello.models.Task;
import kz.bitlab.trello.models.TaskCategory;
import kz.bitlab.trello.repositories.TaskCategoryRepository;
import kz.bitlab.trello.services.CommentService;
import kz.bitlab.trello.services.FolderService;
import kz.bitlab.trello.services.TaskCategoryService;
import kz.bitlab.trello.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private FolderService folderService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskCategoryService taskCategoryService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/allfolders/")
    public String allFolders(Model model){
        model.addAttribute("folders",folderService.allFolders());
        return "all_folders";
    }

    @PostMapping("/addFolder")
    public String addFolder(Folder folder){
        folderService.addFolder(folder);
        return "redirect:/allfolders/";
    }

    @GetMapping("/folder/{id}")
    public String detailsFolder(@PathVariable(name = "id") Long id,
                                Model model){
        model.addAttribute("folder",folderService.getFolderById(id));
        model.addAttribute("tasks",folderService.allTasksFromFolder(id));
        model.addAttribute("categories",taskCategoryService.allCategories());
        return "folder_details";
    }

    @PostMapping("/addTask/{id}")
    public String addTask(@PathVariable(name = "id") Long id,
                          Task task
                          ){
        taskService.addTask(id,task);
        return "redirect:/folder/"+id;
    }

    @PostMapping("/removeCategory/{id}")
    public String removeCategory(@PathVariable(name = "id") Long id,
                                 @RequestParam(name = "category") Long cat_id){
        folderService.removeCategory(id,cat_id);
        return "redirect:/folder/"+id;
    }

    @PostMapping("/addCategory/{id}")
    public String addCategory(@PathVariable(name = "id") Long id,
                              @RequestParam(name = "category") Long cat_id){
        folderService.addCategory(id,cat_id);
        return "redirect:/folder/"+id;
    }

    @PostMapping("/deleteFolder/{id}")
    public String deleteFolder(@PathVariable(name = "id") Long id){
        folderService.deleteFolder(id);
        return "redirect:/allfolders/";
    }

    @PostMapping("/editFolder/{id}")
    public String editFolder(@PathVariable(name = "id") Long id,Folder folder){
        folderService.editFolder(folder);
        return "redirect:/folder/"+id;
    }

    @GetMapping("/task/{id}")
    public String detailsTask(@PathVariable(name = "id") Long id,
                              Model model){
        model.addAttribute("task",taskService.getTaskById(id));
        model.addAttribute("comments",taskService.getCommentsByTaskId(id));
        return "task_details";
    }

    @PostMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable(name = "id") Long id){
        Task task = taskService.getTaskById(id);
        taskService.deleteTaskById(id);
        return "redirect:/folder/"+task.getFolder().getId();
    }

    @PostMapping("/editTask/{id}")
    public String editTask(@PathVariable(name = "id") Long id,
                           Task task){
        Task current_task = taskService.getTaskById(id);
        taskService.editTask(task);
        return "redirect:/folder/"+current_task.getFolder().getId();
    }

    @PostMapping("/addComment/{id}")
    public String addComment(@PathVariable(name = "id") Long id,
                             @RequestParam(name = "comment") String comment){
        Comment comment1 = new Comment();
        comment1.setComment(comment);
        commentService.addComment(id,comment1);
        return "redirect:/task/"+id;
    }

    @GetMapping("/allcategories/")
    public String allCategories(Model model){
        model.addAttribute("categories",taskCategoryService.allCategories());
        return "all_categories";
    }

    @PostMapping("/createCategory")
    public String createCategory(TaskCategory category){
        System.out.println("zashel");
        taskCategoryService.addCategory(category);
        return "redirect:/allcategories/";
    }

    @PostMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable(name = "id") Long id){
        taskCategoryService.deleteCategory(id);
        return "redirect:/allcategories/";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(TaskCategory taskCategory){
        System.out.println("updated");
        System.out.println(taskCategory.getName());
        taskCategoryService.updateCategory(taskCategory);
        return "redirect:/allcategories/";
    }
}
