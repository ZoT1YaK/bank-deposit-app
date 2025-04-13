package com.bank.controller;

import com.bank.model.DepositRequest;
import com.bank.service.DepositService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/deposits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepositResource {

    @Inject
    public DepositService depositService;

    @GET
    public List<DepositRequest> getAll() {
        return depositService.listAll();
    }

    @POST
    public Response create(@Valid DepositRequest request) {
        try {
            return Response.status(Response.Status.CREATED).entity(depositService.save(request)).build();
        }
        catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}