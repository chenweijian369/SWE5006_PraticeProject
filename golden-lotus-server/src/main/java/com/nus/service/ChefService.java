package com.nus.service;

import com.nus.entity.Chef;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChefService {

    List<Chef> getAllChefs();


}
