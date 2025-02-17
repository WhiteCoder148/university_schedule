package com.brimstone.university_schedule.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brimstone.university_schedule.model.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
	
	@Query(value = "from Lesson l where l.date >= :since and l.date <= :to")
	List<Lesson> findByPeriod(LocalDate since, LocalDate to);
}
