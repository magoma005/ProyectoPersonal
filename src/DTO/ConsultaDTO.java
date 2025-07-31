package DTO;

import java.io.Serializable;

public class ConsultaDTO implements Serializable {
    private String id;           // ID único de la consulta
    private String fecha;
    private String mascota;
    private String servicio;
    private String diagnostico;

    // Constructor completo con ID
    public ConsultaDTO(String id, String fecha, String mascota, String servicio, String diagnostico) {
        this.id = id;
        this.fecha = fecha;
        this.mascota = mascota;
        this.servicio = servicio;
        this.diagnostico = diagnostico;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getMascota() {
        return mascota;
    }

    public String getServicio() {
        return servicio;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    // Compatibilidad con versiones antiguas del sistema
    public String getMotivo() {
        return diagnostico;
    }

    public String getTratamiento() {
        return diagnostico;
    }

    // Por si necesitas mostrar el código como columna en la tabla
    public String getCodigo() {
        return id;
    }
}
