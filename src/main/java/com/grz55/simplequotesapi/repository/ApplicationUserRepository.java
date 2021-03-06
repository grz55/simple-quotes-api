package com.grz55.simplequotesapi.repository;

import com.grz55.simplequotesapi.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    Optional<UserDetails> findByUsername(String username);

}
