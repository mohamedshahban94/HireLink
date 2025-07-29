package com.learn.signinpage.repo;

import com.learn.signinpage.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job , Integer> {

}
