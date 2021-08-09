package com.chumlung.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chumlung.backend.model.Videolinks;

@Repository
public interface VideolinksRepository extends JpaRepository<Videolinks, Long>{

}
