package com.qa.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.spring.data.Marsupial;

@Repository
public interface MarsupialRepo extends JpaRepository<Marsupial, Integer> {

}