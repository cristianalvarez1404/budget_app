package com.example.moneymanager.service;

import com.example.moneymanager.dto.CategoryDTO;
import com.example.moneymanager.dto.ProfileDTO;
import com.example.moneymanager.entity.CategoryEntity;
import com.example.moneymanager.entity.ProfileEntity;
import com.example.moneymanager.repository.CategoryRepository;
import com.example.moneymanager.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProfileRepository profileRepository;
    private final ProfileService profileService;

    //save category
    public CategoryDTO saveCategory(CategoryDTO categoryDTO){
        ProfileEntity profile = profileService.getCurrentProfile() ;
        if(categoryRepository.existsByNameAndProfileId(categoryDTO.getName(), profile.getId()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category with this name already exists!");
        }

        CategoryEntity newCategory = toEntity(categoryDTO,profile);
        newCategory = categoryRepository.save(newCategory);
        return toDTO(newCategory);
    }

    //helper methods
    private CategoryEntity toEntity(CategoryDTO category, ProfileEntity profile){
        return CategoryEntity.builder()
                .name(category.getName())
                .icon(category.getIcon())
                .profile(profile)
                .type(category.getType())
                .build();
    }

    private CategoryDTO toDTO(CategoryEntity entity){
        return CategoryDTO.builder()
                .id(entity.getId())
                .profileId(entity.getProfile() != null ? entity.getProfile().getId() : null)
                .name(entity.getName())
                .icon(entity.getIcon())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .type(entity.getType())
                .build();
    }
}
