package teamcity.api.requests;
//    we should create 4 method and we will implement

import teamcity.api.models.BaseModel;

public interface CrudInterface {
    Object create(BaseModel model);
    Object read(String id);
    Object update(String id, BaseModel model);
    Object delete(String id);
}
