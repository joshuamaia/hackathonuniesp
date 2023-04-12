package br.com.hackathon.planonegocio.entities.template.businessrule.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.template.businessrule.SearchAllPageTemplate;
import br.com.hackathon.planonegocio.entities.template.domain.Template;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.TemplateRepository;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model.TemplateModelMapper;

@Service
public class SearchAllPageTemplateUC implements SearchAllPageTemplate {

	@Autowired
	TemplateRepository templateRepository;

	@Autowired
	TemplateModelMapper templateModelMapper;

	@Override
	public Page<Template> searchAllPage(Integer page, Integer size, String wordSearch) {
		PageRequest pageRequest = PageRequest.of(page, size);
		if (wordSearch == null || wordSearch.trim().isEmpty()) {
			return templateRepository.findAll(pageRequest).map(templateModelMapper::modelToEntity);
		}
		wordSearch = wordSearch.toLowerCase();
		return templateRepository.searchAllPage(wordSearch, pageRequest).map(templateModelMapper::modelToEntity);

	}

}
