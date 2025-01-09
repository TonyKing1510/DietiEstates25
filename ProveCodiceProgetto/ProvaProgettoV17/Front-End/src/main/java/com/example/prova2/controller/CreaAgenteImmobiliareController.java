package com.example.prova2.controller;
import com.example.prova2.model.*;
import com.example.prova2.service.AddAgenteService;
import com.example.prova2.service.GestoreCFService;
import com.example.prova2.service.GestoreMailService;
import com.example.prova2.service.GestoreUsernameService;
import com.example.prova2.utility.AlertFactory;
import com.example.prova2.view.CreaGestore;
import com.example.prova2.view.DashboardAmministratore;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.Optional;

public class CreaAgenteImmobiliareController {

    @FXML
    public Label erroreMinimoPassword;

    @FXML
    public Label erroreMinimoUsername;


    private GestoreAgenziaImmobiliare adminCheStaCreandoAgente;

    @FXML
    public TextField textFieldNome;

    @FXML
    public TextField textFieldCognome;

    @FXML
    public TextField textFieldCf;

    @FXML
    public TextField textFieldNumeroTelefono;

    @FXML
    public TextField textFieldVia;

    @FXML
    public TextField textFieldNCivico;

    @FXML
    public Button bottoneSalvaDatiAna;

    @FXML
    public Label erroreNome;

    @FXML
    public Label erroreCodiceFiscale;

    @FXML
    public Label erroreCognome;

    @FXML
    public Label erroreNumeroTelefono;

    @FXML
    public Label erroreVia;

    @FXML
    public Label erroreNumeroCivico;

    @FXML
    public TextField textFieldUsername;

    @FXML
    public TextField textFieldEmail;

    @FXML
    public PasswordField textFieldPassword;

    @FXML
    public Label erroreUsername;

    @FXML
    public Label errorePassword;

    @FXML
    public Label erroreEmail;


    @FXML
    public PasswordField textFieldRiPassword;

    @FXML
    public Button tornaIndietroBottone;

    @FXML
    public Label errorepasswordNonUguale;

    @FXML
    public Label passwordLabel;

    @FXML
    public Label passwordReLabel;

    @FXML
    public Label emailLabel;

    @FXML
    public Label usernameLabel;

    @FXML
    public Label numeroTelefonoLabel;

    @FXML
    public Label viaLabel;

    @FXML
    public Label numeroCivicoLabel;

    @FXML
    public Label nomeLabel;

    @FXML
    public Label cognomeLabel;

    @FXML
    public Label cflabel;

    @FXML
    public Label aziendaAppLabel;

    @FXML
    public TextField azienda;

    @FXML
    public TextField textFieldPassword2;

    @FXML
    public Button buttonPassword;

    private static final String MSG_ERRORE_APERTURE_PAGINE = "Errore di navigazione";

    public void initialize(){
        this.adminCheStaCreandoAgente = DashboardAmministratoreController.getAdmin();
        // Inizializza i due campi
        textFieldPassword2.setManaged(false); // Nasconde il TextField alla partenza
        textFieldPassword2.setVisible(false);
        textFieldPassword2.textProperty().bindBidirectional(textFieldPassword.textProperty());

        // Imposta l'evento per alternare i campi
        buttonPassword.setOnAction(event -> togglePasswordVisibility());
    }


    public  void salvaDati() {
        if(isCampiControllatiConSuccesso()) {
            inviaDati();
        }
    }

    private void inviaDati() {
        if(inviaDatiEffettivo()) {
            mostraPopUpConferma();
        }else{
            AlertFactory.generateFailAlert("Errore!","Errore");
        }
    }

    private boolean inviaDatiEffettivo() {
        try {
            AccountAziendale accountAziendale = creaAccount();
            GestoreAgenziaImmobiliare gestoreAgenziaImmobiliare = creaGestore();
            Agente a = creaAgente();
            a.setAccountAgente(accountAziendale);
            a.setGestoreRiferimento(gestoreAgenziaImmobiliare);
            return AddAgenteService.addAgente(a);
        }catch (IOException e) {
            AlertFactory.generateFailAlert("Errore!","Errore nella creazione del Agente!");
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AlertFactory.generateFailAlert("Errore!","Errore nell'aggiunta del agente!");
            return false;
        }
    }

    private boolean isCampiControllatiConSuccesso() {
        return checkCampi() && (controllaSeCiSonoDuplicati());
    }

    private boolean controllaSeCiSonoDuplicati() {
        return checkDuplicati(textFieldEmail.getText(), textFieldUsername.getText(), textFieldCf.getText());
    }


    public boolean checkDuplicati(String email,String username,String cf) {
        boolean flag = true;
        flag &= checkDuplicatoEmail(email);
        flag &= checkDuplicatoUsername(username);
        flag &= checkDuplicatoCF(cf);
        return flag;
    }

    @FXML
    private boolean checkDuplicatoCF(String cf) {
        try {
            return CreaGestoreController.isCampoGiaRegistrato(cf, GestoreCFService.getAllCfGestore(), erroreCodiceFiscale, "Le chiediamo gentilmente di inserire un codice fiscale non ancora registrato.");
        }catch (IOException e) {
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    @FXML
    private boolean checkDuplicatoUsername(String username) {
        try {
            return CreaGestoreController.isCampoGiaRegistrato(username, GestoreUsernameService.getAllUsernameGestore(), erroreUsername, "Le chiediamo gentilmente di inserire uno username non ancora registrato.");
        }catch (IOException e) {
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    @FXML
    private boolean checkDuplicatoEmail(String email) {
        try {
            return CreaGestoreController.isCampoGiaRegistrato(email, GestoreMailService.getAllEmailGestore(), erroreEmail, "Le chiediamo gentilmente di inserire una mail non ancora registrata.");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }


    private boolean checkCampi() {
        boolean flag = true;
        flag &= checkDatiAccount();
        flag &= checkDatiAnagrafici();
        return flag;
    }

    private boolean checkDatiAnagrafici() {
        boolean flag = CreaGestoreController.INITIAL_VALUE_FLAG;
        flag &= checkNome();
        flag &= checkCognome();
        flag &= checkCf();
        flag &= checkTelefono();
        flag &= checkVia();
        flag &= checkNumeroCivico();
        return flag;
    }


    private boolean checkDatiAccount() {
        boolean flag = CreaGestoreController.INITIAL_VALUE_FLAG;
        flag &= checkEmail();
        flag&= checkPassword();
        flag&= checkUsername();
        flag&= checkRePassword();
        return flag;
    }



    private Agente creaAgente() {
        return new Agente(textFieldNome.getText(), textFieldCognome.getText(),
                textFieldCf.getText(), textFieldNumeroTelefono.getText(), textFieldVia.getText(),textFieldNCivico.getText());
    }

    private AccountAziendale creaAccount() {
        return new AccountAziendale(textFieldUsername.getText(),
                textFieldPassword.getText(), textFieldEmail.getText());
    }

    private GestoreAgenziaImmobiliare creaGestore() {
        return new GestoreAgenziaImmobiliare(adminCheStaCreandoAgente.getCf()
                , adminCheStaCreandoAgente.getCognome(), adminCheStaCreandoAgente.getCf(),
                adminCheStaCreandoAgente.getTelefono(), adminCheStaCreandoAgente.getVia(),
                adminCheStaCreandoAgente.getNumeroCivico());
    }


    private void mostraPopUpConferma() {
        Alert alert = CreaGestoreController.generateSuccessMessage();
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get().getText().equals("Torna alla dashboard")) {
                navigaAllaDashboard();
            } else if (result.get().getText().equals("Continua a creare altri agenti")) {
                navigaACreaGestore();
            }
        }
    }

    private void navigaAllaDashboard() {
        try {
            DashboardAmministratore.initializePageDashboardAmministratore(bottoneSalvaDatiAna.getScene().getWindow());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AlertFactory.generateFailAlert("Operazione interrotta", "Il thread è stato interrotto durante l'operazione.");
        } catch (IOException e) {
            AlertFactory.generateFailAlert(MSG_ERRORE_APERTURE_PAGINE, "Si è verificato un problema durante il caricamento della dashboard.");
        }
    }

    private void navigaACreaGestore() {
        try {
            CreaGestore.initializePageCreaGestore(bottoneSalvaDatiAna.getScene().getWindow());
        } catch (Exception e) {
            AlertFactory.generateFailAlert(MSG_ERRORE_APERTURE_PAGINE, "Si è verificato un problema durante il caricamento della pagina di creazione gestori.");
        }
    }

    public void tornaIndietro() {
        try {
            DashboardAmministratore.initializePageDashboardAmministratore(tornaIndietroBottone.getScene().getWindow());
        } catch (IOException e){
            AlertFactory.generateFailAlert(MSG_ERRORE_APERTURE_PAGINE,"Si è verificato un errore ci scusi!");
            e.printStackTrace();
        } catch (InterruptedException e1){
            Thread.currentThread().interrupt();
            AlertFactory.generateFailAlert(MSG_ERRORE_APERTURE_PAGINE,"Si è verificato un errore ci scusi!");
        }
    }

    public boolean checkEmail(){
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        return creaGestoreController.isValidCampo(textFieldEmail.getText(), CreaGestoreController.EMAILPATTERN, erroreEmail, "una email", emailLabel);
    }

    public boolean checkUsername(){
        String username = textFieldUsername.getText();
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        return creaGestoreController.isValidCampo(username, CreaGestoreController.USERNAMEPATTERN, erroreUsername, "uno username", usernameLabel);
    }


    public boolean checkPassword() {
        String password = textFieldPassword.getText();
        String passwordRe = textFieldRiPassword.getText();
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        creaGestoreController.isValidCampo(password,passwordRe,errorepasswordNonUguale,"due password uguali",passwordReLabel);
        return creaGestoreController.isValidCampo(password,CreaGestoreController.PASSWORDPATTERN,errorePassword,"una password",passwordLabel);
    }


    public boolean checkRePassword() {
        String password = textFieldPassword.getText();
        String passwordRinserita = textFieldRiPassword.getText();
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        return creaGestoreController.isValidCampo(password,passwordRinserita,errorepasswordNonUguale,"due password uguali",passwordReLabel);
    }


    public Boolean checkNome() {
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        return creaGestoreController.controllaCampiAnagrafici(textFieldNome, erroreNome, CreaGestoreController.UNNOME);
    }

    public boolean checkCognome() {
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        return creaGestoreController.controllaCampiAnagrafici(textFieldCognome, erroreCognome, CreaGestoreController.COGNOMEMSG);
    }

    public boolean checkCf() {
        String cf = textFieldCf.getText();
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        return creaGestoreController.isValidCampo(cf, CreaGestoreController.CFPATTERN, erroreCodiceFiscale, "un codice fiscale", cflabel);
    }

    public boolean checkTelefono() {
        String telefono = textFieldNumeroTelefono.getText();
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        return creaGestoreController.isValidCampo(telefono,CreaGestoreController.TELEFONOPATTERN,erroreNumeroTelefono,"un numero di telefono",numeroTelefonoLabel);
    }

    public boolean checkVia() {
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        return creaGestoreController.controllaCampiAnagrafici(textFieldVia, erroreVia, CreaGestoreController.UNAVIA);
    }

    public boolean checkNumeroCivico(){
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        String numeroCivico = textFieldNCivico.getText();
        return creaGestoreController.isValidCampo(numeroCivico,CreaGestoreController.NUMEROCIVICOPATTERN,erroreNumeroCivico,"un numero civico",numeroCivicoLabel);
    }


    /*public void mostraErroreLunghezzaPassword() {
        CreaGestoreController.attivaMessaggioMinimoCaratteri(textFieldPassword.getText(),8,erroreMinimoPassword);
    }

    public void mostraErroreLunghezzaUsername() {
        CreaGestoreController.attivaMessaggioMinimoCaratteri(textFieldUsername.getText(),6,erroreMinimoUsername);
    }*/




    private void togglePasswordVisibility() {
        boolean isPasswordVisible = textFieldPassword2.isVisible();
        textFieldPassword2.setVisible(!isPasswordVisible);
        textFieldPassword2.setManaged(!isPasswordVisible);
        textFieldPassword.setVisible(isPasswordVisible);
        textFieldPassword.setManaged(isPasswordVisible);
    }
}
