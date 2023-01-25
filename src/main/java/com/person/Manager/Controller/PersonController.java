package com.person.Manager.Controller;

import com.person.Manager.Entity.Person;
import com.person.Manager.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(value = "/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        return ResponseEntity.ok().body(personService.findAll());
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<Person> findByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok().body(personService.findByName(name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Person> insertPeople(@RequestBody Person person){
        person = personService.insertPeople(person);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(person);
    }

    @PutMapping(value = "/{name}")
    public ResponseEntity<Person> updatePeople(@RequestBody Person person, @PathVariable String name) throws Exception {
        person = personService.updatePeople(person, name);
        return ResponseEntity.ok().body(person);
    }
}
