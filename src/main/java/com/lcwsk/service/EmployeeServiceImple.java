package com.lcwsk.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcwsk.enities.Employee;
import com.lcwsk.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImple implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String EMPLOYEE_CACHE_KEY = "EMPLOYEE";

    @Cacheable(value = EMPLOYEE_CACHE_KEY)
    public List<Employee> getAllEmployee() {
        // Check if data exists in Redis
        Object cacheResult = redisTemplate.opsForValue().get(EMPLOYEE_CACHE_KEY);
        if (cacheResult != null) {
            return convertCacheResult(cacheResult);
        }

        // Fetch from DB if cache is empty
        List<Employee> employees = repository.findAll();
        redisTemplate.opsForValue().set(EMPLOYEE_CACHE_KEY, employees); // Store in cache
        return employees;
    }

    private List<Employee> convertCacheResult(Object cacheResult) {
        if (cacheResult instanceof List<?>) {
            return ((List<?>) cacheResult).stream()
                    .map(obj -> objectMapper.convertValue(obj, Employee.class))
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    @CachePut(value = "EMPLOYEE", key = "#employee.id")
    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    @Cacheable(value = "EMPLOYEE", key = "#id")
    public Employee getEmployeeById(Long id){
        return repository.findById(id)
                .orElseThrow(()->new RuntimeException("Employee not found with id: " + id));
    }

    @Override
    @CachePut(value = "EMPLOYEE", key = "#id")
    public Employee updateEmployee(Long id, Employee employee) {
        Employee employee1 =  getEmployeeById(id);
        employee1.setName(employee.getName());
        employee1.setSalary(employee.getSalary());
        return repository.save(employee1);
    }

    @Override
    @CacheEvict(value = "EMPLOYEE", key = "#id")
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
