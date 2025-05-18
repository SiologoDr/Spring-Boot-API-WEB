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
@Table (name = "asignacion")
public class Asignaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_asignacion;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_solicitud")
    private Solicitudes solicitud;

    @ManyToOne
    @JoinColumn(name = "id_desarrollador")
    private Desarrolladores desarrollador;
}
