package lapr.project.controller;

import lapr.project.model.shared.Constants;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AuthControllerTest {
    @Test
    void authControllerTest() {
        AuthController authController = new AuthController();
        assertTrue(authController.doLogin("manager@lei.sem1.pt", Constants.THE_OTHER_ONE));
        assertEquals(Constants.ROLE_TRAFFIC_MANAGER, authController.getUserRoles().get(0).getDescription());
        authController.doLogout();
        assertNull(authController.getUserRoles());
    }
}
