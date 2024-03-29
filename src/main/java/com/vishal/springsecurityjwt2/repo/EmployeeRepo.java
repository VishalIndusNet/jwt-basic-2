package com.vishal.springsecurityjwt2.repo;

import com.vishal.springsecurityjwt2.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {

         Optional<Employee> findByEmail(String email);
}
