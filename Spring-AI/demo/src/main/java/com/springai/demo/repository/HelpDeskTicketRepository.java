package com.springai.demo.repository;

import com.springai.demo.entity.HelpDeskTool;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpDeskTicketRepository extends JpaRepository<HelpDeskTool, Integer> {
    List<HelpDeskTool> findByUsername(String username);
}
