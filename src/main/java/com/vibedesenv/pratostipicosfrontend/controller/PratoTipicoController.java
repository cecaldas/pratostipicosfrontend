package com.vibedesenv.pratostipicosfrontend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vibedesenv.pratostipicosfrontend.entity.PratoTipico;
import com.vibedesenv.pratostipicosfrontend.repository.PratoTipicoRepository;

@Controller
@RequestMapping("/pratos")
public class PratoTipicoController {

	private final PratoTipicoRepository pratoTipicoRepository;

	@Autowired
	public PratoTipicoController(PratoTipicoRepository pratoTipicoRepository) {
		this.pratoTipicoRepository = pratoTipicoRepository;
	}

	@GetMapping("")
	public String showUpdateForm(Model model) {
		List<PratoTipico> pratosTipicos = new ArrayList<PratoTipico>();
		
		pratosTipicos = pratoTipicoRepository.findAll();
		
		model.addAttribute("versao", pratoTipicoRepository.getVersion());
		model.addAttribute("pratosTipicos", pratosTipicos);
		return "index";
	}

}
