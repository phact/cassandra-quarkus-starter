package project;

import project.cassandra.CassandraManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
public class ProjectResource {

    private static final Logger logger = Logger.getLogger(ProjectResource.class);

    ObjectMapper objectMapper = new ObjectMapper();

    @Inject
    CassandraManager cassandraManager;

    void onStart(@Observes StartupEvent ev) {               //
        logger.info("The application is starting...");
        setup();
    }

    private void setup() {
    }

}
