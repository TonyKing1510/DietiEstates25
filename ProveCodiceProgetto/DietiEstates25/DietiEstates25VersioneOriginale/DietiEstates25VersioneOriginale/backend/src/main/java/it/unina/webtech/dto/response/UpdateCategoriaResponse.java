package it.unina.webtech.dto.response;

public class UpdateCategoriaResponse {
    boolean success;

    boolean cfExists;

    boolean nessunaNotificaTrovata;

    public UpdateCategoriaResponse(boolean success, boolean cfExists) {
        this.success = success;
        this.cfExists = cfExists;
    }

    public UpdateCategoriaResponse(boolean nessunaNotificaTrovata) {
        this.nessunaNotificaTrovata = nessunaNotificaTrovata;
    }

    public void setNessunaNotificaTrovata(boolean nessunaNotificaTrovata) {
        this.nessunaNotificaTrovata = nessunaNotificaTrovata;
    }

    public boolean isNessunaNotificaTrovata() {
        return nessunaNotificaTrovata;
    }

    public void setCfExists(boolean cfExists) {
        this.cfExists = cfExists;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isCfExists() {
        return cfExists;
    }

    public boolean isSuccess() {
        return success;
    }
}
