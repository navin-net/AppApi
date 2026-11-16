CREATE TABLE sma_role_permissions (
    role_id       BIGINT NOT NULL REFERENCES sma_roles(id) ON DELETE CASCADE,
    permission_id BIGINT NOT NULL REFERENCES sma_permissions(id) ON DELETE CASCADE,
    PRIMARY KEY (role_id, permission_id)
);

CREATE INDEX idx_role_permissions_permission ON sma_role_permissions(permission_id);
