package GSari.ParcialMutantes.dto;

import GSari.ParcialMutantes.validators.ValidDna;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DnaRequest {

    @ValidDna
    private String [] dna;
}
