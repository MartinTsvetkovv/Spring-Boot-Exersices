package com.example.productservicerestapi.Exeptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PatientExeptionMapper implements ExceptionMapper<PatientBussinessExeption> {
    @Override
    public Response toResponse(PatientBussinessExeption exception) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"status\":\"error\"");
        sb.append(",");
        sb.append("\"message\":\"Try Again\"");

        return Response.serverError().entity(sb.toString()).type(MediaType.APPLICATION_JSON).build();
    }
}
