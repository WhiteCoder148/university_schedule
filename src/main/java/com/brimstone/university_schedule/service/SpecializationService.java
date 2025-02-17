package com.brimstone.university_schedule.service;

import com.brimstone.university_schedule.repository.SpecializationRepository;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.brimstone.university_schedule.model.entity.Specialization;

import jakarta.transaction.Transactional;

@Service
public class SpecializationService extends BaseService {
	
	private final SpecializationRepository specializationRepository;

	private final Logger logger = LoggerFactory.getLogger(SpecializationService.class);

	public SpecializationService(SpecializationRepository specializationRepository) {
		this.specializationRepository = specializationRepository;
	}

	@Transactional
	public void save(Specialization specialization) {
		specializationRepository.save(specialization);
		logger.info("Saved specialization with name: {}", specialization.getName());
	}

	public Optional<Specialization> getById(Integer specializationId) {
		return specializationRepository.findById(specializationId);
	}

	public Optional<Specialization> getByName(String specializationName){
		return specializationRepository.findByName(specializationName);
	}
	
	public List<Specialization> getAll() {
		return specializationRepository.findAll();
	}
	
	@Transactional
	public void update(Specialization specialization, Integer specializationId) {
		specialization.setId(specializationId);
		specializationRepository.save(specialization);
		logger.info("Updated specialization with id: {}", specialization.getId());
	}

	@Transactional
	public void deleteById(Integer specializationId) {
		specializationRepository.deleteById(specializationId);
		logger.info("Deleted specialization with id: {}", specializationId);
	}
}
