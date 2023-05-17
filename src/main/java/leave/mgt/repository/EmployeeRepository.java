package leave.mgt.repository;

import leave.mgt.model.Employee;
import leave.mgt.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee findEmployeeByUser(Users theUser);
}
