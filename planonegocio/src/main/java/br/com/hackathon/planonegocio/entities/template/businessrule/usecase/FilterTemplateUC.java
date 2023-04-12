package br.com.hackathon.planonegocio.entities.template.businessrule.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.template.businessrule.FilterTemplate;
import br.com.hackathon.planonegocio.entities.template.domain.Template;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.TemplateRepository;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model.TemplateModel;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model.TemplateModelMapper;
import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.specification.TemplateSpecification;
import jakarta.validation.constraints.NotNull;

@Service
public class FilterTemplateUC implements FilterTemplate {

	@Autowired
	TemplateRepository templateRepository;
	
	@Autowired
	TemplateModelMapper templateModelMapper;

	@Override
	public Page<Template> filter(@Nullable String title, @Nullable String description, @Nullable Integer page,
			@Nullable Integer size) {
		var specification = this.prepareSpecification(title, description);
		return this.templateRepository.findAll(specification, this.preparePageable(PageRequest.of(page, size)))
				.map(templateModelMapper::modelToEntity);
	}

	@NotNull
	private Specification<TemplateModel> prepareSpecification(@Nullable String title, @Nullable String description) {
		final var specification = new TemplateSpecification();

		return specification.and(specification.findByDescription(description)).and(specification.findByTitle(title));
	}

	private Pageable preparePageable(@Nullable Pageable pageable) {
		return Optional.ofNullable(pageable).orElse(Pageable.unpaged());
	}

}
