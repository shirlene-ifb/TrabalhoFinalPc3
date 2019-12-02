package br.com.prog3.trabalhofinal.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prog3.trabalhofinal.domain.*;

public interface IProdutoRepository extends JpaRepository<Produto, Long>{

}
