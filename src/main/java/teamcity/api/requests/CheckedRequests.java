package teamcity.api.requests;

import teamcity.api.enums.Endpoint;
import teamcity.api.models.BaseModel;
import io.restassured.specification.RequestSpecification;
import teamcity.api.requests.checked.CheckedBase;

import java.util.EnumMap;

public class CheckedRequests {
//    private final EnumMap<Endpoint, CheckedBase<?>> requests = new EnumMap<>(Endpoint.class);
//
//    public CheckedRequests(RequestSpecification spec) {
//        for (var endpoint: Endpoint.values()) {
//            requests.put(endpoint, new CheckedBase<>(spec, endpoint));
//        }
//    }

//    public <T extends BaseModel> CheckedBase<T> getRequest(Endpoint endpoint) {
//        return (CheckedBase<T>) requests.get(endpoint);
//    }

    // Изменяем EnumMap, чтобы он принимал CheckedBase с подтипами BaseModel
    private final EnumMap<Endpoint, CheckedBase<? extends BaseModel>> requests = new EnumMap<>(Endpoint.class);

    public CheckedRequests(RequestSpecification spec) {
        for (var endpoint : Endpoint.values()) {
            // Создаем CheckedBase с конкретным типом BaseModel
            requests.put(endpoint, new CheckedBase<>(spec, endpoint));
        }
    }

    @SuppressWarnings("unchecked") // Используем аннотацию, чтобы подавить предупреждение о непроверяемом приведении
    public <T extends BaseModel> CheckedBase<T> getRequest(Endpoint endpoint) {
        CheckedBase<?> request = requests.get(endpoint);
        if (request == null) {
            return null; // Или выбросьте исключение, если это необходимо
        }
        // Проверяем тип перед приведением
        if (request instanceof CheckedBase<?>) {
            return (CheckedBase<T>) request; // Безопасное приведение
        }
        throw new ClassCastException("Unable to cast to CheckedBase<T>");
    }

}
