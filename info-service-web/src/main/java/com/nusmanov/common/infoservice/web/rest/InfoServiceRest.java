package com.nusmanov.common.infoservice.web.rest;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("/")
public class InfoServiceRest {

    @GET
    @Path("/status")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping(@QueryParam("message") final String message) {

        if (null == message) {
            return "ping pong";
        }

        return message;

    }
}
