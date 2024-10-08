package teamcity.api.requests;

import teamcity.api.models.BaseModel;

public interface CrudInterface {
//    we should create 4 method and we will implement

    Object create(BaseModel model);
    Object read(String id);
    Object update(String id, BaseModel model);
    Object delete(String id);

}
