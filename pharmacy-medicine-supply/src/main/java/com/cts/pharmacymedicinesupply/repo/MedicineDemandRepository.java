package com.cts.pharmacymedicinesupply.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.pharmacymedicinesupply.model.MedicineDemand;

@Repository
public interface MedicineDemandRepository extends JpaRepository<MedicineDemand,Integer>{

}
