package com.person.Manager.Repository;

import com.person.Manager.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    void deleteAllByPersonId(Long personId);
}
