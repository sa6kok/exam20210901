package com.localbandb.localbandb.data.repositories;

import com.localbandb.localbandb.data.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
  Role findByAuthority(String authority);
}
