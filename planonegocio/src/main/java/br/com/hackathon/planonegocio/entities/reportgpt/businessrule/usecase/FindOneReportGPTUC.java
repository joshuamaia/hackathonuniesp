package br.com.hackathon.planonegocio.entities.reportgpt.businessrule.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.FindOneReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.domain.ReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.ReportGPTRepository;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.model.ReportGPTModel;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.model.ReportGPTModelMapper;

@Service
public class FindOneReportGPTUC implements FindOneReportGPT {

	@Autowired
	ReportGPTRepository reportGPTRepository;

	@Autowired
	ReportGPTModelMapper reportGPTModelMapper;

	@Override
	public ReportGPT findOneReportGPT(Long id) {
		Optional<ReportGPTModel> e = reportGPTRepository.findById(id);
		if (!e.isPresent()) {
			throw new RuntimeException("Entity not present!");
		}
		return reportGPTModelMapper.modelToEntity(e.get());
	}

}
