package com.springboot.prodreadyfeatures.Repository;

import com.springboot.prodreadyfeatures.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Long> {

}
