package com.vibedesenv.pratostipicosfrontend.repository;

import java.util.List;

import com.vibedesenv.pratostipicosfrontend.entity.PratoTipico;

public interface PratoTipicoRepository{
    
    List<PratoTipico> findAll();
    
}
