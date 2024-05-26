package com.example.kms;

import com.example.kms.entity.Employee;
import com.example.kms.entity.EmployeeStatus;
import com.example.kms.entity.EmployeeType;
import com.example.kms.entity.Watch;
import com.example.kms.repository.EmployeeRepository;
import com.example.kms.repository.WatchRepository;
import com.example.kms.service.EmployeeService;
import com.example.kms.service.WatchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class KmsApplicationTests {

    @Autowired
    private EmployeeService employeeService;
    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetEmployeeById() {
        Employee mockEmployee = new Employee(1, "f",
                "s", "m", null,
                "{employee_id = 1}", EmployeeType.TEACHER,
                EmployeeStatus.WORKS, null, null);
        Mockito.when(employeeRepository.findById(mockEmployee.getEmployeeId()))
                .thenReturn(Optional.of(mockEmployee));
        Employee result = employeeService.getEmployeeById(mockEmployee.getEmployeeId());

        assertNotNull(result);
        assertEquals(1, result.getEmployeeId());
        assertEquals("f", result.getFirstName());
        assertEquals("s", result.getSecondName());
        assertEquals("m", result.getMiddleName());
        assertNull(result.getImage());
        assertEquals("{employee_id = 1}", result.getQR());
        assertEquals(EmployeeType.TEACHER, result.getEmployeeType());
        assertEquals(EmployeeStatus.WORKS, result.getEmployeeStatus());
        assertNull(result.getPermissions());
        assertNull(result.getDivisions());
    }

    @Autowired
    private WatchService watchService;
    @MockBean
    public WatchRepository watchRepository;

    @Test
    public void testGetWatchById() {
        Watch watch = new Watch(1,"150");
        Mockito.when(watchRepository.findById(watch.getWatchId())).thenReturn(Optional.of(watch));
        Watch result = watchService.getWatchById(watch.getWatchId());
        assertEquals(watch.getWatchId(), result.getWatchId());
        assertEquals(watch.getBuildingNumber(), result.getBuildingNumber());
    }

   /* @Test
    void contextLoads() {
    }*/

}
