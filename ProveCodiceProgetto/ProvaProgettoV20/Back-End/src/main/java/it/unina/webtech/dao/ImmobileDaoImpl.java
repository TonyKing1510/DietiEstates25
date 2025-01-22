package it.unina.webtech.dao;

import it.unina.webtech.db.DatabaseConnection;
import it.unina.webtech.dto.ImmobileDTO;
import it.unina.webtech.model.Immobile;

import java.sql.*;

public class ImmobileDaoImpl implements ImmobileDao {
    private Connection connection;

    public ImmobileDaoImpl() throws SQLException {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ImmobileDTO addImmobile(Immobile immobile) {
        String sql = "insert into immobile(tipoImmobile,tipoVendita,descrizione,indirizzo,comune," +
                "n°civico,n°stanze,piano,classeenergetica,superficie,arredamento,prezzo,spesecondominiali,n°bagni," +
                "n°cucine,n°soggiorni,agenteproprietario,città) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            setSQL(immobile, ps);
            int nRows=ps.executeUpdate();
            if(nRows>0) {
                return new ImmobileDTO(true,false,false);
            }else{
                return new ImmobileDTO(false,true,true);
            }
        } catch (SQLException e) {
                e.printStackTrace();
                return new ImmobileDTO(false,true,true);
        }
    }


    private boolean isImmobileGiàInVendita(Immobile immobile) {
        String sql = "select 1 from immobile where lat = ? and long = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setDouble(1,immobile.getPosizioneGeografica().getLatitudine());
            ps.setDouble(2,immobile.getPosizioneGeografica().getLongitudine());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void setSQL(Immobile immobile, PreparedStatement ps) throws SQLException {
        try {
            aggiungiAttributiObbligatori(immobile, ps);

            if(immobile.getPosizioneGeografica()!=null){
                setOptionalString(ps, 4, immobile.getPosizioneGeografica().getVia());
                setOptionalString(ps, 5, immobile.getPosizioneGeografica().getComune());
                setOptionalString(ps, 6, immobile.getPosizioneGeografica().getNumeroCivico());
                setOptionalString(ps, 18, immobile.getPosizioneGeografica().getCittà());
            }
            else {
                ps.setNull(4,Types.VARCHAR);
                ps.setNull(5,Types.VARCHAR);
                ps.setNull(6,Types.VARCHAR);
                ps.setNull(18,Types.VARCHAR);
            }
            if(immobile.getDettagliStrutturali() != null) {
                setOptionalInt(ps, 7, immobile.getDettagliStrutturali().getNumeroStanze());
                setOptionalInt(ps, 8, immobile.getDettagliStrutturali().getPiano());
                setOptionalInt(ps, 10, immobile.getDettagliStrutturali().getSuperficie());
                setOptionalInt(ps, 14, immobile.getDettagliStrutturali().getNumeroBagni());
                setOptionalInt(ps, 15, immobile.getDettagliStrutturali().getNumeroCucine());
                setOptionalInt(ps, 16, immobile.getDettagliStrutturali().getNumeroSoggiorni());
            }else{
                ps.setNull(7,Types.INTEGER);
                ps.setNull(8,Types.INTEGER);
                ps.setNull(10,Types.INTEGER);
                ps.setNull(14,Types.INTEGER);
                ps.setNull(15,Types.INTEGER);
                ps.setNull(16,Types.INTEGER);
            }
            if(immobile.getDettagliVendita().getArredamento() != null) {
                setOptionalObject(ps, 11, immobile.getDettagliVendita().getArredamento());
            }else{
                setNullOther(ps);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void aggiungiAttributiObbligatori(Immobile immobile, PreparedStatement ps) throws SQLException {
        ps.setObject(1, immobile.getDettagliVendita().getTipoImmobile(), Types.OTHER);
        ps.setObject(2, immobile.getDettagliVendita().getTipoVendita(), Types.OTHER);
        ps.setString(3, immobile.getDescrizioneImmobile());
        ps.setObject(9, immobile.getClasseEnergetica(),Types.OTHER);
        ps.setFloat(12, immobile.getDettagliVendita().getPrezzo());
        ps.setFloat(13, immobile.getDettagliVendita().getSpeseCondominiali());
        ps.setString(17, immobile.getDettagliVendita().getAgenteProprietario().getCf());
    }


    private static void setNullOther(PreparedStatement ps) throws SQLException {
        ps.setNull(11, Types.OTHER);
    }


    private static void setOptionalString(PreparedStatement ps, int index, String value) throws SQLException {
        if (value != null) {
            ps.setString(index, value);
        } else {
            ps.setNull(index, Types.VARCHAR);
        }
    }

    private static void setOptionalInt(PreparedStatement ps, int index, Integer value) throws SQLException {
        if (value != null) {
            ps.setInt(index, value);
        } else {
            ps.setNull(index, Types.INTEGER);
        }
    }

    private static void setOptionalObject(PreparedStatement ps, int index, Object value) throws SQLException {
        if (value != null) {
            ps.setObject(index, value, Types.OTHER);
        } else {
            ps.setNull(index, Types.OTHER);
        }
    }

}
