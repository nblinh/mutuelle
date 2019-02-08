package com.harmonie.irma.type;

import org.hibernate.dialect.PostgreSQL94Dialect;
import org.springframework.context.annotation.Configuration;


import java.sql.Types;

@Configuration
public class PostgreSQL9JsonDialect extends PostgreSQL94Dialect {

	public PostgreSQL9JsonDialect() {
		this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
	}
}
