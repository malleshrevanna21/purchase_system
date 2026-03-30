package com.project.purchasesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.purchasesystem.entity.Mrp;

public interface MrpRepository extends JpaRepository<Mrp, Long> {

}