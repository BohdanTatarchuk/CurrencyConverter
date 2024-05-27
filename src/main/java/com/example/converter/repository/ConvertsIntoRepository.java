package com.example.converter.repository;

import com.example.converter.model.ConvertsInto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvertsIntoRepository extends JpaRepository<ConvertsInto, Integer> {



}
