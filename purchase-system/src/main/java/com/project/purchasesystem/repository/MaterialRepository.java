package com.project.purchasesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.purchasesystem.entity.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}