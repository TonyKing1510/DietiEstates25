package it.unina.webtech;
import it.unina.webtech.dto.request.LasciaRecensioneRequestDTO;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteControllerTest {

    private ClienteController controller;

    @BeforeEach
    void setUp() {
        controller = new ClienteController();
    }

    private LasciaRecensioneRequestDTO creaRecensione(String agenteDaRecensire, int valutazione) {
        LasciaRecensioneRequestDTO recensioneDTO = new LasciaRecensioneRequestDTO();
        recensioneDTO.setAgenteDaRecensire(agenteDaRecensire);
        recensioneDTO.setValutazione(valutazione);
        return recensioneDTO;
    }

    @Test
    void testLasciaRecensione_CFNullValZero() {
        //Arrange
        LasciaRecensioneRequestDTO recensioneDTO = creaRecensione(null,0);

        //Act e Assert
        Response response = controller.lasciaRecensione(recensioneDTO);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void testLasciaRecensione_CFEmpty() {
        LasciaRecensioneRequestDTO recensioneDTO = creaRecensione("",1);

        Response response = controller.lasciaRecensione(recensioneDTO);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void testLasciaRecensione_CFNonValid() {
        LasciaRecensioneRequestDTO recensioneDTO = creaRecensione("SVN",5);

        Response response = controller.lasciaRecensione(recensioneDTO);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void testLasciaRecensione_ValNegativa() {
        LasciaRecensioneRequestDTO recensioneDTO=creaRecensione("SVNGLN03T05H892R",-1);

        Response response = controller.lasciaRecensione(recensioneDTO);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void testLasciaRecensione_ValMaggioreDiCinque() {
        LasciaRecensioneRequestDTO recensioneDTO = creaRecensione("SVNGLN03T05H892R",6);

        Response response = controller.lasciaRecensione(recensioneDTO);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void testLasciaRecensione_CFValidoValValida() {
        LasciaRecensioneRequestDTO recensioneDTO = creaRecensione("SVNGLN03T05H175V",4);

        Response response = controller.lasciaRecensione(recensioneDTO);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }


}
