package com.comviva.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comviva.entities.ResultOutput;

@Repository
public interface IResultOutputRepository extends JpaRepository<ResultOutput, Long> {

}
