package com.person.Manager.Service;

import com.person.Manager.Entity.Person;
import com.person.Manager.Repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressService addressService;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findByName(String name) throws Exception {
        Person person = personRepository.findByName(name);
        if (person == null) {
            throw new Exception();
        }
        return person;
    }

    public Person insertPeople(Person person) {
        return personRepository.save(person);
    }

    @Transactional
    public Person updatePeople(Person newPerson, String name) throws Exception {
        Person person = findByName(name);

        if (newPerson.getName() != null) {
            person.setName(newPerson.getName());
        }
        if (newPerson.getBirthDate() != null) {
            person.setBirthDate(newPerson.getBirthDate());
        }
        if (newPerson.getAddress() != null) {
            addressService.deleteAddresses(person.getId());
            person.setAddress(newPerson.getAddress());
        }
        return personRepository.save(person);
    }

}