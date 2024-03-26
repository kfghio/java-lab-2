import org.example.models.Cat;
import org.example.models.Color;
import org.example.models.Owner;
import org.example.reps.CatRepository;
import org.example.reps.DataAccessObject;
import org.example.reps.OwnerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Tests {
    @Mock
    private final DataAccessObject<Cat, UUID> dataAccessObject1 = mock(DataAccessObject.class);

    @Test
    public void getCatById() {
        Color colorWhite = Color.builder().id(UUID.fromString("eb2a2c00-ddcb-4cd1-b1be-bb47b5b79cee")).color("WHITE").build();
        Owner owner = Owner.builder()
                .id(UUID.fromString("38509cb8-1510-4a01-a963-5be19b734843"))
                .name("Prosto Chel")
                .birthday(new Date())
                .build();
        Cat cat = Cat.builder()
                .id(UUID.fromString("d111084b-0677-4926-a749-01460141879a"))
                .name("ProstoCot")
                .breed("DevelMayCry")
                .color(colorWhite)
                .birthday(new Date())
                .owner(owner)
                .build();

        when(dataAccessObject1.getById(cat.getId())).thenReturn(Optional.of(new Cat()));
        Cat resievedCat = dataAccessObject1.getById(cat.getId()).orElse(null);
        verify(dataAccessObject1, times(1)).getById(cat.getId());
    }

    @Mock
    private final DataAccessObject<Color, UUID> dataAccessObject2 = mock(DataAccessObject.class);

    @Test
    public void getColorById() {
        UUID colorId = UUID.fromString("590f9533-13bd-4193-9961-d5e6f0e5f956");
        when(dataAccessObject2.getById(colorId)).thenReturn(Optional.of(new Color()));
        Color color = dataAccessObject2.getById(colorId).orElse(null);
        verify(dataAccessObject2, times(1)).getById(colorId);
    }

    @Mock
    private final DataAccessObject<Owner, UUID> dataAccessObject3 = mock(OwnerRepository.class);

    @Test
    public void getOwnerById() {
        Owner owner = Owner.builder()
                .id(UUID.fromString("38509cb8-1510-4a01-a963-5be19b734843"))
                .name("Prosto Chel")
                .birthday(new Date())
                .build();
        when(dataAccessObject3.getById(owner.getId())).thenReturn(Optional.of(new Owner()));
        Owner resievedOwner = dataAccessObject3.getById(owner.getId()).orElse(null);
        verify(dataAccessObject3, times(1)).getById(owner.getId());
    }

}
