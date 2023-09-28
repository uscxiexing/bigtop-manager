package org.apache.bigtop.manager.server.orm.repository;

import org.apache.bigtop.manager.server.enums.JobState;
import org.apache.bigtop.manager.server.orm.entity.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findAllByJobId(Long jobId);

    List<Task> findAllByJobIdAndState(Long jobId, JobState state);

    List<Task> findAllByJobIdAndHostnameAndState(Long jobId, String hostname, JobState state);

}