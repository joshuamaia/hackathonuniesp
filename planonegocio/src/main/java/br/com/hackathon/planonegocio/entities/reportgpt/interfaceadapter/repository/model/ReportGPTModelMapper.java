package br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.model;

import br.com.hackathon.planonegocio.entities.reportgpt.domain.ReportGPT;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportGPTModelMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ReportGPT modelToEntity(ReportGPTModel model) {
		return modelMapper.map(model, ReportGPT.class);
	}

}
