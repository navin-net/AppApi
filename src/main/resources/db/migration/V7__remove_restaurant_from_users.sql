ALTER TABLE sma_users DROP CONSTRAINT IF EXISTS uq_users_restaurant_username;
ALTER TABLE sma_users DROP CONSTRAINT IF EXISTS uq_users_restaurant_email;
DROP INDEX IF EXISTS idx_users_restaurant;
ALTER TABLE sma_users DROP CONSTRAINT IF EXISTS sma_users_restaurant_id_fkey;
ALTER TABLE sma_users DROP COLUMN IF EXISTS restaurant_id;

ALTER TABLE sma_users ADD CONSTRAINT uq_users_username UNIQUE (username);
CREATE UNIQUE INDEX uq_users_email ON sma_users(email) WHERE email IS NOT NULL;
