package com.example.lab5part3.Controller;

import com.example.lab5part3.Api.ApiResponse;
import com.example.lab5part3.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    ArrayList<Event> events = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Event> getEvents(){
        return events;
    }

    @GetMapping("/get/{id}")
    public Event getEventById(@PathVariable String id){
        return events.get(Integer.parseInt(id));
    }

    @PostMapping("/add")
    public ApiResponse addEvent(@RequestBody Event event) {
        events.add(event);
        return new ApiResponse("Event added successfully");
    }

    @PutMapping("/update")
    public ApiResponse updateEvent (@RequestBody Event event){
        events.set(Integer.parseInt(event.getId()),event);
        return new ApiResponse("Event updated Successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteEvent(@PathVariable String id){
        events.remove(Integer.parseInt(id));
        return new ApiResponse("Event deleted Successfully");
    }

}
