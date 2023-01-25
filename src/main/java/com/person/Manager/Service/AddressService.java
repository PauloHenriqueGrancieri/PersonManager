package com.person.Manager.Service;

import com.person.Manager.Entity.Address;
import com.person.Manager.Entity.Person;
import com.person.Manager.Repository.AddressRepository;
import com.person.Manager.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    PersonRepository personRepository;

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public List<Address> findByName(String name) throws Exception {
        Person person = personRepository.findByName(name);
        if (person == null) {
            throw new Exception();
        }
        return person.getAddress();
    }

    public List<Address> insertAddress(List<Address> addressList, String name) {
        Person person = personRepository.findByName(name);
        List<Address> newAddressList = person.getAddress();
        newAddressList.addAll(addressList);

        addressRepository.saveAll(newAddressList);
        personRepository.save(person);

        return addressList;
    }

    public void deleteAddresses(Long peopleId) {
        addressRepository.deleteAllByPersonId(peopleId);
    }

}
