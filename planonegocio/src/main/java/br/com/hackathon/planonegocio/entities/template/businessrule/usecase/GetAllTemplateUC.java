package br.com.hackathon.planonegocio.entities.template.businessrule.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.template.businessrule.GetAllTemplate;
import br.com.hackathon.planonegocio.entities.template.domain.Template;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.TemplateRepository;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model.TemplateModelMapper;

@Service
public class GetAllTemplateUC implements GetAllTemplate {

	@Autowired
	TemplateRepository templateRepository;

	@Autowired
	TemplateModelMapper templateModelMapper;

	@Override
	public List<Template> getAllTemplate() {
		return templateRepository.findAll().stream().map(templateModelMapper::modelToEntity).collect(Collectors.toList());
	}

}
