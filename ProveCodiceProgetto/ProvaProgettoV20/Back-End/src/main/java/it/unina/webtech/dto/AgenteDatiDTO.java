package it.unina.webtech.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgenteDatiDTO {
        @JsonProperty("nome")
        private String nome;

        @JsonProperty("cognome")
        private String cognome;

        @JsonProperty("telefono")
        private String telefono;

        @JsonProperty("email")
        private String email;

        @JsonProperty("partitaIva")
        private String partitaIva;

        @JsonProperty("bio")
        private String bio;

        @JsonProperty("cf")
        private String cf;
        // Costruttore
        public AgenteDatiDTO(String nome, String cognome, String email, String telefono, String partitaIva, String bio,String cf) {
            this.nome = nome;
            this.cognome = cognome;
            this.email = email;
            this.telefono = telefono;
            this.partitaIva = partitaIva;
            this.bio = bio;
            this.cf = cf;
        }

        public AgenteDatiDTO() {}

        public String getNome() {
            return nome;
        }
        public void setNome(String nome) {
            this.nome = nome;
        }
        public String getCognome() {
            return cognome;
        }
        public void setCognome(String cognome) {
            this.cognome = cognome;
        }
        public String getTelefono() {
            return telefono;
        }
        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getPartitaIva() {
            return partitaIva;
        }
        public void setPartitaIva(String partitaIva) {
            this.partitaIva = partitaIva;
        }
        public String getBio() {
            return bio;
        }
        public void setBio(String bio) {
            this.bio = bio;
        }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }
}


