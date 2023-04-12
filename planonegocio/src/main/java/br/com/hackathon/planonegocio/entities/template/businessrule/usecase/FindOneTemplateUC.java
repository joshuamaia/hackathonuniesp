package br.com.hackathon.planonegocio.entities.template.businessrule.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.template.businessrule.FindOneTemplate;
import br.com.hackathon.planonegocio.entities.template.domain.Template;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.TemplateRepository;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model.TemplateModel;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model.TemplateModelMapper;

@Service
public class FindOneTemplateUC implements FindOneTemplate {

	@Autowired
	TemplateRepository TemplateRepository;

	@Autowired
	TemplateModelMapper templateModelMapper;

	@Override
	public Template findOneTemplate(Long id) {
		Optional<TemplateModel> e = TemplateRepository.findById(id);
		if (!e.isPresent()) {
			throw new RuntimeException("Entity not present!");
		}
		return templateModelMapper.modelToEntity(e.get());
	}

}
