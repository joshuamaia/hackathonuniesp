package br.com.hackathon.planonegocio.entities.topic.businessrule.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import br.com.hackathon.planonegocio.entities.topic.businessrule.FilterTopic;
import br.com.hackathon.planonegocio.entities.topic.domain.Topic;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.TopicRepository;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.model.TopicModel;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.model.TopicModelMapper;
import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.specification.TopicSpecification;
import jakarta.validation.constraints.NotNull;

@Service
public class FilterTopicUC implements FilterTopic {

	@Autowired
	TopicRepository topicRepository;
	
	@Autowired
	TopicModelMapper TopicModelMapper;

	@Override
	public Page<Topic> filter(@Nullable String description, @Nullable Integer page,
			@Nullable Integer size) {
		var specification = this.prepareSpecification(description);
		return this.topicRepository.findAll(specification, this.preparePageable(PageRequest.of(page, size)))
				.map(TopicModelMapper::modelToEntity);
	}

	@NotNull
	private Specification<TopicModel> prepareSpecification(@Nullable String description) {
		final var specification = new TopicSpecification();

		return specification.and(specification.findByDescription(description));
	}

	private Pageable preparePageable(@Nullable Pageable pageable) {
		return Optional.ofNullable(pageable).orElse(Pageable.unpaged());
	}

}
