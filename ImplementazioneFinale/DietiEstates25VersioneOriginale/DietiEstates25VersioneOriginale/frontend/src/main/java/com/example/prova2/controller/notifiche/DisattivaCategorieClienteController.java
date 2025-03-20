package com.example.prova2.controller.notifiche;
import com.example.prova2.controller.dashBoard.DashBoardClienteController;
import com.example.prova2.facade.NotificaServiceFacade;
import com.example.prova2.factory.AlertFactory;
import com.example.prova2.model.CategoriaNotifica;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DisattivaCategorieClienteController {
    @FXML
    public CheckBox checkBoxesitoCategoria;
    @FXML
    public Label esitoCategoriaVisita;
    @FXML
    public Label consigliImmobileCategoria;
    @FXML
    public CheckBox checkBoxConsiglioImmobile;
    @FXML
    public Pane paneConsiglioImmobile;
    @FXML
    public Pane paneEsitoViste;
    @FXML
    public ImageView immagineNoNot;
    @FXML
    public Label scrittaNoNot;
    @FXML
    public Label scrittaNoNot2;
    @FXML
    public Label scrittaInAlto;
    @FXML
    public Button btnCancellaCategorie;
    @FXML
    public Button btnAggiorna;
    @FXML
    public ImageView logoCar;
    @FXML
    public Label scrittaCar;

    HashMap<Label, CheckBox> mapForLabelCheckBox = new HashMap<>();

    HashMap<Label,Pane> mapForPaneLabel= new HashMap<>();

    VisualizzaNotificheClienteController controllerPaginaPrecedente;

    public void initCategoriDisattivate(VisualizzaNotificheClienteController visualizzaNotificheController){
        this.controllerPaginaPrecedente = visualizzaNotificheController;
        checkBoxesitoCategoria.setSelected(true);
        checkBoxConsiglioImmobile.setSelected(true);
        mapForLabelCheckBox = makeHashMapForLabelCheckBox();
        List<Label> categorieDisponibili=initArrayCategorieDisponibili();
        new Thread(()->{
            List<CategoriaNotifica> categorieDisattivate = NotificaServiceFacade.getCategorieDisattivateCliente(DashBoardClienteController.getCliente());
            Platform.runLater(()->{
                initCategorie(categorieDisattivate, categorieDisponibili, mapForLabelCheckBox);
            });
        }).start();
    }

    private void initCategorie(List<CategoriaNotifica> categorieDisattivate, List<Label> categorieDisponibili, HashMap<Label, CheckBox> map) {
        List<CategoriaNotifica> categorieRecuperate= NotificaServiceFacade.getCategorieDispCliente(DashBoardClienteController.getCliente().getAccountAgente().getEmail(),DashBoardClienteController.getCliente().getToken());
        gestisciCasoNoCategorie(categorieRecuperate);
        if(!categorieRecuperate.isEmpty()){
            scrittaCar.setVisible(false);
            logoCar.setVisible(false);
            scrittaInAlto.setVisible(true);
            btnAggiorna.setVisible(true);
        }
        attivaPane(categorieRecuperate,categorieDisponibili);
        spostaPaneSeUnoSolo(categorieRecuperate);

        for (CategoriaNotifica categoria : categorieDisattivate) {
            for (Label categoriaDisponibile : categorieDisponibili) {
                if(isCategoriaDisattivataUgualeCategoriaDisponibile(categoria, categoriaDisponibile)){
                    map.get(categoriaDisponibile).setSelected(false);
                }
            }
        }
    }

    private void spostaPaneSeUnoSolo(List<CategoriaNotifica> categorieRecuperate) {
        if(categorieRecuperate.size() == 1 && (categorieRecuperate.getFirst().getLabel().equals("ESITOVISITA"))){
                mapForPaneLabel.get(esitoCategoriaVisita).setLayoutY(200);
        }
    }

    private void gestisciCasoNoCategorie(List<CategoriaNotifica> categorieRecuperate) {
        if(categorieRecuperate.isEmpty()){
            scrittaCar.setVisible(false);
            logoCar.setVisible(false);
            scrittaInAlto.setVisible(true);
            btnAggiorna.setVisible(true);

            scrittaInAlto.setVisible(false);
            btnAggiorna.setVisible(false);
            scrittaNoNot.setVisible(true);
            scrittaNoNot2.setVisible(true);
            immagineNoNot.setVisible(true);
        }
    }

    private void attivaPane(List<CategoriaNotifica> categorieRecuperate, List<Label> categorieDisponibili) {
        for (CategoriaNotifica categoria : categorieRecuperate) {
            for (Label categoriaDisponibile : categorieDisponibili) {
                if(confrontoCategorie(categoriaDisponibile.getText(),categoria.getLabel())){
                    Pane disattivaPannello=mapForPaneLabel.get(categoriaDisponibile);
                    disattivaPannello.setVisible(true);
                }
            }
        }
    }

    private HashMap<Label, CheckBox> makeHashMapForLabelCheckBox() {
        HashMap<Label,CheckBox> map = new HashMap<>();
        map.put(consigliImmobileCategoria, checkBoxConsiglioImmobile);
        map.put(esitoCategoriaVisita, checkBoxesitoCategoria);
        return map;
    }

    private boolean isCategoriaDisattivataUgualeCategoriaDisponibile(CategoriaNotifica categoria, Label categoriaDisponibile) {
        return confrontoCategorie(categoria.getLabel(), categoriaDisponibile.getText());
    }

    private boolean confrontoCategorie(String label, String text) {
        String labelNormalized = label.toLowerCase().replaceAll("\\s+", "");
        String textNormalized = text.toLowerCase().replaceAll("\\s+", "");
        return labelNormalized.equals(textNormalized);
    }

    private List<Label> initArrayCategorieDisponibili() {
        List<Label> categorieDisponibili= new ArrayList<>();
        categorieDisponibili.add(consigliImmobileCategoria);
        categorieDisponibili.add(esitoCategoriaVisita);
        mapForPaneLabel.put(consigliImmobileCategoria,paneConsiglioImmobile);
        mapForPaneLabel.put(esitoCategoriaVisita,paneEsitoViste);
        return categorieDisponibili;
    }

    public void aggiornaCategorie() {
        aggiornaCategorieInBaseAllePreferenze();
    }

    private void aggiornaCategorieInBaseAllePreferenze() {
        for (Label categoria : mapForLabelCheckBox.keySet()) {
            if(isPaneVisibile(categoria)) {
                if (mapForLabelCheckBox.get(categoria).isSelected()) {
                    NotificaServiceFacade.attivaCategoriaPerCliente(categoria.getText(), DashBoardClienteController.getCliente().getAccountAgente().getEmail(),DashBoardClienteController.getCliente().getToken());
                } else {
                    NotificaServiceFacade.disattivaCategoriaPerCliente(categoria.getText(), DashBoardClienteController.getCliente().getAccountAgente().getEmail(),DashBoardClienteController.getCliente().getToken());
                }
            }
        }
        controllerPaginaPrecedente.init();
        AlertFactory.generateAlertForAggiornaCategoria();
    }

    private boolean isPaneVisibile(Label categoria) {
        return mapForPaneLabel.get(categoria).isVisible();
    }

    public void annullaCategorie() {

    }
}
