package com.example.kms.service;

import com.example.kms.entity.Shift;
import com.example.kms.entity.User;
import com.example.kms.entity.Watch;
import com.example.kms.repository.ShiftRepository;
import com.example.kms.repository.UserRepository;
import com.example.kms.repository.WatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftService {
    private final ShiftRepository shiftRepository;
    private final UserRepository userRepository;
    private final WatchRepository watchRepository;

    public Shift createShift(Integer userId, Integer watchId) {
        User watchman = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Not found user with id = " + userId));
        Watch watch = watchRepository.findById(watchId)
                .orElseThrow(()-> new RuntimeException("Not found watch with id = " + watchId));
        return shiftRepository.save(new Shift(watchman, watch, new Timestamp(System.currentTimeMillis())));
    }

    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }

    public Shift getShiftById(Integer id){
        return shiftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Shift with id = " + id));
    }

    public Shift endShift(Integer id) {
        Shift shift = shiftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Shift with id = " + id));
        if (shift.getEnd_date_time() != null) throw new RuntimeException("Shift with id " + id + " has already ended");
        shift.setEnd_date_time(new Timestamp(System.currentTimeMillis()));
        return shiftRepository.save(shift);
    }

    public Shift getLastShiftByUserId(Integer userId)  {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Not found user with id = " + userId));
        List<Shift> shifts = shiftRepository.findByWatchman(user);
        Shift shift = new Shift();
        Integer id = 0;
        for (Shift _shift : shifts){
            if (_shift.getShift_id() > id) {
                shift = _shift;
            }
        }
        return shift;
    }

}
