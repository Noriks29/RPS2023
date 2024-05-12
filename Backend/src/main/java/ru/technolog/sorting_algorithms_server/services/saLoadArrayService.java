package ru.technolog.sorting_algorithms_server.services;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.technolog.sorting_algorithms_server.entitys.data.saArraysData;
import ru.technolog.sorting_algorithms_server.entitys.dto.dtoArray;
import ru.technolog.sorting_algorithms_server.repository.saArrayDataRepository;
import ru.technolog.sorting_algorithms_server.response.ApiResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class saLoadArrayService {
    // Автоматическое внедрение зависимости репозитория для работы с данными массивов
    @Autowired
    private saArrayDataRepository arrayDataRepository;


    // Метод для добавления массива в базу данных
    public ResponseEntity<ApiResponse> addArrayWithSorting(dtoArray arrayDTO) {
        // Создаем объект saArraysData и устанавливаем его поля на основе данных из DTO
        try {

            saArraysData saArraysData = new saArraysData();
            saArraysData.setArrayData(arrayDTO.getArrayData());
            saArraysData.setArrayData_sort(arrayDTO.getArrayData_sort());
            if (arrayDTO.getArrayData().isEmpty()) {
                return ResponseEntity.status(400).body(new ApiResponse("Данные успешно добавлены для сортировки"));
            }
            saArraysData.setArrayName(arrayDTO.getArrayName());
            saArraysData.setDateOfLoad(LocalDateTime.now());
            saArraysData.setStatusOfLoad(true);
            saArraysData.setStatusOfSorted(true);
            // Сохраняем массив в репозитории
            arrayDataRepository.save(saArraysData);

            // Возвращаем успешный ответ с сообщением
            return ResponseEntity.ok(new ApiResponse("Данные успешно добавлены для сортировки"));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new ApiResponse(exception.fillInStackTrace().getMessage()));
        }

    }


    // Метод для удаления массива по его идентификатору
    public ResponseEntity<ApiResponse> deleteByIdArray(Long arrayId) {
        // Пытаемся найти массив по его идентификатору
        Optional<saArraysData> saArraysDataOptional = arrayDataRepository.findById(arrayId);

        if (saArraysDataOptional.isPresent()) {
            // Если массив найден, удаляем его из репозитория
            arrayDataRepository.deleteById(arrayId);
            return ResponseEntity.ok(new ApiResponse("Удаление успешно"));
        } else {
            // Если массив не найден, возвращаем ошибку 404 с соответствующим сообщением
            return ResponseEntity.status(404).body(new ApiResponse("Этот массив не существует"));
        }
    }

    // Метод для получения списка всех массивов в виде DTO
    public List<dtoArray> getAllArrays() {
        // Получаем список всех массивов из репозитория
        List<saArraysData> arraysList = StreamSupport.stream(arrayDataRepository.findAll().spliterator(), true).toList();

        // Преобразуем список массивов в список DTO
        return arraysList.stream()
                .map(this::mapToArraysDTO)
                .toList();
    }

    // Метод для преобразования объекта saArraysData в объект dtoArray
    public dtoArray mapToArraysDTO(saArraysData saArraysData) {
        dtoArray dtoArray = new dtoArray();
        dtoArray.setArrayData(saArraysData.getArrayData());
        dtoArray.setArrayData_sort(saArraysData.getArrayData_sort());
        dtoArray.setArrayId(saArraysData.getArrayId());
        dtoArray.setArrayName(saArraysData.getArrayName());
        dtoArray.setDateOfLoad(saArraysData.getDateOfLoad());
        Boolean statusOfSorted = saArraysData.isStatusOfSorted();
        dtoArray.setStatusOfSorted(statusOfSorted != null && statusOfSorted);
        Boolean statusOfLoaded = saArraysData.isStatusOfLoad();
        dtoArray.setStatusOfLoad(statusOfLoaded != null && statusOfLoaded);

        return dtoArray;
    }

    public ResponseEntity<ApiResponse> updateArrayWithSorted(Long arrayId, dtoArray arrayDTO) {
        Optional<saArraysData> saArraysDataOptional = arrayDataRepository.findById(arrayId);
        if (saArraysDataOptional.isPresent()) {
            saArraysData saArraysData = saArraysDataOptional.get();
            saArraysData.setArrayData(arrayDTO.getArrayData());
            saArraysData.setArrayData_sort(arrayDTO.getArrayData_sort());
            saArraysData.setArrayName(saArraysData.getArrayName());
            saArraysData.setDateOfLoad(LocalDateTime.now());
            saArraysData.setStatusOfLoad(true);
            saArraysData.setStatusOfSorted(true);
            // Сохраняем массив в репозитории
            arrayDataRepository.save(saArraysData);

            // Возвращаем успешный ответ с сообщением
            return ResponseEntity.ok(new ApiResponse("Данные успешно обновлены"));
        } else {
            // Если массив не найден, возвращаем ошибку 404 с соответствующим сообщением
            return ResponseEntity.status(404).body(new ApiResponse("По этому индексу данных нет"));
        }
    }

}
