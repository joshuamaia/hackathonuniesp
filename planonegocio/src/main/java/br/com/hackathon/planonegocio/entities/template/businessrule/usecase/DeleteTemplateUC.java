package br.com.hackathon.planonegocio.entities.template.businessrule.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.template.businessrule.DeleteTemplate;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.TemplateRepository;

@Service
public class DeleteTemplateUC implements DeleteTemplate{
	
	@Autowired
	TemplateRepository templateRepository;

	@Override
	public void deleteTemplate(Long id) {
		templateRepository.deleteById(id);
	}

}
