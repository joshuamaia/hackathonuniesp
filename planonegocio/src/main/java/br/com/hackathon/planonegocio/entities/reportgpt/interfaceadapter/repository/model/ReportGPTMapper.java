package br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.model;

import br.com.hackathon.planonegocio.entities.reportgpt.domain.ReportGPT;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportGPTMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ReportGPTModel modelToEntity(ReportGPT model) {
		return modelMapper.map(model, ReportGPTModel.class);
	}

}
