package com.survey.dao;

import java.util.List;

import com.survey.hibernate.model.Questionnaire;
import com.survey.hibernate.model.TargetQuestionnaire;

public interface TargetQuestionnaireDao {

	public void createTargetQuestionnaire(TargetQuestionnaire targetQuestionnaire);
	public List<Questionnaire> getQuestionnairesByTargetId(Integer targetId);
	public List<TargetQuestionnaire> getAllTargetQuestionnaires();
	public TargetQuestionnaire getTargetQuestionnaireById(Integer id);
	public TargetQuestionnaire getTargetQuestionnaireByQuestionnaireAndTarget(Integer questionnaireId, Integer targetId);
}
