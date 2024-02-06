CREATE TABLE habitation
(
    id   SERIAL PRIMARY KEY,
    city VARCHAR
);

COMMENT ON TABLE habitation IS 'Проживание';
COMMENT ON COLUMN habitation.id IS 'ID';
COMMENT ON COLUMN habitation.city IS 'Город';

---------------------------------------------------
CREATE TABLE work_schedule
(
    id        SERIAL PRIMARY KEY,
    work_type VARCHAR
);

COMMENT ON TABLE work_schedule IS 'График работы';
COMMENT ON COLUMN work_schedule.id IS 'ID';
COMMENT ON COLUMN work_schedule.work_type IS 'Тип работы';

---------------------------------------------------
CREATE TABLE education_type
(
    id              SERIAL PRIMARY KEY,
    education_level VARCHAR
);

COMMENT ON TABLE education_type IS 'Тип образования';
COMMENT ON COLUMN education_type.id IS 'ID';
COMMENT ON COLUMN education_type.education_level IS 'Уровень образования';

---------------------------------------------------
CREATE TABLE citizenship_type
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR
);

COMMENT ON TABLE citizenship_type IS 'Тип гражданства';
COMMENT ON COLUMN citizenship_type.id IS 'ID';
COMMENT ON COLUMN citizenship_type.name IS 'Названия гражданства';

---------------------------------------------------
CREATE TABLE gender
(
    id   SERIAL PRIMARY KEY,
    type VARCHAR
);

COMMENT ON TABLE gender IS 'Пол';
COMMENT ON COLUMN gender.id IS 'ID';
COMMENT ON COLUMN gender.type IS 'Тип пола';

---------------------------------------------------
CREATE TABLE licence_category
(
    id       SERIAL PRIMARY KEY,
    category VARCHAR
);

COMMENT ON TABLE licence_category IS 'Категория прав';
COMMENT ON COLUMN licence_category.id IS 'ID';
COMMENT ON COLUMN licence_category.category IS 'Категория';

---------------------------------------------------
CREATE TABLE business_trips
(
    id        SERIAL PRIMARY KEY,
    readiness VARCHAR
);

COMMENT ON TABLE business_trips IS 'Командировка';
COMMENT ON COLUMN business_trips.id IS 'ID';
COMMENT ON COLUMN business_trips.readiness IS 'Готовность';

---------------------------------------------------
CREATE TABLE level
(
    id              SERIAL PRIMARY KEY,
    knowledge_level VARCHAR(4)
);

COMMENT ON TABLE level IS 'Уровень';
COMMENT ON COLUMN level.id IS 'ID';
COMMENT ON COLUMN level.knowledge_level IS 'Уровень знания';

---------------------------------------------------
CREATE TABLE language
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR
);

COMMENT ON TABLE language IS 'Язык';
COMMENT ON COLUMN language.id IS 'ID';
COMMENT ON COLUMN language.name IS 'Название';

---------------------------------------------------
CREATE TABLE language_foreign
(
    id                SERIAL PRIMARY KEY,
    language_id       INTEGER REFERENCES language (id),
    language_level_id INTEGER REFERENCES level (id)
);

COMMENT ON TABLE language_foreign IS 'Иностранный язык';
COMMENT ON COLUMN language_foreign.id IS 'ID';
COMMENT ON COLUMN language_foreign.language_id IS 'ID языка';
COMMENT ON COLUMN language_foreign.language_level_id IS 'ID уровень знания';

---------------------------------------------------
CREATE TABLE employees_experience
(
    id                       SERIAL PRIMARY KEY,
    post                     VARCHAR,
    responsibilities         VARCHAR,
    company                  VARCHAR,
    employment_opportunities DATE,
    dismissal                DATE
);

COMMENT ON TABLE employees_experience IS 'Опыт работы';
COMMENT ON COLUMN employees_experience.id IS 'ID';
COMMENT ON COLUMN employees_experience.post IS 'Должносить';
COMMENT ON COLUMN employees_experience.responsibilities IS 'Обязанности';
COMMENT ON COLUMN employees_experience.company IS 'Компания';
COMMENT ON COLUMN employees_experience.employment_opportunities IS 'Трудоустройство';
COMMENT ON COLUMN employees_experience.dismissal IS 'Увольнение';

---------------------------------------------------
CREATE TABLE add_information
(
    id                    SERIAL PRIMARY KEY,
    language_foreign_id   INTEGER REFERENCES language_foreign (id),
    business_trips_id     INTEGER REFERENCES business_trips (id),
    courses_and_trainings VARCHAR,
    skills                VARCHAR,
    about_me              VARCHAR
);

COMMENT ON TABLE add_information IS 'Дополнительная информация';
COMMENT ON COLUMN add_information.id IS 'ID';
COMMENT ON COLUMN add_information.language_foreign_id IS 'Иностранный язык';
COMMENT ON COLUMN add_information.business_trips_id IS 'Командировка';
COMMENT ON COLUMN add_information.courses_and_trainings IS 'Курсы и тренинг';
COMMENT ON COLUMN add_information.skills IS 'Навыки и умения';
COMMENT ON COLUMN add_information.about_me IS 'Обо мне';

---------------------------------------------------
CREATE TABLE license
(
    id                  SERIAL PRIMARY KEY,
    add_information_id  INTEGER REFERENCES add_information (id),
    license_category_id INTEGER REFERENCES licence_category (id)
);

COMMENT ON TABLE license IS 'Права';
COMMENT ON COLUMN license.id IS 'ID';
COMMENT ON COLUMN license.add_information_id IS 'ID дополнительной информации';
COMMENT ON COLUMN license.license_category_id IS 'ID категория права';

---------------------------------------------------
CREATE TABLE personal_data
(
    id                 SERIAL PRIMARY KEY,
    first_name         VARCHAR,
    last_name          VARCHAR,
    patronymic         VARCHAR,
    habitation_id      INTEGER REFERENCES habitation (id),
    gender_id          INTEGER REFERENCES gender (id),
    birth_data         DATE,
    add_information_id INTEGER REFERENCES add_information (id)
);

COMMENT ON TABLE personal_data IS 'Личные данные';
COMMENT ON COLUMN personal_data.id IS 'ID';
COMMENT ON COLUMN personal_data.first_name IS 'Имя';
COMMENT ON COLUMN personal_data.last_name IS 'Фамилия';
COMMENT ON COLUMN personal_data.patronymic IS 'Отчество';
COMMENT ON COLUMN personal_data.habitation_id IS 'Проживание ID';
COMMENT ON COLUMN personal_data.gender_id IS 'Пол человека ID';
COMMENT ON COLUMN personal_data.birth_data IS 'Дата рождения';
COMMENT ON COLUMN personal_data.add_information_id IS 'Дополнительная информация ID';

---------------------------------------------------
CREATE TABLE work
(
    id                      SERIAL PRIMARY KEY,
    person_data_id          INTEGER REFERENCES personal_data (id),
    employees_experience_id INTEGER REFERENCES employees_experience (id)
);

COMMENT ON TABLE work IS 'Работа';
COMMENT ON COLUMN work.id IS 'ID';
COMMENT ON COLUMN work.person_data_id IS 'Персональные данные ID';
COMMENT ON COLUMN work.employees_experience_id IS 'Опыт работы ID';

---------------------------------------------------
CREATE TABLE employment_type
(
    id               SERIAL PRIMARY KEY,
    person_data_id   INTEGER REFERENCES personal_data (id),
    work_schedule_id INTEGER REFERENCES work_schedule (id)
);

COMMENT ON TABLE employment_type IS 'Тип занятости';
COMMENT ON COLUMN employment_type.id IS 'ID';
COMMENT ON COLUMN employment_type.person_data_id IS 'Персональные данные ID';
COMMENT ON COLUMN employment_type.work_schedule_id IS 'График работы ID';

---------------------------------------------------
CREATE TABLE specification
(
    id                      SERIAL PRIMARY KEY,
    education_type_id       INTEGER REFERENCES education_type (id),
    ending                  INTEGER,
    educational_institution VARCHAR,
    direction               VARCHAR
);

COMMENT ON TABLE specification IS 'Спецификация';
COMMENT ON COLUMN specification.id IS 'ID';
COMMENT ON COLUMN specification.education_type_id IS 'Тип образования ID';
COMMENT ON COLUMN specification.ending IS 'Когда окончания учебы';
COMMENT ON COLUMN specification.educational_institution IS 'Учебное заведение';
COMMENT ON COLUMN specification.direction IS 'Направление';

---------------------------------------------------
CREATE TABLE education
(
    id               SERIAL PRIMARY KEY,
    person_data_id   INTEGER REFERENCES personal_data (id),
    specification_id INTEGER REFERENCES specification (id)
);

COMMENT ON TABLE education IS 'Образование';
COMMENT ON COLUMN education.id IS 'ID';
COMMENT ON COLUMN education.person_data_id IS 'Персональные данные ID';
COMMENT ON COLUMN education.specification_id IS 'Спецификация ID';

---------------------------------------------------
CREATE TABLE citizenship
(
    id                  INTEGER PRIMARY KEY,
    personal_data_id    INTEGER REFERENCES personal_data (id),
    citizenship_type_id INTEGER REFERENCES citizenship_type (id)
);

COMMENT ON TABLE citizenship IS 'Гражданство';
COMMENT ON COLUMN citizenship.id IS 'ID';
COMMENT ON COLUMN citizenship.personal_data_id IS 'Персональные данные ID';
COMMENT ON COLUMN citizenship.citizenship_type_id IS 'Тип гражданства ID';