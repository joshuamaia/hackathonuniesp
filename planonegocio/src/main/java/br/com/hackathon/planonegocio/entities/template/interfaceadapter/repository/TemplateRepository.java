package br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.hackathon.planonegocio.entities.template.interfaceadapter.repository.model.TemplateModel;

public interface TemplateRepository extends JpaRepository<TemplateModel, Long>, JpaSpecificationExecutor<TemplateModel> {

	@Query("FROM TemplateModel t WHERE LOWER(t.description) like %:wordSearch%")
	Page<TemplateModel> searchAllPage(@Param("wordSearch") String wordSearch, Pageable pageable);

}
