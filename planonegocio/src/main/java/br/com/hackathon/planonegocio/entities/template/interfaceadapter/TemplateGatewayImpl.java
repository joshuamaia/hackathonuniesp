package br.com.hackathon.planonegocio.entities.template.interfaceadapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.template.businessrule.DeleteTemplate;
import br.com.hackathon.planonegocio.entities.template.businessrule.FilterTemplate;
import br.com.hackathon.planonegocio.entities.template.businessrule.FindOneTemplate;
import br.com.hackathon.planonegocio.entities.template.businessrule.GetAllTemplate;
import br.com.hackathon.planonegocio.entities.template.businessrule.SaveTemplate;
import br.com.hackathon.planonegocio.entities.template.businessrule.SearchAllPageTemplate;
import br.com.hackathon.planonegocio.entities.template.businessrule.UpdateTemplate;
import br.com.hackathon.planonegocio.entities.template.domain.Template;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.controller.model.GetTemplateResponse;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.controller.model.GetTemplateResponseMapper;

@Service
public class TemplateGatewayImpl implements TemplateGateway {

	@Autowired
	private SaveTemplate saveTemplate;

	@Autowired
	private UpdateTemplate updateTemplate;

	@Autowired
	private GetAllTemplate getAllTemplate;

	@Autowired
	private FindOneTemplate findOneTemplate;

	@Autowired
	private DeleteTemplate deleteTemplate;

	@Autowired
	private SearchAllPageTemplate searchAllPageTemplate;

	@Autowired
	private FilterTemplate filterTemplate;

	@Autowired
	GetTemplateResponseMapper getTemplateResponseMapper;

	@Override
	public GetTemplateResponse saveTemplate(Template template) {
		return getTemplateResponseMapper.modelToEntity(saveTemplate.saveTemplate(template));
	}

	@Override
	public GetTemplateResponse updateTemplate(Template template) {
		return getTemplateResponseMapper.modelToEntity(updateTemplate.updateTemplate(template));
	}

	@Override
	public List<GetTemplateResponse> getAllTemplate() {
		return getAllTemplate.getAllTemplate().stream().map(getTemplateResponseMapper::modelToEntity)
				.collect(Collectors.toList());
	}

	@Override
	public GetTemplateResponse findOneTemplate(Long id) {
		return getTemplateResponseMapper.modelToEntity(findOneTemplate.findOneTemplate(id));
	}

	@Override
	public void deleteTemplate(Long id) {
		deleteTemplate.deleteTemplate(id);
	}

	@Override
	public Page<GetTemplateResponse> searchAllPage(Integer page, Integer size, String wordSearch) {
		return searchAllPageTemplate.searchAllPage(page, size, wordSearch)
				.map(getTemplateResponseMapper::modelToEntity);
	}

	@Override
	public Page<GetTemplateResponse> filter(String title, String description, Integer page, Integer size) {
		return filterTemplate.filter(title, description, page, size).map(getTemplateResponseMapper::modelToEntity);
	}

}
