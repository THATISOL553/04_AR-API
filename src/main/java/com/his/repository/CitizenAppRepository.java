package com.his.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.his.entity.CitizenApiEntity;

public interface CitizenAppRepository extends JpaRepository<CitizenApiEntity, Serializable>{

}
