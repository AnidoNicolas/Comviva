package com.comviva.exercise2.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.comviva.exercise2.entities.ResultOutput;

@Repository
public interface IResultOutputRepository extends JpaRepository<ResultOutput, Long> {

}
