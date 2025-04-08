package lucas.notas.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Notas implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public Notas() {
        // Construtor padrão exigido pelo JPA
    }

    public Notas(byte nota1, byte nota2, byte nota3, byte nota4) {
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
    }

    private byte nota1;
    private byte nota2;
    private byte nota3;
    private byte nota4;

    public byte getNota1() { return nota1; }
    public void setNota1(byte nota1) { this.nota1 = nota1; }

    public byte getNota2() { return nota2; }
    public void setNota2(byte nota2) { this.nota2 = nota2; }

    public byte getNota3() { return nota3; }
    public void setNota3(byte nota3) { this.nota3 = nota3; }

    public byte getNota4() { return nota4; }
    public void setNota4(byte nota4) { this.nota4 = nota4; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notas notas = (Notas) o;
        return nota1 == notas.nota1 &&
               nota2 == notas.nota2 &&
               nota3 == notas.nota3 &&
               nota4 == notas.nota4;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nota1, nota2, nota3, nota4);
    }
}

