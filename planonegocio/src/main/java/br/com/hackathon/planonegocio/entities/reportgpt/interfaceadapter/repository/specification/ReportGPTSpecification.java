package br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.specification;

import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.model.ReportGPTModel;
import br.com.hackathon.planonegocio.shared.interfaceadapter.repository.specification.SpecificationBase;

public class ReportGPTSpecification extends SpecificationBase<ReportGPTModel> {

	private static final long serialVersionUID = 1L;

	public Specification<ReportGPTModel> findByDescription(@Nullable String description) {
		return Optional.ofNullable(description).map(n -> prepareLikeSpecification("description", n)).orElse(null);
	}
	
	public Specification<ReportGPTModel> findByTopicDescription(@Nullable String nome) {
		return Optional.ofNullable(nome).map(n -> prepareLikeSubSpecification("topic", "description", n)).orElse(null);
	}

	public Specification<ReportGPTModel> findByTemplateTitle(@Nullable String email) {
		return Optional.ofNullable(email).map(n -> prepareLikeSubSpecification("template", "title", n)).orElse(null);
	}

}
