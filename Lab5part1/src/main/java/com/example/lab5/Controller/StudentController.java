package com.example.lab5.Controller;

import com.example.lab5.Api.ApiResponse;
import com.example.lab5.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    ArrayList<Student> students = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Student> getStudents() {
        return students;
    }

    @GetMapping("/get-above-avg")
    public ArrayList<Student> getAboveAvg(){
        ArrayList<Student> aboveAvg = new ArrayList<>();
        double sum = 0;

        for (Student student : students)
            sum += student.getGpa();

        double average = sum / students.size();

        for (Student student : students)
            if (student.getGpa() >= average)
                aboveAvg.add(student);

        return aboveAvg;

    }

    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student){
        students.add(student);
        return new ApiResponse("Student added successfully");
    }

    @PutMapping("/update")
    public ApiResponse updateStudent(@RequestBody Student student){
        for (Student s : students)
            if (s.getId().equals(student.getId())) {
                students.set(Integer.parseInt(student.getId()), student);
                return new ApiResponse("Student data Updated successfully");
            }

        return new ApiResponse("Student was Not found!");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteStudent(@PathVariable String id){
        students.remove(Integer.parseInt(id));
        return new ApiResponse("Student was removed");
    }

    @GetMapping("classify")
    public ArrayList<String[]> classify(){
        ArrayList<String[]> categorizedStudents = new ArrayList<>();
        for (Student student : students){
            if (student.getGpa() >= 4.75) {
                categorizedStudents.add(new String[]{
                        student.getId(),
                        student.getName(),
                        Double.toString(student.getGpa()),
                        "First-Class Honors"});
            }
            else if (student.getGpa() >= 4.25) {
                categorizedStudents.add(new String[]{
                        student.getId(),
                        student.getName(),
                        Double.toString(student.getGpa()),
                        "Second-Class Honors"});
            }
            else if (student.getGpa() >= 3.5) {
                categorizedStudents.add(new String[]{
                        student.getId(),
                        student.getName(),
                        Double.toString(student.getGpa()),
                        "Third-Class Honors"});
            } else {
                categorizedStudents.add(new String[]{
                        student.getId(),
                        student.getName(),
                        Double.toString(student.getGpa()),
                        "Ordinary"});
            }
        }
        return categorizedStudents;
    }
}
