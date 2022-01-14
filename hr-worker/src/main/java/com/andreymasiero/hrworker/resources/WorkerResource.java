package com.andreymasiero.hrworker.resources;

import com.andreymasiero.hrworker.entities.Worker;
import com.andreymasiero.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerResource {

    private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);

    private final Environment env;
    private final WorkerRepository repository;

    @Autowired
    public WorkerResource(WorkerRepository repository, Environment env) {
        this.repository = repository;
        this.env = env;
    }

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {
        logger.info("PORT = " + env.getProperty("local.server.port"));
        var worker = repository.findById(id);
        return worker.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
