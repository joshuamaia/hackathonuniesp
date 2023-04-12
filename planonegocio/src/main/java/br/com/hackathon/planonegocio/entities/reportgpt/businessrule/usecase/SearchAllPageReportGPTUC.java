package br.com.hackathon.planonegocio.entities.reportgpt.businessrule.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.SearchAllPageReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.domain.ReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.ReportGPTRepository;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.model.ReportGPTModelMapper;

@Service
public class SearchAllPageReportGPTUC implements SearchAllPageReportGPT {

	@Autowired
	ReportGPTRepository reportGPTRepository;

	@Autowired
	ReportGPTModelMapper reportGPTModelMapper;

	@Override
	public Page<ReportGPT> searchAllPage(Integer page, Integer size, String wordSearch) {
		PageRequest pageRequest = PageRequest.of(page, size);
		if (wordSearch == null || wordSearch.trim().isEmpty()) {
			return reportGPTRepository.findAll(pageRequest).map(reportGPTModelMapper::modelToEntity);
		}
		wordSearch = wordSearch.toLowerCase();
		return reportGPTRepository.searchAllPage(wordSearch, pageRequest).map(reportGPTModelMapper::modelToEntity);

	}

}
