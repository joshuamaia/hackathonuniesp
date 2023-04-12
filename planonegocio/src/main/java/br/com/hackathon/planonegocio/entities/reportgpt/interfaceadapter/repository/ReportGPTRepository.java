package br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository;

import br.com.hackathon.planonegocio.entities.reportgpt.interfaceadapter.repository.model.ReportGPTModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportGPTRepository
		extends JpaRepository<ReportGPTModel, Long>, JpaSpecificationExecutor<ReportGPTModel> {

	@Query("FROM ReportGPTModel r WHERE LOWER(r.description) like %:wordSearch%")
	Page<ReportGPTModel> searchAllPage(@Param("wordSearch") String wordSearch, Pageable pageable);

	@Query("SELECT COUNT(r) FROM ReportGPTModel r WHERE r.topic.id = :topicId AND r.template.id = :templateId")
	Long countReportByTopicAndTemplate(@Param("topicId") Long topicId, @Param("templateId") Long templateId);

	@Query("SELECT COUNT(r) FROM ReportGPTModel r WHERE r.topic.id = :topicId")
	Long countReportByTopic(@Param("topicId") Long topicId);

	@Query("SELECT COUNT(r) FROM ReportGPTModel r WHERE r.template.id = :templateId")
	Long countReportByTemplate(@Param("templateId") Long templateId);
}
