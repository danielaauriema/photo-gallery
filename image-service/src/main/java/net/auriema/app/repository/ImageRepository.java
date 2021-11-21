package net.auriema.app.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import net.auriema.app.entity.Image;

public interface ImageRepository extends MongoRepository<Image, UUID> {

}
