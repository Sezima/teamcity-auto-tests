package teamcity.api.models;

import teamcity.api.annotations.Random;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseModel {
    private String id;
    @Random
    private String username;
    @Random
    private String password;
    private Roles roles;
}


//model for project. data class
//  why we need lombok?
//    so we can write getters and settes manualy but its good if we have little data(variables)
//    but what we get if we have 10 or 20 and more parameters (variables) ?
//    so we can solve this problem with lombok. And lombok solve not only this problem

