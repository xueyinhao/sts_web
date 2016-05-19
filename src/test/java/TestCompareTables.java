

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

/**
 * 
 * 测试对比数据库新增表及表字段增减
 * @author xueyinhao
 *
 */
public class TestCompareTables {
	
	public static Connection getConnection(String schema) {
		
		String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://192.168.1.11:3306/" + schema + "?useUnicode=true&characterEncoding=utf8";
	    String username = "xhsit";
	    String password = "sit1818";
	    
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
		
	}

	
	public static Map getTableInfo(Connection conn) throws Exception {
		
		Map tableMap = new HashMap();
		DatabaseMetaData metaData = conn.getMetaData();
		ResultSet rs = metaData.getTables(conn.getCatalog(), "xhsit", null, new String[]{"TABLE"});

		
		DatabaseMetaData metaData2 = null;
		ResultSet rs2 = null;
		while(rs.next()) {
		   String tableName = rs.getString("TABLE_NAME");
		   
		   metaData2 = conn.getMetaData();  
           rs2 = metaData.getColumns(null, "xhsit", tableName, null);  
           Map map = new HashMap();  
           while(rs2.next()){  
               String columnName = rs2.getString("COLUMN_NAME");//列名  
               String dataType  = rs2.getString("DATA_TYPE");//字段数据类型(对应java.sql.Types中的常量)  
               String typeName = rs2.getString("TYPE_NAME");//字段类型名称(例如：VACHAR2)  
               String columnSize = rs2.getString("COLUMN_SIZE");
               //System.out.println("column: " + columnName + "    data type: " + dataType + "    typeName" + typeName + "    columnSize: " + columnSize);
               map.put(columnName, dataType + ":" + typeName +":" + columnSize);  
           }  
           
           tableMap.put(tableName, map);
		}

		rs.close();
		rs2.close();
		conn.close();
		
		
		return tableMap;
	}
	
	
	public static void doCompare(Map sitMap, Map sit3Map) {
		
		for(Iterator it=sit3Map.keySet().iterator();it.hasNext();){//在sit3中有的表，而在sit中没有
			String table = (String)it.next();
			if(sitMap.get(table) == null){
				System.out.println("!!!!! table missed in sitMap :      " + table);
			}
			else {//都有此表，但数据不一样
				Map tmpMap = (Map)sitMap.get(table);
				Map tmp3Map = (Map)sit3Map.get(table);
				
				for(Iterator tmpIt=tmp3Map.keySet().iterator();tmpIt.hasNext();){
					String column = (String)tmpIt.next();
					if(tmpMap.get(column) == null){
						System.out.println("----- column: " + column + " is missed in table: " + table);
					}else{
						String columnContent1 = (String)tmpMap.get(column);
						String columnContent3 = (String)tmp3Map.get(column);
						if(!columnContent1.equals(columnContent3)){
							System.out.println("???? column: " + column + " not equals " + table);
						}
						
					}
				}
				
			}
		}
		
	}
	
	@Test
	public void testDB() {
		try{
		Map sitMap = getTableInfo(getConnection("sit"));
		Map sit3Map = getTableInfo(getConnection("sit3"));
		doCompare(sitMap,sit3Map);
		doCompare(sit3Map,sitMap);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}




