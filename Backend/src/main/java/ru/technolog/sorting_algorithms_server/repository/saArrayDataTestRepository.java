package ru.technolog.sorting_algorithms_server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.technolog.sorting_algorithms_server.entitys.data.saArraysData_test;

// для работы с изменением данных в БД
@Repository
public interface saArrayDataTestRepository extends CrudRepository<saArraysData_test,Long> {
}
