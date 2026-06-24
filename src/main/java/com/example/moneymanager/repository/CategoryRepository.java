package com.example.moneymanager.repository;

import com.example.moneymanager.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    //SELECT * FROM tbl_categories WHERE profile_id = ?
    List<CategoryEntity> findByProfileId(Long profileId);

    //SELECT * FROM tbl_categories WHERE id = ? AND profile_id = ?
    Optional<CategoryEntity> findByIdAndProfileId(Long id, Long profileId);

    //SELECT * FROM tbl_categories WHERE type = ? AND profile_id = ?
    List<CategoryEntity> findByTypeAndProfileId(String type, Long profileId);

    //SELECT * FROM tbl_categories WHERE name = ? AND profile_id = ?
    Boolean existsByNameAndProfileId(String name, Long profileId);


}
