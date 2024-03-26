package org.example.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "colors")
public class Color {
    @Id
    @GeneratedValue(generator = "u")
    @GenericGenerator(name = "u", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String color;

    @OneToMany(mappedBy = "color")
    private List<Cat> cats;
}
