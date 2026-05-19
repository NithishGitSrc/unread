CREATE TABLE Users (
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    name          VARCHAR(100) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at    TIMESTAMP    NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE Links (
    id         BIGINT    NOT NULL AUTO_INCREMENT,
    url        TEXT      NOT NULL,
    url_hash   CHAR(64)  NOT NULL,   -- SHA-256 of normalised URL
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id),
    UNIQUE (url_hash)
);

CREATE TABLE Cards (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    user_id     BIGINT       NOT NULL,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    is_active   BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP    NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);

CREATE TABLE CardLinks (
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    card_id       BIGINT       NOT NULL,
    link_id       BIGINT       NOT NULL,
    label         VARCHAR(255),
    description   TEXT,
    display_order SMALLINT     NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    UNIQUE (card_id, link_id),
    FOREIGN KEY (card_id) REFERENCES Cards(id) ON DELETE CASCADE,
    FOREIGN KEY (link_id) REFERENCES Links(id) ON DELETE RESTRICT
);

CREATE TABLE CardTextBlocks (
    id            BIGINT    NOT NULL AUTO_INCREMENT,
    card_id       BIGINT    NOT NULL,
    content       TEXT      NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (card_id) REFERENCES Cards(id) ON DELETE CASCADE
);

CREATE TABLE CardSchedule (
    id             BIGINT    NOT NULL AUTO_INCREMENT,
    card_id        BIGINT    NOT NULL,
    scheduled_date DATE      NOT NULL,
    status         ENUM('PENDING','COMPLETED','SKIPPED') NOT NULL DEFAULT 'PENDING',
    completed_at   TIMESTAMP,
    created_at     TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id),
    FOREIGN KEY (card_id) REFERENCES Cards(id) ON DELETE CASCADE
);
