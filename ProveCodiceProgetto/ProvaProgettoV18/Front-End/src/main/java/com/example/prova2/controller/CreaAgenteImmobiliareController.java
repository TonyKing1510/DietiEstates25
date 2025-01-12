package com.example.prova2.controller;
import com.example.prova2.model.*;
import com.example.prova2.service.AgenteService;
import com.example.prova2.service.GestoreService;
import com.example.prova2.factory.AlertFactory;
import com.example.prova2.view.CreaGestore;
import com.example.prova2.view.DashboardAmministratore;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.Optional;

import static com.example.prova2.controller.CreaGestoreController.*;

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

    private static final String MSG_ERRORE_APERTURE_PAGINE = "Errore di navigazione";

    public void initialize(){
        this.adminCheStaCreandoAgente = DashboardAmministratoreController.getAdmin();
        azienda.setText(adminCheStaCreandoAgente.agenziaAppartenente.getNomeAgenzia());
        addListenerNome();
        addListenerCognome();
        addListenerCF();
        addListenerNumeroTelefono();
        addListenerVia();
        addListenerNumeroCivico();
        addListenerUsername();
        addListenerEmail();
        addListenerPassword();
        addListenerRiPassword();
    }


    private void addListenerRiPassword() {
        textFieldRiPassword.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.FALSE.equals(newValue) && (!isCampoVuotoOrNull(textFieldRiPassword.getText()))) {
                if(!textFieldPassword.getText().equals(textFieldRiPassword.getText())){
                    scriviMessaggioDiErrore(errorepasswordNonUguale,"Si prega di inserire due password uguali!");
                }
            }
        });
    }

    private void addListenerPassword() {
        textFieldPassword.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.FALSE.equals(newValue) && (!isCampoVuotoOrNull(textFieldPassword.getText()))) {
                checkPassword();
            }
        });
    }

    private void addListenerEmail() {
        textFieldEmail.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.FALSE.equals(newValue) && (!isCampoVuotoOrNull(textFieldEmail.getText()))) {
                checkEmail();
            }
        });
    }

    private void addListenerUsername() {
        textFieldUsername.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.FALSE.equals(newValue) && (!isCampoVuotoOrNull(textFieldUsername.getText()))) {
                checkUsername();
            }
        });
    }

    private void addListenerNumeroCivico() {
        textFieldNCivico.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.FALSE.equals(newValue) && (!isCampoVuotoOrNull(textFieldNCivico.getText()))) {
                checkNumeroCivico();
            }
        });
    }

    private void addListenerVia() {
        textFieldVia.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.FALSE.equals(newValue) && (!isCampoVuotoOrNull(textFieldVia.getText()))) {
                checkVia();
            }
        });
    }

    private void addListenerNumeroTelefono() {
        textFieldNumeroTelefono.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.FALSE.equals(newValue) && (!isCampoVuotoOrNull(textFieldNumeroTelefono.getText()))) {
                checkTelefono();
            }
        });
    }

    private void addListenerCF() {
        textFieldCf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.FALSE.equals(newValue) && (!isCampoVuotoOrNull(textFieldCf.getText()))) {
                checkCf();
            }
        });
    }

    private void addListenerCognome() {
        textFieldCognome.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.FALSE.equals(newValue) && (!isCampoVuotoOrNull(textFieldCognome.getText()))) {
                checkCognome();
            }
        });
    }

    private void addListenerNome() {
        textFieldNome.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.FALSE.equals(newValue) && (!isCampoVuotoOrNull(textFieldNome.getText()))) {
                checkNome();
            }
        });
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
            return AgenteService.addAgente(a);
        }catch (IOException e) {
            AlertFactory.generateFailAlert("Errore!","Errore nella creazione del Gestore!");
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AlertFactory.generateFailAlert("Errore!","Errore nell'aggiunta del gestore!");
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
            return CreaGestoreController.isCampoGiaRegistrato(cf, GestoreService.getAllCfGestore(), erroreCodiceFiscale, "Le chiediamo gentilmente di inserire un codice fiscale non ancora registrato.");
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
            return CreaGestoreController.isCampoGiaRegistrato(username, GestoreService.getAllUsernameGestore(), erroreUsername, "Le chiediamo gentilmente di inserire uno username non ancora registrato.");
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
            return CreaGestoreController.isCampoGiaRegistrato(email, GestoreService.getAllEmailGestore(), erroreEmail, "Le chiediamo gentilmente di inserire una mail non ancora registrata.");
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
        Alert alert = AlertFactory.generateAlertSuccess("Torna alla dashboard","Continua a creare altri agenti","Operazione avvenuta!","Complimenti hai creato il tuo agente con successo","Complimenti hai creato il tuo agente!Ti arriverà una notifica di conferma.Puoi sempre annullare la tua creazione nella pagina principale");
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
        if(password.equals(passwordRe)){
            disattivaMessaggioDiErrore(errorepasswordNonUguale);
        }
        return creaGestoreController.isValidCampo(password,CreaGestoreController.PASSWORDPATTERN,errorePassword,"una password",passwordLabel);
    }


    public boolean checkRePassword() {
        String password = textFieldPassword.getText();
        String passwordRinserita = textFieldRiPassword.getText();
        if(!password.equals(passwordRinserita)){
            errorepasswordNonUguale.setVisible(true);
            errorepasswordNonUguale.setText("Si prega di inserire due password uguali!");
            return false;
        }else{
            disattivaMessaggioDiErrore(errorepasswordNonUguale);
            return true;
        }
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


    public void mostraErroreLunghezzaPassword() {
        CreaGestoreController.attivaMessaggioMinimoCaratteri(textFieldPassword.getText(),8,erroreMinimoPassword);
    }

    public void mostraErroreLunghezzaUsername() {
        CreaGestoreController.attivaMessaggioMinimoCaratteri(textFieldUsername.getText(),6,erroreMinimoUsername);
    }

    public void disattivaErroreNome() {
        disattivaMessaggioDiErrore(erroreNome);
    }


    public void disattivaErroreCognome() {
        disattivaMessaggioDiErrore(erroreCognome);
    }

    public void disattivaErroreCF() {
        disattivaMessaggioDiErrore(erroreCodiceFiscale);
    }

    public void disattivaErroreTelefono() {
        disattivaMessaggioDiErrore(erroreNumeroTelefono);

    }

    public void disattivaErroreVia() {
        disattivaMessaggioDiErrore(erroreVia);

    }

    public void disattivaErroreNumeroCivico() {
        disattivaMessaggioDiErrore(erroreNumeroCivico);

    }

    public void disattivaErroreEmail() {
        disattivaMessaggioDiErrore(erroreEmail);

    }

    public void disattivaErroreUsername() {
        disattivaMessaggioDiErrore(erroreUsername);
    }

    public void disattivaErrorePassword() {
        disattivaMessaggioDiErrore(errorePassword);
    }

    public void disattivaErroreRiPassword() {
        disattivaMessaggioDiErrore(errorepasswordNonUguale);
    }
}
