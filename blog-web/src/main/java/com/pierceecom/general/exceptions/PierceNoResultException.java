package com.pierceecom.general.exceptions;

import javax.ws.rs.core.Response;

/*********************
 * Created by Alex S
 * on 20.09.2017
 ********************/

public class PierceNoResultException extends Exception {

    private Response.Status statusCode;

    public PierceNoResultException(Response.Status statusCode) {
        this.statusCode = statusCode;
    }

    //<editor-fold desc="getters and setters">
    public Response.Status getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Response.Status statusCode) {
        this.statusCode = statusCode;
    }
    //</editor-fold>
}
