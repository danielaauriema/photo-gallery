package net.auriema.app.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import net.auriema.app.entity.Thumb;

public interface ThumbRepository extends MongoRepository<Thumb, UUID> {

}
