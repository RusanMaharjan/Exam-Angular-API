package com.exam.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.quiz.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
