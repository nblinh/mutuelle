package com.harmonie.irma.type;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class JsonStringArrayType implements UserType {

		protected static final int[] SQL_TYPES = { Types.ARRAY };

		@Override
		public Object assemble(Serializable cached, Object owner) throws HibernateException {
		    return this.deepCopy(cached);
		}

		@Override
		public Object deepCopy(Object value) throws HibernateException {
		    return value;
		}

		@Override
		public Serializable disassemble(Object value) throws HibernateException {
		    return (Double[]) this.deepCopy(value);
		}

		@Override
		public boolean equals(Object x, Object y) throws HibernateException {

		    if (x == null) {
		        return y == null;
		    }
		    return x.equals(y);
		}

		@Override
		public int hashCode(Object x) throws HibernateException {
		    return x.hashCode();
		}

		@Override
		public boolean isMutable() {
		    return true;
		}

		@Override
		public Object replace(Object original, Object target, Object owner)       throws HibernateException {
		    return original;
		}

		@Override
		public Class<Double[]> returnedClass() {
		    return Double[].class;
		}

		@Override
		public int[] sqlTypes() {
		    return new int[] { Types.ARRAY };
		}

		@Override
		public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
				throws HibernateException, SQLException {
			if (rs.wasNull()) {
		        return null;
		    }
		    if(rs.getArray(names[0]) == null){
		        return new Double[0];
		    }

		    Array array = rs.getArray(names[0]);
		    JSONParser parser = new JSONParser();
		    JSONArray jsonArray=null;
			try {
				jsonArray = (JSONArray)parser.parse(array.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		    String[] javaArray = new String[jsonArray.size()];
		    for(int i=0;i<jsonArray.size();i++) {
		    	javaArray[i]=(String) jsonArray.get(i);
		    }
		   
		    return javaArray;
		}

		@Override
		public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
				throws HibernateException, SQLException {
			Connection connection = st.getConnection();
		    if (value == null) {
		    	st.setNull(index, SQL_TYPES[0]);
		    } else {
		        String[] castObject = (String[]) value;
		        Array array = connection.createArrayOf("String", castObject);
		        st.setArray(index, array);
		    }
			
		}
}