package br.com.hackathon.planonegocio.entities.template.businessrule.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hackathon.planonegocio.entities.template.businessrule.SaveTemplate;
import br.com.hackathon.planonegocio.entities.template.domain.Template;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.TemplateRepository;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model.TemplateMapper;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model.TemplateModel;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model.TemplateModelMapper;

@Service
public class SaveTemplateUC implements SaveTemplate {
	
	@Autowired
	TemplateRepository templateRepository;
	
	@Autowired
	TemplateMapper templateMapper;
	
	@Autowired
	TemplateModelMapper templateModelMapper;

	@Override
	@Transactional
	public Template saveTemplate(Template template) {
		
		if (!template.getDescription().contains(":topic")) {
			throw new RuntimeException("Obrigado conter a flag :topic na descrição!");
		}
		
		TemplateModel toReturn = templateRepository.save(templateMapper.modelToEntity(template));

		return templateModelMapper.modelToEntity(toReturn);
	}

}
