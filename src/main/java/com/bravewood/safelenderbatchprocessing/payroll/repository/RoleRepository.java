package com.bravewood.safelenderbatchprocessing.payroll.repository;


import com.bravewood.safelenderbatchprocessing.payroll.domain.Role;
import com.bravewood.safelenderbatchprocessing.payroll.request.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
