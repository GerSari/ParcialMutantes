package GSari.ParcialMutantes.services;

import GSari.ParcialMutantes.entities.Dna;
import GSari.ParcialMutantes.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DnaService {

    private final DnaRepository dnaRepository;
    private static final int SEQUENCE_LENGTH = 4;

    @Autowired
    public DnaService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public boolean isMutant(String[] dna) {
        int n = dna.length;
        int sequenceCount = 0;

        // Verificamos filas
        sequenceCount += horizontalesCH(dna, n);
        if (sequenceCount > 1) return true;

        // Verificamos columnas
        sequenceCount += verticalesCH(dna, n);
        if (sequenceCount > 1) return true;

        // Verificamos diagonales principales
        sequenceCount += diagonalesPrincipalesCH(dna, n);
        if (sequenceCount > 1) return true;

        // Verificamos diagonales secundarias
        sequenceCount += diagonalesSecundariasCH(dna, n);
        return sequenceCount > 1;
    }

    private int horizontalesCH(String[] dna, int n) {
        int sequenceCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n - SEQUENCE_LENGTH; j++) {
                if (horSequence(dna[i], j, SEQUENCE_LENGTH)) {
                    sequenceCount++;
                    if (sequenceCount > 1) return sequenceCount;
                }
            }
        }
        return sequenceCount;
    }

    private int verticalesCH(String[] dna, int n) {
        int sequenceCount = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= n - SEQUENCE_LENGTH; i++) {
                if (verSequence(dna, i, j, SEQUENCE_LENGTH)) {
                    sequenceCount++;
                    if (sequenceCount > 1) return sequenceCount;
                }
            }
        }
        return sequenceCount;
    }

    private int diagonalesPrincipalesCH(String[] dna, int n) {
        int sequenceCount = 0;
        for (int i = 0; i <= n - SEQUENCE_LENGTH; i++) {
            for (int j = 0; j <= n - SEQUENCE_LENGTH; j++) {
                if (diagSequence(dna, i, j, 1, 1, SEQUENCE_LENGTH)) {
                    sequenceCount++;
                    if (sequenceCount > 1) return sequenceCount;
                }
            }
        }
        return sequenceCount;
    }

    private int diagonalesSecundariasCH(String[] dna, int n) {
        int sequenceCount = 0;
        for (int i = 0; i <= n - SEQUENCE_LENGTH; i++) {
            for (int j = SEQUENCE_LENGTH - 1; j < n; j++) {
                if (diagSequence(dna, i, j, 1, -1, SEQUENCE_LENGTH)) {
                    sequenceCount++;
                    if (sequenceCount > 1) return sequenceCount;
                }
            }
        }
        return sequenceCount;
    }

    private boolean horSequence(String row, int start, int length) {
        char firstChar = row.charAt(start);
        for (int i = 1; i < length; i++) {
            if (row.charAt(start + i) != firstChar) {
                return false;
            }
        }
        return true;
    }

    private boolean verSequence(String[] dna, int startRow, int col, int length) {
        char firstChar = dna[startRow].charAt(col);
        for (int i = 1; i < length; i++) {
            if (dna[startRow + i].charAt(col) != firstChar) {
                return false;
            }
        }
        return true;
    }

    private boolean diagSequence(String[] dna, int startRow, int startCol, int rowIncrement, int colIncrement, int length) {
        char firstChar = dna[startRow].charAt(startCol);
        for (int i = 1; i < length; i++) {
            int newRow = startRow + i * rowIncrement;
            int newCol = startCol + i * colIncrement;
            if (newRow >= dna.length || newCol < 0 || newCol >= dna.length || dna[newRow].charAt(newCol) != firstChar) {
                return false;
            }
        }
        return true;
    }

    public boolean analyzeDna(String[] dna) {
        String dnaSequence = String.join(",", dna);

        // Verificar si el ADN ya existe en la base de datos
        Optional<Dna> existingDna = dnaRepository.findByDna(dnaSequence);
        if (existingDna.isPresent()) {
            return existingDna.get().isMutant();
        }

        // Determinamos si el ADN es mutante y lo guardamos en la base de datos
        boolean isMutant = isMutant(dna);
        Dna dnaEntity = new Dna(dnaSequence, isMutant);
        dnaRepository.save(dnaEntity);

        return isMutant;
    }
}
