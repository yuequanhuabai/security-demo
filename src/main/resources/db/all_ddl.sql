CREATE TABLE `buy_list` (
                            `id` varchar(64) NOT NULL,
                            `name` varchar(64) DEFAULT NULL,
                            `status` char(8) DEFAULT NULL,
                            `type` varchar(50) DEFAULT NULL,
                            `maker` varchar(32) DEFAULT NULL,
                            `checker` varchar(32) DEFAULT NULL,
                            `create_by` varchar(32) DEFAULT NULL,
                            `create_at` datetime DEFAULT CURRENT_TIMESTAMP,
                            `update_by` varchar(32) DEFAULT NULL,
                            `update_at` datetime DEFAULT CURRENT_TIMESTAMP,
                            `is_deleted` varchar(8) DEFAULT '0',
                            `version` int DEFAULT '0',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `log` (
                       `id` varchar(64) DEFAULT NULL,
                       `db_type` varchar(64) DEFAULT NULL,
                       `param` varchar(64) DEFAULT NULL,
                       `url` varchar(128) DEFAULT NULL,
                       `username` varchar(64) DEFAULT NULL,
                       `password` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `student` (
                           `id` varchar(64) DEFAULT NULL,
                           `name` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_answer` (
                            `id` bigint NOT NULL,
                            `question_id` bigint DEFAULT NULL,
                            `question_code` varchar(64) DEFAULT NULL,
                            `user_id` varchar(64) DEFAULT NULL,
                            `user_name` varchar(64) DEFAULT NULL,
                            `content` json DEFAULT NULL COMMENT 'answer content',
                            `extend_info` json DEFAULT NULL COMMENT 'extend_info',
                            `status` varchar(16) DEFAULT '0' COMMENT 'status: 0=temp store, 1=submit',
                            `approver_id` varchar(16) DEFAULT NULL COMMENT '審批人的userId',
                            `approver_name` varchar(32) DEFAULT NULL COMMENT '審批人的人名',
                            `approve_status` int DEFAULT NULL COMMENT '審批狀態： 0=待審批，1=通過，-1=不通過',
                            `tag` varchar(16) DEFAULT NULL COMMENT '標簽',
                            `version` int DEFAULT NULL COMMENT 'version no',
                            `total_score` int DEFAULT NULL COMMENT 'score value',
                            `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                            `update_by` varchar(16) DEFAULT NULL,
                            `updater_name` varchar(32) DEFAULT NULL,
                            `update_time` datetime DEFAULT NULL,
                            `delete_flag` int DEFAULT '0' COMMENT 'flag of logical delete',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_answer_socre` (
                                  `id` bigint NOT NULL,
                                  `answer_id` bigint DEFAULT NULL,
                                  `answer_user_id` varchar(64) DEFAULT NULL,
                                  `subject_id` bigint DEFAULT NULL,
                                  `user_id` varchar(100) DEFAULT NULL,
                                  `user_name` varchar(100) DEFAULT NULL,
                                  `favor` int DEFAULT '0' COMMENT 'like: 0=not like , 1=like',
                                  `score` int DEFAULT '-1' COMMENT 'score value',
                                  `comment` varchar(258) DEFAULT NULL COMMENT 'comment',
                                  `userName` varchar(100) DEFAULT NULL COMMENT 'username of rated user',
                                  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_questionnaire` (
                                   `id` bigint NOT NULL COMMENT 'Primary key,使用雪花id',
                                   `code` varchar(255) DEFAULT NULL COMMENT 'union code',
                                   `cn_name` varchar(255) DEFAULT NULL COMMENT 'questionnaire cn name',
                                   `hk_name` varchar(255) DEFAULT NULL COMMENT 'questionnaire hk name',
                                   `en_name` varchar(255) DEFAULT NULL COMMENT 'questionnaire hk name',
                                   `cn_conclusion` varchar(255) DEFAULT NULL COMMENT 'questionnaire cn conclusion',
                                   `hk_conclusion` varchar(255) DEFAULT NULL COMMENT 'questionnaire hk conclusion',
                                   `en_conclusion` varchar(255) DEFAULT NULL COMMENT 'questionnaire en conclusion',
                                   `cn_description` varchar(255) DEFAULT NULL COMMENT 'questionnaire cn description',
                                   `hk_description` varchar(255) DEFAULT NULL COMMENT 'questionnaire hk description',
                                   `en_description` varchar(255) DEFAULT NULL COMMENT 'questionnaire en description',
                                   `duration` int DEFAULT NULL COMMENT 'questionnaire duration(minute, for questionnaire type: exam)',
                                   `question_type` int DEFAULT NULL COMMENT 'type of questionnaire(1.event ;2 survey, 3. exam)',
                                   `subject` json DEFAULT NULL COMMENT 'subject',
                                   `start_time` datetime DEFAULT NULL COMMENT 'the start time for answering the questionnaire',
                                   `end_time` datetime DEFAULT NULL COMMENT 'the deadline for completing the questionnaire',
                                   `valid_days` int DEFAULT NULL COMMENT '相對有效期，和start_time 和 end_time   二選一',
                                   `need_approve` int DEFAULT NULL COMMENT '提交後是否需要審批， 0=不需要，1=需要',
                                   `re_edit` int DEFAULT NULL COMMENT '提交後是否允許再次編輯： 0:允許；1:不允許',
                                   `answer_limit` int DEFAULT '1' COMMENT '回答次數限制，默認1此',
                                   `cycle_copy_type` int DEFAULT '0' COMMENT '周期性複製類型： 0=不複製，1=周，2=月，3=季度，4=年',
                                   `cycle_copy_day` int DEFAULT NULL COMMENT '周期複製在周期的第幾天',
                                   `answer_share` int DEFAULT '1' COMMENT '共享回答（多個用戶編輯同一個回答）',
                                   `data_list_show_subject_id` varchar(128) DEFAULT NULL COMMENT '數據列表顯示的題目ID，多個以逗號分隔',
                                   `version` int DEFAULT NULL COMMENT 'version no',
                                   `status` int DEFAULT NULL COMMENT 'status 0=unpublished, 1=publish',
                                   `create_by` varchar(16) DEFAULT NULL COMMENT 'creator (user ID)',
                                   `creator_name` varchar(32) DEFAULT NULL COMMENT 'creator username',
                                   `create_time` datetime DEFAULT NULL COMMENT 'create time',
                                   `update_by` varchar(16) DEFAULT NULL COMMENT 'updator (user id)',
                                   `update_name` varchar(32) DEFAULT NULL COMMENT 'update name',
                                   `update_time` datetime DEFAULT NULL COMMENT 'update time',
                                   `delete_flag` int DEFAULT '0' COMMENT 'flag of logical delete',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_questionnaire_favorite` (
                                            `id` bigint NOT NULL,
                                            `question_id` bigint DEFAULT NULL,
                                            `collect` int DEFAULT '0' COMMENT '1為收藏，0為取消收藏',
                                            `user_id` varchar(16) DEFAULT NULL,
                                            `user_name` varchar(32) DEFAULT NULL,
                                            `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_questionnaire_notice` (
                                          `id` bigint NOT NULL,
                                          `question_id` bigint DEFAULT NULL,
                                          `trigger_point` varchar(16) DEFAULT NULL COMMENT '觸發點：public,answer,approved, not-approved, beginTime,endTime',
                                          `recipient_type` varchar(16) DEFAULT NULL COMMENT '接收者類型： mgr,all-answerer',
                                          `recipient_email` varchar(1024) DEFAULT NULL COMMENT '自定義接收者email，多個英文逗號分隔',
                                          `title` varchar(128) DEFAULT NULL COMMENT 'email 標題',
                                          `content` varchar(128) DEFAULT NULL COMMENT 'email content',
                                          `create_by` varchar(16) DEFAULT NULL,
                                          `create_time` datetime DEFAULT NULL,
                                          `update_by` varchar(16) DEFAULT NULL,
                                          `update_time` datetime DEFAULT NULL,
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_questionnaire_user` (
                                        `id` bigint NOT NULL,
                                        `question_id` bigint DEFAULT NULL COMMENT 'questionId',
                                        `user_type` int DEFAULT NULL COMMENT '用戶類型： 0-管理員，1=限定回答',
                                        `user_id` varchar(16) DEFAULT NULL COMMENT '用戶id',
                                        `user_name` varchar(32) DEFAULT NULL COMMENT '用戶名',
                                        `email` varchar(32) DEFAULT NULL COMMENT 'email',
                                        `create_by` varchar(16) DEFAULT NULL COMMENT 'creator (user Id)',
                                        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

