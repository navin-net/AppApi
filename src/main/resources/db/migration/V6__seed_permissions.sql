INSERT INTO sma_permissions (code, description) VALUES
    ('PRODUCT_READ','View products'),('PRODUCT_CREATE','Create products'),
    ('PRODUCT_UPDATE','Update products'),('PRODUCT_DELETE','Delete products'),
    ('CATEGORY_READ','View categories'),('CATEGORY_CREATE','Create categories'),
    ('CATEGORY_UPDATE','Update categories'),('CATEGORY_DELETE','Delete categories'),
    ('ORDER_READ','View orders'),('ORDER_CREATE','Create orders'),
    ('ORDER_UPDATE','Update orders'),('ORDER_CANCEL','Cancel orders'),
    ('PAYMENT_CREATE','Take payments'),('REPORT_READ','View reports'),
    ('USER_READ','View users'),('USER_CREATE','Create users'),
    ('USER_UPDATE','Update users'),('ROLE_MANAGE','Manage roles')
ON CONFLICT (code) DO NOTHING;

INSERT INTO sma_role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM sma_roles r JOIN sma_permissions p ON TRUE
WHERE r.name = 'OWNER' ON CONFLICT DO NOTHING;

INSERT INTO sma_role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM sma_roles r JOIN sma_permissions p ON p.code <> 'ROLE_MANAGE'
WHERE r.name = 'ADMIN' ON CONFLICT DO NOTHING;

INSERT INTO sma_role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM sma_roles r JOIN sma_permissions p
  ON p.code IN ('PRODUCT_READ','CATEGORY_READ','ORDER_READ','ORDER_CREATE','ORDER_UPDATE','PAYMENT_CREATE','REPORT_READ')
WHERE r.name = 'CASHIER' ON CONFLICT DO NOTHING;

INSERT INTO sma_role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM sma_roles r JOIN sma_permissions p
  ON p.code IN ('PRODUCT_READ','CATEGORY_READ','ORDER_READ','ORDER_CREATE','ORDER_UPDATE')
WHERE r.name = 'WAITER' ON CONFLICT DO NOTHING;

INSERT INTO sma_role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM sma_roles r JOIN sma_permissions p
  ON p.code IN ('PRODUCT_READ','ORDER_READ','ORDER_UPDATE')
WHERE r.name = 'KITCHEN' ON CONFLICT DO NOTHING;


ALTER TABLE sma_users
    ADD COLUMN token_version         INT NOT NULL DEFAULT 0,
    ADD COLUMN failed_login_attempts INT NOT NULL DEFAULT 0,
    ADD COLUMN locked_until          TIMESTAMP;