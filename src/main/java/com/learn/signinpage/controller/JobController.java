package com.learn.signinpage.controller;

import com.learn.signinpage.model.Job;
import com.learn.signinpage.repo.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/jobs")
    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    @GetMapping("jobs/{id}")
    public ResponseEntity<Job> getJob(@PathVariable int id) {
        return jobRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(@RequestBody Job job){
        System.out.println("create job");
        job.setDate(new Date());
        Job savedJob =jobRepository.save(job);
        return ResponseEntity.ok(savedJob);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable int id ,@RequestBody Job job){

        return jobRepository.findById(id)
                .map(existingJob->{
                        existingJob.setCompany(job.getCompany());
                        existingJob.setLocation(job.getLocation());
                        existingJob.setRole(job.getRole());
                        existingJob.setExperience(job.getExperience());
                        existingJob.setLink(job.getLink());
                        return ResponseEntity.ok(jobRepository.save(existingJob));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable int id){

        if(!jobRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        jobRepository.deleteById(id);
        return ResponseEntity.ok("Job Deleted");
    }

}
