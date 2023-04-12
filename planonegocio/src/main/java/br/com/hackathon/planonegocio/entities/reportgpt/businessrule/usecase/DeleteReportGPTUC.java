package br.com.hackathon.planonegocio.entities.reportgpt.businessrule.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.DeleteReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.ReportGPTRepository;

@Service
public class DeleteReportGPTUC implements DeleteReportGPT {
	
	@Autowired
    ReportGPTRepository reportGPTRepository;

	@Override
	public void deleteReportGPT(Long id) {
		reportGPTRepository.deleteById(id);
	}

}
