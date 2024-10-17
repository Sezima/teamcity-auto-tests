package teamcity.api.models;

import lombok.Getter;
import lombok.Setter;

import java.net.http.HttpResponse;

@Getter
@Setter
// if we use key word like abstract it's mean we can't create instance for this class
public abstract class BaseModel {
    private String name;


}
