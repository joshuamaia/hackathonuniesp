package br.com.hackathon.planonegocio.entities.template.businessrule;


import org.springframework.data.domain.Page;

import br.com.hackathon.planonegocio.entities.template.domain.Template;

public interface SearchAllPageTemplate {
	
	public Page<Template> searchAllPage(Integer page, Integer size, String wordSearch);

}
