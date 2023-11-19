package com.epam.ld.module2.service.template;


import com.epam.ld.module2.model.Client;
import com.epam.ld.module2.model.Template;
import com.epam.ld.module2.service.template.exception.NonLatinTemplateException;
import com.epam.ld.module2.service.template.exception.TagsRemainTemplateException;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TemplateEngineTest {

    public static final String SAMPLE_MAIL = "aa@mail.ru";

    @Nested
    class ExecutionTests{
        private static Stream<Arguments> provideInputForTemplateEngine() {
            return Stream.of(
                    Arguments.of(
                            Map.of("subject","Business Letter"),
                            "Subject: #{subject} then letter goes next....",
                            "Subject: Business Letter then letter goes next...."),
                    Arguments.of(
                            Map.of(
                                    "subject", "Application",
                                    "name", "Victor"),
                            "Subject is #{subject}, Name is #{name}.",
                            "Subject is Application, Name is Victor."),
                    Arguments.of(
                            Map.of(
                                    "name", "Dana Coots",
                                    "job_title","Marketing Manager",
                                    "company","QuipJobs",
                                    "date","March 9, 2023"),
                            """
                            #{name}
                            #{job_title}
                            #{company}
                            45 Cimarron Way
                            San Diego, CA 92029
                                                     
                            #{date}
                                                     
                            Pat Gray
                            HR Recruiter
                            Trusted Media
                            9 Roundtable Circle
                            Los Angeles, CA 90016
                                                     
                            Greetings Mrs. Gray:
                                                     
                            I'm pleased to recommend Jason Breeze for the Social Media Coordinator position at Trusted Media. I was Jason's supervisor at QuipJobs for five years, where he used his digital marketing and communication skills to deliver strong results in his role as Social Media Support Specialist. His creativity, writing and organizational skills would make him an asset at Trusted Media.
                                                     
                            As a Social Media Support Specialist, Jason demonstrated his ability to connect with an audience and analyze data. Over two years, he grew our social media presence on all platforms by 233%. During our new product launch, he used his exceptional creative skills to design an effective social media campaign.
                                                     
                            I highly recommend Jason for this role and believe he can be a strong addition to your team. Please contact me if you have any questions about his past work.
                                                     
                            Sincerely,
                                                     
                            #{name}
                            """,
                            """
                            Dana Coots
                            Marketing Manager
                            QuipJobs
                            45 Cimarron Way
                            San Diego, CA 92029
                                                     
                            March 9, 2023
                                                     
                            Pat Gray
                            HR Recruiter
                            Trusted Media
                            9 Roundtable Circle
                            Los Angeles, CA 90016
                                                     
                            Greetings Mrs. Gray:
                                                     
                            I'm pleased to recommend Jason Breeze for the Social Media Coordinator position at Trusted Media. I was Jason's supervisor at QuipJobs for five years, where he used his digital marketing and communication skills to deliver strong results in his role as Social Media Support Specialist. His creativity, writing and organizational skills would make him an asset at Trusted Media.
                                                     
                            As a Social Media Support Specialist, Jason demonstrated his ability to connect with an audience and analyze data. Over two years, he grew our social media presence on all platforms by 233%. During our new product launch, he used his exceptional creative skills to design an effective social media campaign.
                                                     
                            I highly recommend Jason for this role and believe he can be a strong addition to your team. Please contact me if you have any questions about his past work.
                                                     
                            Sincerely,
                                                     
                            Dana Coots
                            """)
            );
        }

        @ParameterizedTest
        @MethodSource("provideInputForTemplateEngine")
        void templateShouldBeProcessed(Map<String,String> tags, String text, String expected){
            //Having
            Template template = new Template(text);
            Client client = new Client(SAMPLE_MAIL, tags);
            TemplateEngine templateEngine = new TemplateEngine();
            //When
            String actual = templateEngine.generateMessage(template, client);
            //Then
            Assertions.assertEquals(expected, actual);
        }
    }

    @Nested
    class ValidationTests{
        @ParameterizedTest
        @ValueSource(strings = {"气候宜人", "18381","привет"})
        void exceptionShouldBeRaisedForNonLatin1Data(String text){
            //Having
            Template template = new Template(text);
            Client client = new Client(SAMPLE_MAIL, new HashMap<>());
            TemplateEngine templateEngine = new TemplateEngine();
            //Then
            assertThrows(NonLatinTemplateException.class, () -> templateEngine.generateMessage(template, client),"Templ Latin-1");
        }

        @ParameterizedTest
        @ValueSource(strings = {"aalle, ekek=ek. Ek ek.", "H","HI I AM PAVEL(Worker)"})
        void exceptionShouldBeNotRaisedForLatin1Data(String text){
            //Having
            Template template = new Template(text);
            Client client = new Client(SAMPLE_MAIL, new HashMap<>());
            TemplateEngine templateEngine = new TemplateEngine();
            //Then
            assertDoesNotThrow(() -> templateEngine.generateMessage(template, client));
        }

        private static Stream<Arguments> provideLessTagsInputForTemplateEngine() {
            return Stream.of(
                    Arguments.of(new HashMap<>(),
                            "Hi John! The subject of our letter is #{subject} and we are going to meet tonight"),
                    Arguments.of(Map.of(
                                    "name", "Victor",
                                    "surname","Vyrostak"),
                            "Subject: #{subject} then letter goes next...."),
                    Arguments.of(Map.of(
                                    "subject","Application",
                                    "name", "Victor"),
                            "Subject is #{subject}, Name is #{name}, Surname is #{surname} ")
            );

        }

        @ParameterizedTest
        @MethodSource("provideLessTagsInputForTemplateEngine")
        void allTagsShouldBeInserted(Map<String,String> tags, String text){
            //Having
            Template template = new Template(text);
            Client client = new Client(SAMPLE_MAIL, tags);
            TemplateEngine templateEngine = new TemplateEngine();
            //Then
            assertThrows(TagsRemainTemplateException.class, () -> templateEngine.generateMessage(template, client));

        }


        private static Stream<Arguments> provideMoreTagsInputForTemplateEngine() {
            return Stream.of(
                    Arguments.of(Map.of(
                                    "name", "Victor",
                                    "surname", "Vyrostak",
                                    "sometag","sometagvalue"),
                            "My name is #{name} and I'm Java specialist."),
                    Arguments.of(Map.of(
                                    "subject", "Java",
                                    "language", "object-oriented"),
                            "Today we will speak about java. Java language is object-oriented.")
            );
        }


        @ParameterizedTest
        @MethodSource("provideMoreTagsInputForTemplateEngine")
        void extraValuesShouldBeIgnored(Map<String,String> tags, String text){
            //Having
            Template template = new Template(text);
            Client client = new Client(SAMPLE_MAIL, tags);
            TemplateEngine templateEngine = new TemplateEngine();
            //Then
            assertDoesNotThrow(() -> templateEngine.generateMessage(template, client));
        }
    }

}


