CREATE TABLE sma_users (
    id            SERIAL PRIMARY KEY,
    restaurant_id BIGINT NOT NULL REFERENCES sma_restaurants(id),
    role_id       BIGINT NOT NULL REFERENCES sma_roles(id),
    username      VARCHAR(80)  NOT NULL,
    email         VARCHAR(150),
    password_hash VARCHAR(255) NOT NULL,   -- BCrypt hash, never plain text
    full_name     VARCHAR(150) NOT NULL,
    phone         VARCHAR(30),
    is_active     BOOLEAN NOT NULL DEFAULT TRUE,
    last_login_at TIMESTAMP,
    created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_users_restaurant_username UNIQUE (restaurant_id, username),
    CONSTRAINT uq_users_restaurant_email    UNIQUE (restaurant_id, email)
);

CREATE INDEX idx_users_restaurant ON sma_users(restaurant_id);
CREATE INDEX idx_users_role       ON sma_users(role_id);
