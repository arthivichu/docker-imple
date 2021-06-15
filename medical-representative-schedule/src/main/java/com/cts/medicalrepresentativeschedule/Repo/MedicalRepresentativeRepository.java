package com.cts.medicalrepresentativeschedule.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.medicalrepresentativeschedule.model.MedicalRepresentative;

@Repository
public interface MedicalRepresentativeRepository extends JpaRepository<MedicalRepresentative,Integer> {

}
