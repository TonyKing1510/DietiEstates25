package it.unina.webtech;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VisitaControllerTest {
    private VisitaController controller;

    @BeforeEach
    void setUp() {
        controller = new VisitaController();
    }

    private Response callCheckVisita(Integer idImmobile, String email) {
        return controller.checkVisitaPerCliente(idImmobile, email);
    }

    @Test
    void testCheckVisita_EmailNull(){
        int idImmobile = 0;

        Response response = callCheckVisita(idImmobile, null);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void testCheckVisita_EmailEmpty(){
        String email = "";
        int idImmobile = 1;

        Response response = callCheckVisita(idImmobile, email);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void testCheckVisita_EmailInvalid(){
        String email = "invalidEmail";
        int idImmobile = 3;

        Response response = callCheckVisita(idImmobile, email);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void testCheckVisita_EmailValidIdNull(){
        String email = "email123@gmail.com";

        Response response = callCheckVisita(null, email);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void testCheckVisita_EmailValidIdNegativ(){
        String email = "email123@gmail.com";
        int idImmobile = -1;

        Response response = callCheckVisita(idImmobile, email);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void testCheckVisita_EmailValidIdZero(){
        String email = "email123@gmail.com";
        int idImmobile = 0;

        Response response = callCheckVisita(idImmobile, email);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }


    @Test
    void testCheckVisita_EmailValidIdPositive(){
        String email = "email123@gmail.com";
        int idImmobile = 1;

        Response response = callCheckVisita(idImmobile, email);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }


}
