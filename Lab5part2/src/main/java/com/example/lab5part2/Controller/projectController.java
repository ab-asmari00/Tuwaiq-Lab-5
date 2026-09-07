package com.example.lab5part2.Controller;

import com.example.lab5part2.Api.ApiResponse;
import com.example.lab5part2.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class projectController {

    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Project> getProjects(){
        return projects;
    }

    @GetMapping("/search/{title}")
    public Project getProjectByTitle(@PathVariable String title){
        for (Project project : projects)
            if (project.getTitle().equals(title))
                return project;
        return null;
    }

    @GetMapping("/get-company-projects/{companyName}")
    public ArrayList<Project> getCompanyProjects(@PathVariable String companyName){
        ArrayList<Project> companyProjects = new ArrayList<>();
        for (Project project : projects)
            if (project.getCompanyName().equals(companyName))
                companyProjects.add(project);

        return companyProjects;
    }

    @PostMapping("/add")
    public ApiResponse addProject(@RequestBody Project project){
        projects.add(project);
        return new ApiResponse("Project added successfully");
    }

    @PutMapping("/update")
    public ApiResponse updateProject(@RequestBody Project project){
        projects.set(projects.indexOf(project),project);
        return new ApiResponse("Project Updated");
    }

    @PutMapping("/change-status/{id}/{status}")
    public ApiResponse updateStatus(@PathVariable String id,@PathVariable String status){
        projects.get(Integer.parseInt(id)).setStatus(status);
        return  new ApiResponse("Project status updated");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteProject(@PathVariable String id){
        projects.remove(Integer.parseInt(id));
        return new ApiResponse("Project deleted");
    }


}
