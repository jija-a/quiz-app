create table `categories`
(
    `id`   bigint auto_increment primary key,
    `name` varchar(255) not null,
    constraint `UK_categories_name`
        unique (name)
);

create table `files`
(
    `id`   bigint auto_increment primary key,
    `data` longblob     null,
    `name` varchar(255) null,
    `type` varchar(255) null
);

create table `quizzes`
(
    `id`          bigint auto_increment
        primary key,
    `duration`    int          not null,
    `end_date`    datetime     null,
    `start_date`  datetime     not null,
    `title`       varchar(255) not null,
    `category_id` bigint       not null,
    constraint `UK_quizzes_title`
        unique (title),
    constraint `FK_quizzes_category_id`
        foreign key (`category_id`) references `categories` (id)
);

create table `questions`
(
    `id`       bigint auto_increment primary key,
    `title`    varchar(255) null,
    `type`     varchar(255) null,
    `image_id` bigint       null,
    `quiz_id`  bigint       not null,
    constraint `FK_questions_image_id`
        foreign key (image_id) references `files` (id),
    constraint `FK_questions_quiz_id`
        foreign key (quiz_id) references `quizzes` (id)
);

create table `options`
(
    `id`          bigint auto_increment primary key,
    `is_right`    bit          not null,
    `title`       varchar(255) not null,
    `image_id`    bigint       null,
    `question_id` bigint       not null,
    constraint `FK_options_question_id`
        foreign key (question_id) references `questions` (id),
    constraint `FK_options_image_id`
        foreign key (image_id) references `files` (id)
);

create table `users`
(
    `id`         bigint auto_increment primary key,
    `created_at` datetime     not null,
    `first_name` varchar(255) not null,
    `is_active`  bit          not null,
    `last_name`  varchar(255) not null,
    `updated_at` datetime     null,
    `password`   varchar(255) not null,
    `patronymic` varchar(255) null,
    `username`   varchar(255) not null,
    constraint `UK_users_username`
        unique (username)
);

create table `quiz_results`
(
    `id`          bigint auto_increment primary key,
    `mark`        double   not null,
    `percent`     double   not null,
    `submit_date` datetime null,
    `quiz_id`     bigint   not null,
    `student_id`  bigint   not null,
    constraint `FK_quiz_results_student_id`
        foreign key (student_id) references `users` (id),
    constraint `FK_quiz_results_quiz_id`
        foreign key (quiz_id) references `quizzes` (id)
);
