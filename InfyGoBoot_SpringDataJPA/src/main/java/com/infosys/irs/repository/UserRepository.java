package com.infosys.irs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.infosys.irs.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
