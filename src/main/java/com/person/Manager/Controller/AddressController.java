package com.person.Manager.Controller;

import com.person.Manager.Entity.Address;
import com.person.Manager.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> findAll() {
        return ResponseEntity.ok().body(addressService.findAll());
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<List<Address>> findByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok().body(addressService.findByName(name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/{name}")
    public ResponseEntity<List<Address>> insertAddress(@RequestBody List<Address> address, @PathVariable String name) {
        address = addressService.insertAddress(address, name);
        return ResponseEntity.ok().body(address);
    }

}
