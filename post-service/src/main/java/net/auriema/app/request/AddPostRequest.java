package net.auriema.app.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.auriema.app.model.PostModel;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddPostRequest extends PostModel {

    private String type;
    private String data;
    
}
