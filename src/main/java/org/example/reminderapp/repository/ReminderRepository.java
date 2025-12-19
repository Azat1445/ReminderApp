package org.example.reminderapp.repository;

import org.example.reminderapp.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReminderRepository extends JpaRepository<Reminder, Long>, JpaSpecificationExecutor<Reminder> {
}
