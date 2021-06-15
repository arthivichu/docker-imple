package com.cts.medicalrepresentativeschedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.medicalrepresentativeschedule.Repo.MedicalRepresentativeRepository;
import com.cts.medicalrepresentativeschedule.model.MedicalRepresentative;

@Service
public class MedicalRepresentativeServiceImpl implements MedicalRepresentativeService {
	
	@Autowired
	MedicalRepresentativeRepository repo;
	
	@Override
	public List<MedicalRepresentative> getMedicalRepresentative(){
	
		return repo.findAll();
	}

}
