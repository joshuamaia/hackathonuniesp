package br.com.hackathon.planonegocio.entities.template.interfaceadapter.controller.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hackathon.planonegocio.entities.template.domain.Template;

@Component
public class GetTemplateResponseMapper {

	@Autowired
	private ModelMapper modelMapper;

	public GetTemplateResponse modelToEntity(Template model) {
		return modelMapper.map(model, GetTemplateResponse.class);
	}

}
