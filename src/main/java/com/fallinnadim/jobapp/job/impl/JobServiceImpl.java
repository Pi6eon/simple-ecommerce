package com.fallinnadim.jobapp.job.impl;

import com.fallinnadim.jobapp.job.Job;
import com.fallinnadim.jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;
    @Override
    public List<Job> findAll() {
        return jobs;
    }
    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        for (Job job: jobs) {
            if (job.getId().equals(id)) {
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean deleteJobById(Long id) {
        Job job = getJobById(id);
        return jobs.remove(job);
    }

    @Override
    public boolean updateJobById(Long id, Job job) {
        for (Job jobToChange: jobs) {
            if (jobToChange.getId().equals(id)) {
                jobToChange.setTitle(job.getTitle());
                jobToChange.setDescription(job.getDescription());
                jobToChange.setMaxSalary(job.getMaxSalary());
                jobToChange.setMinSalary(job.getMinSalary());
                jobToChange.setLocation(job.getLocation());
                return true;
            }
        }
        return false;
    }
}
