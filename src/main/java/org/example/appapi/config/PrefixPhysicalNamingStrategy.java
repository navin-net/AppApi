
package org.example.appapi.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class PrefixPhysicalNamingStrategy implements PhysicalNamingStrategy {

    private static final String PREFIX = "sma_";

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {

        if (name == null) return null;

        String tableName = name.getText();

        if (!tableName.startsWith(PREFIX)) {
            tableName = PREFIX + tableName;
        }

        return Identifier.toIdentifier(tableName, name.isQuoted());
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        return name;
    }

    @Override
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment context) {
        return name;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment context) {
        return name;
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment context) {
        return name;
    }
}
