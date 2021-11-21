package net.auriema.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.auriema.app.entity.Post;

@Repository
public interface PostRepository extends  JpaRepository<Post, Long> {

}
