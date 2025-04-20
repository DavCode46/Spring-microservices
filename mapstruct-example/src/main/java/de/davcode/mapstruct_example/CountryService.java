package de.davcode.mapstruct_example;


import de.davcode.mapstruct_example.dao.CountryDao;
import de.davcode.mapstruct_example.dtos.CountryDto;
import de.davcode.mapstruct_example.mappers.CountryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CountryService {

    public CountryDto readById(UUID id) {
        if (CountryDao.db.containsKey(id)) {
            return CountryMapper.mapper.toCountryDto(CountryDao.db.get(id));
        } else {
            log.error("Country with id {} not found", id);
            throw  new RuntimeException("Country with id: " + id  + " not found");
        }
    }
}