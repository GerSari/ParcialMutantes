package GSari.ParcialMutantes.repositories;


import GSari.ParcialMutantes.entities.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface DnaRepository extends JpaRepository<Dna, Long> {

    Optional<Dna> findByDna(String dnaSequence);

    long countByIsMutant(boolean isMutant);
}
