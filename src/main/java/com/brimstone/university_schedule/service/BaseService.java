package com.brimstone.university_schedule.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.brimstone.university_schedule.model.entity.BaseModel;

@Component
class BaseService {
	
	public <T extends BaseModel> List<T> sortListByIdFromMoreToLess(List<T> list) {
		if (list.isEmpty()) {
			return list;
		}
		list.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
		return list;
	}
	
	public <T extends BaseModel> List<T> sortListByIdFromLessToMore(List<T> list) {
		if (list.isEmpty()) {
			return list;
		}
		list.sort(Comparator.comparing(BaseModel::getId));
		return list;
	}

	public <T extends BaseModel> Optional<T> findInListById(List<T> list, Integer id) {
		if (list.isEmpty()) {
			return Optional.empty();
		}
		return list.stream().filter(o -> o.getId().equals(id)).findFirst();
	}
}
