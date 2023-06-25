package services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granjamazon.suino.dto.RacaDTO;
import com.granjamazon.suino.entities.Raca;
import com.granjamazon.suino.repositories.RacaRepository;

@Service
public class RacaService {
	
	@Autowired
	RacaRepository racaRepository;
	public List<RacaDTO> getAll() {
		List<Raca> raca = racaRepository.findAll();
        return raca.stream().map(
               item -> RacaDTO
                		.builder()
                		.nome(item.getNome())
                		.nomeComercial(item.getNomeComercial())
                		.isEnable(item.getIsEnable())
                		.fornecedor(item.getFornecedor().getId())
                		.build()
                ).collect(Collectors.toList());
	}
}