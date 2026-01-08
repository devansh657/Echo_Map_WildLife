package com.example.demo.repository;

import com.example.demo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Override
    Optional<Event> findById(Integer id);

    Optional<Event> findByTitle(String title);

    List<Event> findByOrganizerUsername(String username);
}
