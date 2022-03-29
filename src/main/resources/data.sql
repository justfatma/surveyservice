
insert into survey (id, name, description) values (1000, 'Flight Habits', 'It is organized to analyze the flight habits of people and to make campaigns on the relevant dates.');
insert into survey (id, name, description) values (1001, 'Shopping Habits', 'It is organized to analyze the shopping habits of people and to make campaigns on the relevant dates.');

insert into question (id, description, survey_Id, answera, answerb, answerc, answerd, answere) values 
(10000, 'How often do you fly?', 1000, 'Never', 'Up to 3 times a year', 'Almost each month', 'On holidays', 'Almost every week');
insert into question (id, description, survey_Id, answera, answerb, answerc, answerd, answere) values 
(10001, 'Which airport do you prefer?', 1000, 'Airport 1', 'Airport 2', 'Airport 3', 'Airport 4', 'Airport 5');
insert into question (id, description, survey_Id, answera, answerb, answerc, answerd, answere) values 
(10002, 'Do the campaigns affect your flight choice?', 1000, 'Never', 'Rarely', 'Yes on holidays', 'Usually', 'Always');
insert into question (id, description, survey_Id, answera, answerb, answerc, answerd, answere) values 
(10003, 'What do you pay attention to most when buying a plane ticket?', 1000, 'Time', 'Price', 'Service', 'Airport location', 'Airlane Company');

insert into question (id, description, survey_Id, answera, answerb, answerc, answerd, answere) values 
(10004, 'How often do you shop?', 1001, 'Rarely', 'Each month', 'A few times each month', 'Especially on holidays', 'Every week');
insert into question (id, description, survey_Id, answera, answerb, answerc, answerd, answere) values 
(10005, 'Which brand do you prefer?', 1001, 'Brand 1', 'Brand 2', 'Brand 3', 'Brand 4', 'Brand 5');
insert into question (id, description, survey_Id, answera, answerb, answerc, answerd, answere) values 
(10006, 'Do the campaigns affect your shopping choice?', 1001, 'Never', 'Rarely', 'Yes on holidays', 'Usually', 'Always');
insert into question (id, description, survey_Id, answera, answerb, answerc, answerd, answere) values 
(10007, 'What do you pay attention to most when shopping?', 1001, 'Time', 'Price', 'Quality', 'Location', 'Brand');


insert into participant (id, name, surname, age) values (1000, 'John','Taff', 30 );
insert into participant (id, name, surname, age) values (1001, 'Jack','Pitt', 45 );

insert into participant_answer(id, participant_id, question_id, answer) values (5000, 1000, 10000, 'D');
insert into participant_answer(id, participant_id, question_id, answer) values (5001, 1000, 10001, 'B');
insert into participant_answer(id, participant_id, question_id, answer) values (5002, 1000, 10002, 'C');
insert into participant_answer(id, participant_id, question_id, answer) values (5003, 1000, 10003, 'E');

insert into participant_answer(id, participant_id, question_id, answer) values (5004, 1001, 10000, 'B');
insert into participant_answer(id, participant_id, question_id, answer) values (5005, 1001, 10001, 'C');
insert into participant_answer(id, participant_id, question_id, answer) values (5006, 1001, 10002, 'C');
insert into participant_answer(id, participant_id, question_id, answer) values (5007, 1001, 10003, 'A');




