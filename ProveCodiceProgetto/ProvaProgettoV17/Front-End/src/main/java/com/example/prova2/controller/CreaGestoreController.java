package com.example.prova2.controller;
import com.example.prova2.exception.CampoVuotoException;
import com.example.prova2.exception.LunghezzaMassimaException;
import com.example.prova2.exception.LunghezzaMinimaException;
import com.example.prova2.model.AccountGestore;
import com.example.prova2.model.GestoreAgenziaImmobiliare;
import com.example.prova2.service.AddGestoreService;
import com.example.prova2.service.GestoreCFService;
import com.example.prova2.service.GestoreMailService;
import com.example.prova2.service.GestoreUsernameService;
import com.example.prova2.utility.AlertFactory;
import com.example.prova2.view.CreaGestore;
import com.example.prova2.view.DashboardAmministratore;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class CreaGestoreController {
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
    public TextField textFieldPassword2;

    @FXML
    public Button buttonPassword;

    protected static final String MESSAGEERROR = "Si prega di inserire ";

    protected static final String COGNOMEMSG = "un cognome";

    protected static final String CFPATTERN = "^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$";

    protected static final String TELEFONOPATTERN = "^[0-9]{10}$";

    protected static final String NUMEROCIVICOPATTERN = "^\\d+\\s*[-a-zA-Z]*$";

    protected static final String EMAILPATTERN="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    protected static final String PASSWORDPATTERN = "^.{8,}$";

    protected static final String USERNAMEPATTERN = "^.{6,}$";

    protected static final String UNNOME = "un nome";

    protected static final String UNAVIA = "una via";
    @FXML
    public AnchorPane labelDati;

    @FXML
    public PasswordField textFieldRiPassword;

    @FXML
    public Button btnIndietro;

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
    public Label lunghezzaPass;

    @FXML
    public Label lunghezzaUsername;


    public static final boolean INITIAL_VALUE_FLAG = true;



    public  void salvaDati() {
        if(isControlliPassati()) {
            gestisciInvioDati();
        }
    }

    private void gestisciInvioDati() {
        inviaDati();
    }

    private void inviaDati() {
        if(salvaDatiConSuccesso()) {
            mostraPopUpConferma();
        }else{
            AlertFactory.generateFailAlert("Errore nella creazione del gestore !","Siamo spiacenti si " +
                    "è verificato un errore interno nel sistema si prega di riprovare!");
        }
    }

    private boolean salvaDatiConSuccesso() {
        GestoreAgenziaImmobiliare gestore = new GestoreAgenziaImmobiliare(textFieldNome.getText(),textFieldCognome.getText()
                ,textFieldCf.getText(),textFieldNumeroTelefono.getText(),textFieldVia.getText(),textFieldNCivico.getText());
        AccountGestore accountGestore = new AccountGestore(textFieldUsername.getText(),textFieldPassword.getText()
                ,textFieldEmail.getText());
        gestore.setAccountGestore(accountGestore);


        GestoreAgenziaImmobiliare g = DashboardAmministratoreController.getAdmin();
        gestore.setAdminAppartenente(g);
        try {
            return AddGestoreService.addGestore(gestore);
        }catch (IOException e) {
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }



    private boolean isControlliPassati() {
        return checkCampi() && (controllaSeCiSonoDuplicati());
    }

    private boolean controllaSeCiSonoDuplicati() {
        return checkDuplicati(textFieldEmail.getText(), textFieldUsername.getText(), textFieldCf.getText());
    }



    private void mostraPopUpConferma() {
        Alert alert = generateSuccessMessage();
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get().getText().equals("Torna alla dashboard")) {
                navigaAllaDashboard();
            } else if (result.get().getText().equals("Continua a creare altri gestori")) {
                navigaACreaGestore();
            }
        }
    }


    public static Alert generateSuccessMessage(){
        return AlertFactory.generateAlertSuccess("Torna alla dashboard","Continua a creare altri agenti","Operazione completata!","Hai creato con successo il tuo agente!","Complimenti ti arriverà una notifica di riepilogo, ricordati che puoi sempre cancellare i gestori creati nella tua dashboard");
    }

    private void navigaAllaDashboard() {
        try {
            DashboardAmministratore.initializePageDashboardAmministratore(bottoneSalvaDatiAna.getScene().getWindow());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AlertFactory.generateFailAlert("Operazione interrotta", "Il thread è stato interrotto durante l'operazione.");
        } catch (IOException e) {
            AlertFactory.generateFailAlert("Errore di navigazione", "Si è verificato un problema durante il caricamento della dashboard.");
        }
    }

    private void navigaACreaGestore() {
        try {
            CreaGestore.initializePageCreaGestore(bottoneSalvaDatiAna.getScene().getWindow());
        } catch (Exception e) {
            AlertFactory.generateFailAlert("Errore di navigazione", "Si è verificato un problema durante il caricamento della pagina di creazione gestori.");
        }
    }

    private static boolean exists(List<String> list, String object) {
        boolean exists = false;
        for (String item : list) {
            if (item.equals(object)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    private boolean checkCampi() {
        boolean flag = true;
        flag &= checkDatiAccount();
        flag &= checkDatiAnagrafici();
        return flag;
    }

    private boolean checkDatiAnagrafici() {
        boolean flag=INITIAL_VALUE_FLAG;
        flag &= checkNome();
        flag &= checkCognome();
        flag &= checkCf();
        flag &= checkTelefono();
        flag &= checkVia();
        flag &= checkNumeroCivico();
        return flag;

    }

    private boolean checkDatiAccount() {
        boolean flag = true;
        flag &= checkEmail();
        flag&= checkPassword();
        flag&= checkUsername();
        flag&= checkRePassword();
        return flag;
    }


    public boolean checkEmail(){
        String email = textFieldEmail.getText();
        return isValidCampo(email, EMAILPATTERN, erroreEmail, "una email", emailLabel);
    }

    public boolean checkUsername(){
        String username = textFieldUsername.getText();
        return isValidCampo(username, USERNAMEPATTERN, erroreUsername, "uno username", usernameLabel);
    }


    public boolean checkPassword() {
        String password = textFieldPassword.getText();
        String passwordRe = textFieldRiPassword.getText();
        isValidCampo(password,passwordRe,errorepasswordNonUguale,"due password uguali!",passwordReLabel);
        return isValidCampo(password,PASSWORDPATTERN,errorePassword,"una password",passwordLabel);
    }


    public boolean isValidCampo(String campo,String patternForValidate,Label errorLabel,String errorMessage,Label label) {
        if(isCampoVuotoOrNull(campo)) {
            errorLabel.setVisible(true);
            errorLabel.setText(MESSAGEERROR+errorMessage);
            return false;
        }
        else if(isCampoInvalido(campo, patternForValidate)){
            errorLabel.setVisible(true);
            scriviMesErroreMaschileOFemminile(label.getText(), errorLabel, errorMessage,label);
            return false;
        }
        else{
            disattivaMessaggioDiErrore(errorLabel);
            return true;
        }
    }


    public boolean checkRePassword() {
        String password = textFieldPassword.getText();
        String passwordRinserita = textFieldRiPassword.getText();
        return isValidCampo(password,passwordRinserita,errorepasswordNonUguale,"due password uguali!",passwordReLabel);
    }



    public Boolean checkNome() {
        return controllaCampiAnagrafici(textFieldNome, erroreNome, UNNOME);
    }

    private void disattivaMessaggioDiErrore(Label label) {
        label.setVisible(false);
        label.setText("");
    }

    public boolean checkCognome() {
        return controllaCampiAnagrafici(textFieldCognome, erroreCognome, COGNOMEMSG);
    }

    public boolean checkCf() {
        String cf = textFieldCf.getText();
        return isValidCampo(cf, CFPATTERN, erroreCodiceFiscale, "un codice fiscale", cflabel);
    }


    private static void scriviMessaggioDiErrore(Label errore, String message) {
        errore.setText(message);
        errore.setVisible(true);
    }



    private void scriviMesErroreMaschileOFemminile(String campo, Label errorLabel, String errorMessage,Label label) {
        if(isMaschile(campo)) {
                errorLabel.setText(MESSAGEERROR + errorMessage + " valido!");
        }
        else{
            if(label.getText().equals("Riscrivi Password")) {
                errorLabel.setText(MESSAGEERROR + errorMessage + " !");
            }
            else{
                errorLabel.setText(MESSAGEERROR + errorMessage + " valida!");
            }
        }
    }

    private static boolean isCampoInvalido(String campo, String patternForValidate) {
        return !campo.matches(patternForValidate);
    }

    private static boolean isCampoVuotoOrNull(String campo) {
        return campo == null || campo.isEmpty();
    }

    private boolean isMaschile(String s) {
        return !s.endsWith("a") && !s.endsWith("A") && !s.endsWith("d") && !s.endsWith("D") && !s.endsWith("l") && !s.endsWith("L");
    }

    public boolean checkTelefono() {
        String telefono = textFieldNumeroTelefono.getText();
        return isValidCampo(telefono,TELEFONOPATTERN,erroreNumeroTelefono,"un numero di telefono",numeroTelefonoLabel);
    }

    public boolean checkVia() {
        return controllaCampiAnagrafici(textFieldVia, erroreVia, UNAVIA);
    }

    public boolean controllaCampiAnagrafici(TextField textFieldVia, Label erroreVia, String unavia) {
        try {
            String via = textFieldVia.getText();
            campoVuoto(via, erroreVia, unavia);
            lunghezzaMinima(via, erroreVia, unavia);
            lunghezzaMaggiore(via, erroreVia, unavia);
            disattivaMessaggioDiErrore(erroreVia);
            return true;
        }catch(CampoVuotoException | LunghezzaMassimaException | LunghezzaMinimaException e){
            return false;
        }
    }

    public boolean checkNumeroCivico(){
        String numeroCivico = textFieldNCivico.getText();
        return isValidCampo(numeroCivico,NUMEROCIVICOPATTERN,erroreNumeroCivico,"un numero civico",numeroCivicoLabel);
    }

    private void lunghezzaMaggiore(String nome,Label label,String message) {
        if(nome.length() > 20){
            label.setVisible(true);
            label.setText(MESSAGEERROR+message+" con lunghezza minore");
            throw new LunghezzaMassimaException("Parola Troppo Grande");
        }
    }

    private void lunghezzaMinima(String nome,Label label,String message) {
        if (nome.length() < 2) {
            label.setVisible(true);
            label.setText(MESSAGEERROR+" "+message+" "+" con lunghezza maggiore");
            throw new LunghezzaMassimaException("Parola Troppo piccola");
        }
    }

    private void campoVuoto(String campo, Label label, String message) throws CampoVuotoException {
        if (campo.isEmpty()) {
            label.setVisible(true);
            label.setText(MESSAGEERROR+message);
            throw new CampoVuotoException();
        }
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
            return isCampoGiaRegistrato(cf, GestoreCFService.getAllCfGestore(), erroreCodiceFiscale, "Le chiediamo gentilmente di inserire un codice fiscale non ancora registrato.");
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
            return isCampoGiaRegistrato(username, GestoreUsernameService.getAllUsernameGestore(), erroreUsername, "Le chiediamo gentilmente di inserire uno username non ancora registrato.");
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
            return isCampoGiaRegistrato(email, GestoreMailService.getAllEmailGestore(), erroreEmail, "Le chiediamo gentilmente di inserire una mail non ancora registrata.");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public static boolean isCampoGiaRegistrato(String campo, List<String> campi, Label errore, String message){
        return isGiaEsistente(campo,campi,errore, message);
    }

    private static boolean isGiaEsistente(String campo,List<String> lista,Label errore,String message)
    {
        if(exists(lista,campo)){
            scriviMessaggioDiErrore(errore,message);
            return false;
        }
        return true;
    }


    public void tornaIndietro() throws IOException, InterruptedException {
        DashboardAmministratore.initializePageDashboardAmministratore(btnIndietro.getScene().getWindow());
    }

    @FXML
    public void initialize() throws IOException, InterruptedException {
        // Inizializza i due campi
        textFieldPassword2.setManaged(false); // Nasconde il TextField alla partenza
        textFieldPassword2.setVisible(false);
        textFieldPassword2.textProperty().bindBidirectional(textFieldPassword.textProperty());

        // Imposta l'evento per alternare i campi
        buttonPassword.setOnAction(event -> togglePasswordVisibility());
    }

    private void togglePasswordVisibility() {
        boolean isPasswordVisible = textFieldPassword2.isVisible();
        textFieldPassword2.setVisible(!isPasswordVisible);
        textFieldPassword2.setManaged(!isPasswordVisible);
        textFieldPassword.setVisible(isPasswordVisible);
        textFieldPassword.setManaged(isPasswordVisible);
    }


}
