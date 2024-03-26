package models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(generator = "u")
    @GenericGenerator(name = "u", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;
    private Date birthday;

    @OneToMany(mappedBy = "owner")
    private List<Cat> cats = new ArrayList<>();
}
