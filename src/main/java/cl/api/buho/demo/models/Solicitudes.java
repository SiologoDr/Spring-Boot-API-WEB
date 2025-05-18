package cl.api.buho.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "solicitud")
public class Solicitudes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_solicitud;
    private String descripcion;

    private String observacion;
    private String prioridad;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Clientes cliente;

    @ManyToOne
    @JoinColumn(name = "id_tecnico")
    private Tecnicos tecnico;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estados estado;

    @ManyToOne
    @JoinColumn(name = "id_revision")
    private Revisiones revision;
}
