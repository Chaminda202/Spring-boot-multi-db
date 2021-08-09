package com.spring.multi.db.service.distributed.impl;

import com.spring.multi.db.model.base.Employee;
import com.spring.multi.db.model.thirdparty.User;
import com.spring.multi.db.repository.base.EmployeeRepository;
import com.spring.multi.db.repository.thirdparty.UserRepository;
import com.spring.multi.db.service.distributed.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional(value = "chainedTransactionManager")
    // @Transactional
    public User updateUserAndEmployee(User user) {
        Optional<User> optionalUser = this.userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            final User updateUser = this.userRepository.save(user);

            Optional<Employee> optionalEmployee = this.employeeRepository.findById(user.getId());
            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                employee.setAge(user.getAge());
                employee.setSalary(user.getSalary());
                this.employeeRepository.save(employee);
            } else {
                throw new RuntimeException("Employee does not exist in the DB");
            }
            return updateUser;
        }
        throw new RuntimeException("User does not exist in the DB");
    }
}
