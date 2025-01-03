/*
CREATE DOMAIN email_address AS VARCHAR(255)
CHECK (
    VALUE ~ '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'
);

CREATE DOMAIN partita_iva AS CHAR(11)
CHECK (
    VALUE ~ '^[0-9]{11}$'
);


CREATE TYPE TipoImmobile AS ENUM (
    'RESIDENZIALE',
    'APPARTAMENTO',
    'ATTICO',
    'OPENSPACE',
    'LOFT',
    'MANSARDA',
    'VILLA',
	'BAITA'
);


*/

/*
CREATE TYPE TipoVendita AS ENUM (
    'VENDITA',
    'AFFITTO'
);


CREATE TYPE Arredamento AS ENUM (
    'NON ARREDATO',
    'ARREDATO',
	'PARZIALMENTE ARREDATO'
);

CREATE TYPE Categoria AS ENUM (
    'SPAM',
    'VISITAIMMOBILE',
	'ESITO RICHIESTA'
);


*/



/*
CREATE TABLE Cliente
(
	CF varchar(16),
	nome varchar(256),
	cognome varchar(256),
	telefono int,
	via varchar(20),
	numeroCivico int,
	username varchar(256),
	password varchar(256),
	email email_address,
	datareg date,
	orareg time,
	primary key(CF)
);
*/

/*
CREATE TABLE AGENZIA
(
	nomeAgenzia varchar(100),
	località varchar(10),
	primary key(nomeAgenzia,località)
);
*/

/*
CREATE TABLE GESTOREAGENZIAIMMOBILIARE(
	nome varchar(200) NOT NULL,
	cognome varchar(200) NOT NULL,
	cf varchar(16),
	telefono int NOT NULL,
	via varchar(100) NOT NULL,
	n°civico int NOT NULL,
	username varchar(256) NOT NULL,
	password varchar(256) NOT NULL,
	email email_address NOT NULL,
	partitaIva partita_iva NOT NULL,
	admin bool,
	gestoriCreati varchar(16),
	nomeAgenzia varchar(100),
	località varchar(10),
	primary key(cf),
	CONSTRAINT fk_gestore_agenzia_immobiliare_agenzia
	FOREIGN KEY (nomeAgenzia, località)
        REFERENCES Agenzia (nomeAgenzia, località)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	
	CONSTRAINT fk_gestore_agenzia_immobiliare_self
	FOREIGN KEY (gestoriCreati)
        REFERENCES GESTOREAGENZIAIMMOBILIARE (cf)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
*/


/*
CREATE TABLE AGENTE
(
	cf varchar(16),
	nome varchar(256),
	cognome varchar(256),
	telefono int,
	via varchar(20),
	numeroCivico int,
	username varchar(256),
	password varchar(256),
	email email_address,
	partitaIva partita_iva,
	gestoreRiferimento varchar(16),
	primary key(cf),
	CONSTRAINT fk_agente
	FOREIGN KEY (gestoreRiferimento)
        REFERENCES GESTOREAGENZIAIMMOBILIARE (cf)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
*/

/*
CREATE TABLE RECENSIONE
(
	id_recensione SERIAL,
	commentoRecensione text,
	valutazione int,
	clienteCheHaLasciatoRecensione varchar(16),
	agenteRiferimento varchar(16),
	primary key(id_recensione),
	CONSTRAINT fk_recensione_cliente
	FOREIGN KEY (clienteCheHaLasciatoRecensione)
        REFERENCES CLIENTE (cf)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT fk_recensione_agente
	FOREIGN KEY (agenteRiferimento)
        REFERENCES AGENTE (cf)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
*/


/*
CREATE TABLE IMMOBILE
(
	id_immobile SERIAL,
	tipoImmobile TipoImmobile,
	tipoVendita TipoVendita,
	foto varchar(256),
	descrizione text,
	indirizzo varchar(256),
	comune varchar(256),
	n°civico int,
	n°stanze int,
	piano int,
	classeEnergetica varchar(256),
	superficie int,
	arredamento Arredamento,
	prezzo int,
	speseCondominiali int,
	n°bagni int,
	n°cucine int,
	n°soggiorni int,
	agenteProprietario varchar(16),
	primary key(id_immobile),
	CONSTRAINT fk_immobile
	FOREIGN KEY (agenteProprietario)
        REFERENCES AGENTE (cf)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
*/

/*
CREATE TABLE VISITA
(
	id_visita SERIAL,
	dataVisita date,
	oraVisita time,
	accettata bool,
	descrizione text,
	ClientePrenotatoVisita varchar(16),
	AgenteRiferimento varchar(16),
	ImmobileVisita SERIAL,
	primary key(id_visita),
	CONSTRAINT fk_visita_cliente
	FOREIGN KEY (ClientePrenotatoVisita)
        REFERENCES Cliente (cf)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	
	CONSTRAINT fk_visita_agente
	FOREIGN KEY (AgenteRiferimento)
        REFERENCES Agente (cf)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	
	CONSTRAINT fk_visita_immobile
	FOREIGN KEY (ImmobileVisita)
        REFERENCES Immobile (id_immobile)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
*/



/*
CREATE TABLE Notifica
(
	id_notifica serial,
	titolo varchar(256),
	contenuto text,
	categoria Categoria,
	accettata bool,
	clienteNotificato varchar(16),
	agenteNotificato varchar(16),
	gestoreNotificato varchar(16),
	clienteProprietarioNotifica varchar(16),
	agenteProprietarioNotifica varchar(16),
	gestoreProprietarioNotifica varchar(16),
	
	CONSTRAINT fk_notifica_clienteNotificato
	FOREIGN KEY (clienteNotificato)
        REFERENCES Cliente (cf)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	
	CONSTRAINT fk_notifica_agenteNotificato
	FOREIGN KEY (agenteNotificato)
        REFERENCES Agente (cf)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	
	CONSTRAINT fk_notifica_gestoreNotificato
	FOREIGN KEY (gestoreNotificato)
        REFERENCES GESTOREAGENZIAIMMOBILIARE (cf)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	
	
	CONSTRAINT fk_notifica_clienteProprietario
	FOREIGN KEY (clienteProprietarioNotifica)
        REFERENCES Cliente (cf)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	
	CONSTRAINT fk_notifica_agenteProprietario
	FOREIGN KEY (agenteProprietarioNotifica)
        REFERENCES Agente (cf)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	
	CONSTRAINT fk_notifica_gestoreProprietario
	FOREIGN KEY (gestoreProprietarioNotifica)
        REFERENCES GESTOREAGENZIAIMMOBILIARE (cf)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
*/

/*
CREATE TABLE RICERCA
(
	id_ricerca SERIAL,
	titolo varchar(200),
	data date,
	ora time,
	prezzoMin int,
	prezzoMax int,
	n°stanze int,
	classeEnergetica varchar(200),
	località varchar(200),
	viaRicercata varchar(20),
	primary key(id_ricerca)
);
*/


/*
CREATE TABLE CLIENTE_RICERCA
(
	cliente varchar(16),
	ricerca serial,
	primary key(cliente,ricerca),
	CONSTRAINT fk_cliente_ricerca_1
	FOREIGN KEY (cliente)
        REFERENCES CLIENTE (cf)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	
	CONSTRAINT fk_cliente_ricerca_2
	FOREIGN KEY (ricerca)
        REFERENCES Ricerca (id_ricerca)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
*/

/*
CREATE TABLE AGENTE_RICERCA
(
	agente varchar(16),
	ricerca serial,
	primary key(agente,ricerca),
	CONSTRAINT fk_agente_ricerca_1
	FOREIGN KEY (agente)
        REFERENCES AGENTE (cf)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	
	CONSTRAINT fk_agente_ricerca_2
	FOREIGN KEY (ricerca)
        REFERENCES Ricerca (id_ricerca)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
*/


/*
CREATE TABLE GESTORE_RICERCA
(
	gestore varchar(16),
	ricerca serial,
	primary key(gestore,ricerca),
	CONSTRAINT fk_gestore_ricerca_1
	FOREIGN KEY (gestore)
        REFERENCES GESTOREAGENZIAIMMOBILIARE (cf)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	
	CONSTRAINT fk_gestore_ricerca_2
	FOREIGN KEY (ricerca)
        REFERENCES Ricerca (id_ricerca)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
*/


select *
from cliente



