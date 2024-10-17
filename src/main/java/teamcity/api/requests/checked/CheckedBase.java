package teamcity.api.requests.checked;


import io.restassured.response.Response;
import teamcity.api.enums.Endpoint;
import teamcity.api.models.BaseModel;
import teamcity.api.requests.CrudInterface;
import teamcity.api.requests.Request;
import teamcity.api.requests.unchecked.UncheckedBase;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

@SuppressWarnings("unchecked")
public final class CheckedBase<T extends BaseModel> extends Request implements CrudInterface {
    private final UncheckedBase uncheckedBase;

    public CheckedBase(RequestSpecification spec, Endpoint endpoint) {
        super(spec, endpoint);
        this.uncheckedBase = new UncheckedBase(spec, endpoint);
    }

//    @Override
//    public T create(BaseModel model) {
//        return (T) uncheckedBase
//                .create(model)
//                .then().assertThat().statusCode(HttpStatus.SC_OK)
//                .extract().as(endpoint.getModelClass());
//    }

    @Override
    public Response create(BaseModel model) {
        return uncheckedBase
                .create(model)
                .then() // Цепочка для проверки и извлечения
                .extract().response(); // Извлекаем Response через ValidatableResponse
    }


    @Override
    public T read(String id) {
        return (T) uncheckedBase
                .read(id)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(endpoint.getModelClass());
    }

    @Override
    public T update(String id, BaseModel model) {
        return (T) uncheckedBase
                .update(id, model)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(endpoint.getModelClass());
    }

    @Override
    public Object delete(String id) {
        return uncheckedBase
                .delete(id)
                .then() // Цепочка для проверки
                .assertThat().statusCode(HttpStatus.SC_NO_CONTENT) // Проверка кода 204
                .extract().asString();
    }

}