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
@Table (name = "revision")
public class Revisiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_revision;

    private String revision;
    private String descripcion;
}
