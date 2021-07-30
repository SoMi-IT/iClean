package mindthehead.iclean.example.datamanage;

/*

new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
    @Override
    public void run() {
        // Your Code
    }
}, 3000);



import android.content.Context;

import com.example.skilltest.R;
import com.example.skilltest.sharedmanager.SharedPreferencesManager;
import com.example.skilltest.surveysmanager.customobjects.Answer;
import com.example.skilltest.surveysmanager.customobjects.Question;
import com.example.skilltest.surveysmanager.customobjects.Survey;

import java.util.ArrayList;

public class SurveysDataManager {


    private ArrayList<Survey> surveys;



    public static ArrayList<Survey> obtainStoredSurveys(Context context) {

        ArrayList<Survey> storedSurveys = new ArrayList<>();

        String storedSurveysString = SharedPreferencesManager.readString(context, R.string.json_surveys_key);

        storedSurveys = JsonDataManager.getSurveysFromString(storedSurveysString);


        return storedSurveys;

    }//obtainStoredSurveys


    public static Survey obtainSurveyFromId(Context context, String surveyId) {

        ArrayList<Survey> storedSurveys = obtainStoredSurveys(context);


        for(int i=0; i<storedSurveys.size(); i++) {

            if(surveyId.equals(storedSurveys.get(i).getId())) {

                return storedSurveys.get(i);

            }

        }

        return null;

    }//obtainSurveyFromId


    public static void updateSurvey(Context context, Survey survey, String newSurveyId) {

        ArrayList<Survey> storedSurveys = obtainStoredSurveys(context);

        for(int i = 0; i<storedSurveys.size(); i++) {

            if(survey.getId().equals(storedSurveys.get(i).getId())) {

                storedSurveys.get(i).setId(newSurveyId);

            }

        }

        String newStoredSurveysString = JsonDataManager.getStringFromSurveys(storedSurveys);

        SharedPreferencesManager.writeString(context, R.string.json_surveys_key, newStoredSurveysString);

    }//updateSurvey

    public static boolean isSurveyAlreadyExist(Context context, String surveyId) {


        ArrayList<Survey> storedSurveys = obtainStoredSurveys(context);

        for(int i=0; i<storedSurveys.size(); i++) {

            if(surveyId.equals(storedSurveys.get(i).getId())) {

                return true;

            }

        }

        return false;

    }//isSurveyAlreadyExist


    public static void addNewSurvey(Context context, String surveyId) {


        ArrayList<Survey> storedSurveys = obtainStoredSurveys(context);

        Survey newSurvey = new Survey();
        newSurvey.setId(surveyId);
        newSurvey.setQuestions(new ArrayList<Question>());

        storedSurveys.add(newSurvey);

        String newStoredSurveysString = JsonDataManager.getStringFromSurveys(storedSurveys);

        SharedPreferencesManager.writeString(context, R.string.json_surveys_key, newStoredSurveysString);

    }//addNewSurvey


    public static void deleteSurvey(Context context, String surveyId) {


        ArrayList<Survey> storedSurveys = obtainStoredSurveys(context);

        ArrayList<Survey> newSurveys = new ArrayList<Survey>();

        for(int i=0; i<storedSurveys.size(); i++) {

            if(surveyId.equals(storedSurveys.get(i).getId())) {

                storedSurveys.remove(storedSurveys.get(i));

            }

        }


        String newStoredSurveysString = JsonDataManager.getStringFromSurveys(storedSurveys);

        SharedPreferencesManager.writeString(context, R.string.json_surveys_key, newStoredSurveysString);

    }//addNewSurvey



    public static ArrayList<Question> obtainQuestionsFromSurveyId(Context context, String surveyId) {

        ArrayList<Survey> storedSurveys = obtainStoredSurveys(context);
        ArrayList<Question> questions = new ArrayList<>();

        for(int i=0; i<storedSurveys.size(); i++) {

            if(surveyId.equals(storedSurveys.get(i).getId())) {

                questions = storedSurveys.get(i).getQuestions();

            }

        }

        return questions;

    }//obtainQuestionsFromSurveyId


    public static int howQuestionAlreadyExist(Context context, Survey survey, String question) {

        int occurrences = 0;

        for(int i=0; i<survey.getQuestions().size(); i++) {

            if(question.equals(survey.getQuestions().get(i).getQuestion())) {

                occurrences++;
            }

        }

        return occurrences;

    }//isQuestionAlreadyExist


    public static void addNewQuestion(Context context, Survey survey, String question) {


        ArrayList<Survey> storedSurveys = obtainStoredSurveys(context);
        ArrayList<Survey> newSurveys = new ArrayList<Survey>();

        Answer defaultAnswer = new Answer("", false);

        Question newQuestion = new Question();
        newQuestion.setQuestion(question);
        newQuestion.setCorrect(false);

        newQuestion.setAnswer_1(defaultAnswer);
        newQuestion.setAnswer_2(defaultAnswer);
        newQuestion.setAnswer_3(defaultAnswer);
        newQuestion.setAnswer_4(defaultAnswer);

        survey.getQuestions().add(newQuestion);

        for(int i = 0; i<storedSurveys.size(); i++) {

            if(survey.getId().equals(storedSurveys.get(i).getId())) {

                newSurveys.add(survey);

            } else {

                newSurveys.add(storedSurveys.get(i));

            }
        }

        String newStoredSurveysString = JsonDataManager.getStringFromSurveys(newSurveys);

        SharedPreferencesManager.writeString(context, R.string.json_surveys_key, newStoredSurveysString);


    }//addNewQuestion


    public static void deleteQuestion(Context context, Survey survey, String question) {

        ArrayList<Survey> storedSurveys = obtainStoredSurveys(context);

        ArrayList<Survey> newSurveys = new ArrayList<Survey>();


        for(int i=0; i<survey.getQuestions().size(); i++) {

            if(question.equals(survey.getQuestions().get(i).getQuestion())) {

                survey.getQuestions().remove(survey.getQuestions().get(i));

            }

        }

        for(int i = 0; i<storedSurveys.size(); i++) {

            if(survey.getId().equals(storedSurveys.get(i).getId())) {

                newSurveys.add(survey);

            } else {

                newSurveys.add(storedSurveys.get(i));

            }
        }

        String newStoredSurveysString = JsonDataManager.getStringFromSurveys(newSurveys);

        SharedPreferencesManager.writeString(context, R.string.json_surveys_key, newStoredSurveysString);

    }//deleteQuestion


    public static void updateQuestion(Context context, String surveyId, Question oldQuestion, Question newQuestion) {

        ArrayList<Survey> storedSurveys = obtainStoredSurveys(context);


        for(int i = 0; i<storedSurveys.size(); i++) {


            if(surveyId.equals(storedSurveys.get(i).getId())) {

                for(int j = 0; j<storedSurveys.get(i).getQuestions().size(); j++) {


                    if(oldQuestion.getQuestion().equals(storedSurveys.get(i).getQuestions().get(j).getQuestion())) {

                        storedSurveys.get(i).getQuestions().get(j).setQuestion(newQuestion.getQuestion());
                        storedSurveys.get(i).getQuestions().get(j).setCorrect(newQuestion.isCorrect());
                        storedSurveys.get(i).getQuestions().get(j).setAnswer_1(newQuestion.getAnswer_1());
                        storedSurveys.get(i).getQuestions().get(j).setAnswer_2(newQuestion.getAnswer_2());
                        storedSurveys.get(i).getQuestions().get(j).setAnswer_3(newQuestion.getAnswer_3());
                        storedSurveys.get(i).getQuestions().get(j).setAnswer_4(newQuestion.getAnswer_4());

                    }

                }

            }

        }

        String newStoredSurveysString = JsonDataManager.getStringFromSurveys(storedSurveys);

        SharedPreferencesManager.writeString(context, R.string.json_surveys_key, newStoredSurveysString);


    }//updateQuestion


}//SurveysDataManager
*/