package com.pierceecom.general.abstracts;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/*********************
 * Created by Alex S
 * on 17.09.2017
 ********************/

public abstract class AbstractRESTResource<T extends AbstractEntity> {

    protected Response listResult(GenericEntity<List<T>> entityList) {
        return Response
                .ok(entityList)
                .build();
    }

    protected Response singleResult(GenericEntity<T> entity) {
        return Response
                .ok(entity)
                .build();
    }

    protected Response createdResult(URI location) {
        return Response
                .created(location)
                .build();
    }

}
