package com.second.ssyt.examinationpaper.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.second.ssyt.common.PageModel;
import com.second.ssyt.examinationpaper.entity.ExaminationPaperEntity;





public class ExaminationPaperDao {

	public static PageModel<ExaminationPaperEntity> list(ExaminationPaperEntity epe, int pageSize, int pageNo ){			    
		
		
		List<ExaminationPaperEntity> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//连接数据�?
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yec","root","123");
			List<Object> paramList = new ArrayList<>();
			StringBuilder sb = new StringBuilder();		
			sb.append("SELECT total_point, pass_point, total_minutes ");
			sb.append("FROM qes_exam_paper ");
			//判断传入实体的属性是不是默认值，如果不是，添加sql语句并将sql语句�??的�?�放入paramList
			if (StringUtils.isNotBlank(epe.getName())) {
				sb.append("WHERE name LIKE ? ");
				paramList.add("%" + epe.getName() + "%");
			}
			if (StringUtils.isNotBlank(epe.getName())) {
			  if (epe.getState() != 0) {
				  sb.append("AND state = ? ");
				  paramList.add(epe.getState());
			  }
		    }else{
				  if (epe.getState() != 0) {
					  sb.append("WHERE state = ? ");
					  paramList.add(epe.getState()); 
			  }
		    }
			sb.append("LIMIT ?,? ");			
			paramList.add((pageNo - 1) * pageSize);
			paramList.add(pageSize);
			//将paramList中的值拿出来放入sql中的？，并执�?
			System.out.println(paramList);
			System.out.println(sb.toString());
			preparedStatement = connection.prepareStatement(sb.toString());	
			if (paramList != null) {
				for (int i = 0; i < paramList.size(); i++) {
					preparedStatement.setObject(i + 1, paramList.get(i));
				}
			}
			//得到含有从数据库拿出来的数据的set集合
			resultSet = preparedStatement.executeQuery();
			//将数据一条一条的拿出来，放入�?个一个实体，将实体放入list集合
			while (resultSet.next()){
				ExaminationPaperEntity examinationPaperEntity = new ExaminationPaperEntity();
				examinationPaperEntity.setTotalPoint(resultSet.getInt("total_point"));
				examinationPaperEntity.setPassPoint(resultSet.getInt("pass_point"));
				examinationPaperEntity.setTotalMinutes(resultSet.getInt("total_minutes"));
				list.add(examinationPaperEntity);
			}
			
			//得到总记录数
			List<Object> paramList2 = new ArrayList<>();
			StringBuilder sb2 = new StringBuilder();
			sb2.append("SELECT COUNT(*) AS 'count' ");
			sb2.append("FROM qes_exam_paper ");
			if (StringUtils.isNotBlank(epe.getName())) {
				sb2.append("WHERE name LIKE ? ");
				paramList2.add("%" + epe.getName() + "%");
			}
			if (StringUtils.isNotBlank(epe.getName())) {
			  if (epe.getState() != 0) {
				  sb2.append("AND state = ? ");
				  paramList2.add(epe.getState());
			  }
		    }else{
				  if (epe.getState() != 0) {
					  sb2.append("WHERE state = ? ");
					  paramList2.add(epe.getState()); 
			  }
		    }
			System.out.println(paramList2);
			System.out.println(sb2.toString());
			preparedStatement = connection.prepareStatement(sb2.toString());
			if (paramList2 != null) {
				for (int i = 0; i < paramList2.size(); i++) {
					preparedStatement.setObject(i + 1, paramList2.get(i));
				}
			}
			resultSet = preparedStatement.executeQuery();
			int count = 1;
			if (resultSet.next()) {
				count = resultSet.getInt("count");
			}
			//创建实体PageModel并返�?
	     return new PageModel<ExaminationPaperEntity>(list, pageNo, pageSize, count); 
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			try {
			if (resultSet != null){			
					resultSet.close();			
			}
			if (preparedStatement != null){				
					preparedStatement.close();				
			}
			if (connection != null){				
					connection.close();								
			}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return null;					 			
	}
	
	    
}



