package com.foxminded.university_schedule.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.foxminded.university_schedule.model.Group;
import com.foxminded.university_schedule.model.Lesson;
import com.foxminded.university_schedule.model.Teacher;
import com.foxminded.university_schedule.repository.LessonRepository;

import jakarta.transaction.Transactional;

@Service
public class LessonService extends BaseService {

	private LessonRepository lessonRepository;

	private Logger logger = LoggerFactory.getLogger(LessonService.class);

	public LessonService(LessonRepository lessonRepository) {
		this.lessonRepository = lessonRepository;
	}

	@Transactional
	public void save(Lesson lesson) {
		lessonRepository.save(lesson);
		logger.info("Saved lesson by course: {}", lesson.getCourse());
	}

	public Optional<Lesson> getById(Integer lessonId) {
		return lessonRepository.findById(lessonId);
	}

	public List<Lesson> getAll() {
		return lessonRepository.findAll();
	}

	public List<Lesson> getListByPeriod(LocalDate since, LocalDate to) {
		return lessonRepository.findByPeriod(since, to);
	}

	@Transactional
	public void update(Lesson lesson, Integer lessonId) {
		lesson.setId(lessonId);
		lessonRepository.save(lesson);
		logger.info("Updated lesson with id: {}", lesson.getId());
	}

	@Transactional
	public void deleteById(Integer lessonId) {
		lessonRepository.deleteById(lessonId);
		logger.info("Deleted lesson with id: {}", lessonId);
	}

	public List<Lesson> getLessonsOfGroupForDate(Group group, LocalDate date) {
		List<Lesson> lessons = lessonRepository.findByPeriod(date, date);
		lessons.removeIf(l -> !l.getGroup().getId().equals(group.getId()));
		return lessons;
	}

	public List<Lesson> getLessonsOfTeacherForDate(Teacher teacher, LocalDate date) {
		List<Lesson> lessons = lessonRepository.findByPeriod(date, date);
		lessons.removeIf(l -> !l.getTeacher().getId().equals(teacher.getId()));
		return lessons;
	}
}
