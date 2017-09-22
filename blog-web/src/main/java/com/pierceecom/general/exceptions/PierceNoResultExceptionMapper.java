package com.pierceecom.general.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/*********************
 * Created by Alex S
 * on 20.09.2017
 ********************/

public class PierceNoResultExceptionMapper implements ExceptionMapper<PierceNoResultException> {

    public Response toResponse(PierceNoResultException ex) {
        return Response
                .status(ex.getStatusCode())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
