package br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.specification;

import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.model.TopicModel;
import br.com.hackathon.planonegocio.shared.interfaceadapter.repository.specification.SpecificationBase;

public class TopicSpecification extends SpecificationBase<TopicModel> {

	private static final long serialVersionUID = 1L;

	public Specification<TopicModel> findByDescription(@Nullable String description) {
		return Optional.ofNullable(description).map(n -> prepareLikeSpecification("description", n)).orElse(null);
	}

}
