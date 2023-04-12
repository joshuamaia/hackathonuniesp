package br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.hackathon.planonegocio.entities.topic.interfaceadapter.repository.model.TopicModel;

public interface TopicRepository extends JpaRepository<TopicModel, Long>, JpaSpecificationExecutor<TopicModel> {

	@Query("FROM TopicModel t WHERE LOWER(t.description) like %:wordSearch%")
	Page<TopicModel> searchAllPage(@Param("wordSearch") String wordSearch, Pageable pageable);

}
