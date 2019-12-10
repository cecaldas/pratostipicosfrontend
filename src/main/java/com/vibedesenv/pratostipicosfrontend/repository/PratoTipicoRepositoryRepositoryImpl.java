package com.vibedesenv.pratostipicosfrontend.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vibedesenv.pratostipicosfrontend.entity.PratoTipico;

@Repository
public class PratoTipicoRepositoryRepositoryImpl implements PratoTipicoRepository {

	@Override
	public List<PratoTipico> findAll() {
		List<PratoTipico> list = new ArrayList<PratoTipico>();
		
		PratoTipico prato1 = new PratoTipico();
		prato1.setId(1);
		prato1.setNome("nome");
		prato1.setDescricao("descricao do prato");
		prato1.setValor(new BigDecimal(20.55));
		list.add(prato1);
		
		PratoTipico prato2 = new PratoTipico();
		prato2.setId(2);
		prato2.setNome("nome");
		prato2.setDescricao("descricao do prato com mais detalhes");
		prato2.setValor(new BigDecimal(.5));
		list.add(prato2);
		
		return list;
	}


}
