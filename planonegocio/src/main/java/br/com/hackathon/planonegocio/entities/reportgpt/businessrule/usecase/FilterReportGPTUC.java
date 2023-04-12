package br.com.hackathon.planonegocio.entities.reportgpt.businessrule.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.reportgpt.businessrule.FilterReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.domain.ReportGPT;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.ReportGPTRepository;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.model.ReportGPTModel;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.model.ReportGPTModelMapper;
import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.specification.ReportGPTSpecification;
import jakarta.validation.constraints.NotNull;

@Service
public class FilterReportGPTUC implements FilterReportGPT {

	@Autowired
	ReportGPTRepository reportGPTRepository;

	@Autowired
	ReportGPTModelMapper reportGPTModelMapper;

	@Override
	public Page<ReportGPT> filter(@Nullable String description, @Nullable String topicDescription,
			@Nullable String templateTitle, @Nullable Integer page, @Nullable Integer size) {
		var specification = this.prepareSpecification(description, topicDescription, templateTitle);
		return this.reportGPTRepository.findAll(specification, this.preparePageable(PageRequest.of(page, size)))
				.map(reportGPTModelMapper::modelToEntity);
	}

	@NotNull
	private Specification<ReportGPTModel> prepareSpecification(@Nullable String description,
			@Nullable String topicDescription, @Nullable String templateTitle) {
		final var specification = new ReportGPTSpecification();

		return specification.and(specification.findByDescription(description))
				.and(specification.findByTopicDescription(topicDescription))
				.and(specification.findByTemplateTitle(templateTitle));
	}

	private Pageable preparePageable(@Nullable Pageable pageable) {
		return Optional.ofNullable(pageable).orElse(Pageable.unpaged());
	}

}
