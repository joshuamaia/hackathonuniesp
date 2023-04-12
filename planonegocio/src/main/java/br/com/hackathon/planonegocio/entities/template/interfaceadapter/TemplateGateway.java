package br.com.hackathon.planonegocio.entities.template.interfaceadapter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import br.com.hackathon.planonegocio.entities.template.domain.Template;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.controller.model.GetTemplateResponse;

public interface TemplateGateway {

	GetTemplateResponse saveTemplate(Template template);

	GetTemplateResponse updateTemplate(Template template);

	List<GetTemplateResponse> getAllTemplate();

	Page<GetTemplateResponse> filter(@Nullable String title, @Nullable String description, @Nullable Integer page,
			@Nullable Integer size);

	GetTemplateResponse findOneTemplate(Long id);

	void deleteTemplate(Long id);

	public Page<GetTemplateResponse> searchAllPage(Integer page, Integer size, String wordSearch);

}
