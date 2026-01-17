package com.lntu.digitalhuman.service;

import com.lntu.digitalhuman.entity.HumanData;
import com.lntu.digitalhuman.repository.HumanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HumanService {

    private final HumanRepository repository;

    public List<HumanData> getAllHumanData() {
        return repository.findAll();
    }

    public HumanData saveHumanData(HumanData data) {
        if (data.getId() == null) {
            data.setCreatedAt(LocalDateTime.now());
        }
        data.setUpdatedAt(LocalDateTime.now());
        return repository.save(data);
    }

    public HumanData getHumanDataById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteHumanData(String id) {
        repository.deleteById(id);
    }

    public List<HumanData> findByName(String name) {
        return repository.findAll().stream()
                .filter(human -> human.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}