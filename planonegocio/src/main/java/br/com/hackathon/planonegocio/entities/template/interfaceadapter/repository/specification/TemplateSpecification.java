package br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.specification;

import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model.TemplateModel;
import br.com.hackathon.planonegocio.shared.interfaceadapter.repository.specification.SpecificationBase;

public class TemplateSpecification extends SpecificationBase<TemplateModel> {

	private static final long serialVersionUID = 1L;

	public Specification<TemplateModel> findByDescription(@Nullable String description) {
		return Optional.ofNullable(description).map(n -> prepareLikeSpecification("description", n)).orElse(null);
	}
	
	public Specification<TemplateModel> findByTitle(@Nullable String title) {
		return Optional.ofNullable(title).map(n -> prepareLikeSpecification("title", n)).orElse(null);
	}

}
