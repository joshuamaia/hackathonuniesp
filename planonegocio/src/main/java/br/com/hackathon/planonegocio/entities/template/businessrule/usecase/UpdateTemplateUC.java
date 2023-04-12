package br.com.hackathon.planonegocio.entities.template.businessrule.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.ReportGPTRepository;
import br.com.hackathon.planonegocio.entities.template.businessrule.UpdateTemplate;
import br.com.hackathon.planonegocio.entities.template.domain.Template;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.TemplateRepository;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model.TemplateMapper;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model.TemplateModel;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model.TemplateModelMapper;

@Service
public class UpdateTemplateUC implements UpdateTemplate {
	
	@Autowired
	TemplateRepository templateRepository;
	
	@Autowired
	TemplateMapper templateMapper;
	
	@Autowired
	TemplateModelMapper templateModelMapper;
	
	@Autowired
    ReportGPTRepository reportGPTRepository;
	
	@Override
	@Transactional
	public Template updateTemplate(Template template) {
		
		Long countTemplateByReport = reportGPTRepository.countReportByTopic(template.getId());

		if (countTemplateByReport > 0) {
			throw new RuntimeException("Este template não pode ser atualizado. Já existe um Relatório GPT associado a ele!");
		}
		
		if (!template.getDescription().contains(":topic")) {
			throw new RuntimeException("Please include the :topic flag in the description!");
		}
		
		TemplateModel toReturn = templateRepository.save(templateMapper.modelToEntity(template));

		return templateModelMapper.modelToEntity(toReturn);
	}

}
