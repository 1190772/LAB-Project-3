package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ContainerOperationTest {

    @Test
    void testToString() {
        LocalDateTime dateTime = LocalDateTime.of(2021, 11, 14, 10, 20, 30, 482371);
        ContainerOperation containerOperation = new ContainerOperation("user", dateTime, "Update", "BICZ1231235", 12122);

        String expected = "ContainerOperation{user='user', dateTime=" + dateTime + ", type='Update', containerID='BICZ1231235', cargoManifestID='12122'}";

        Assertions.assertEquals(expected, containerOperation.toString());
    }
}