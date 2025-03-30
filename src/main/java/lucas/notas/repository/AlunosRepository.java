package lucas.notas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import lucas.notas.models.Alunos;

public interface AlunosRepository extends JpaRepository<Alunos, Long>{
	//List<Alunos> findByMediaGreaterThan(double media);
	//List<Alunos> findBySituacaoEquals(String situacao);
}

