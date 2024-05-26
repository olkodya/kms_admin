package com.example.kms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Entity(name = "shifts")
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shift_id;
    private Timestamp start_date_time;
    private Timestamp end_date_time;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "watch_id", nullable=false)

    private Watch watch;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable=false)
    private User watchman;


    public Shift(User watchman, Watch watch, Timestamp start_date_time) {
        this.watchman = watchman;
        this.watch = watch;
        this.start_date_time = start_date_time;
    }

    public Watch getWatch() {
        return watch;
    }

    public void setWatch(Watch watch) {
        this.watch = watch;
    }

    public User getWatchman() {
        return watchman;
    }

    public void setWatchman(User watchman) {
        this.watchman = watchman;
    }

    public Timestamp getStart_date_time() {
        return start_date_time;
    }

    public void setStart_date_time(Timestamp start_date_time) {
        this.start_date_time = start_date_time;
    }

    public Timestamp getEnd_date_time() {
        return end_date_time;
    }

    public void setEnd_date_time(Timestamp end_date_time) {
        this.end_date_time = end_date_time;
    }

    public Integer getShift_id() {
        return shift_id;
    }
}
