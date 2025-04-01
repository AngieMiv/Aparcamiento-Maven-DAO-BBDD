package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class DaoUsersJdbc extends DaoJdbc implements DaoList<User>{

	public DaoUsersJdbc() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findOne(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertOne(User t) {
		// TODO Auto-generated method stub
		

		try {
				String sql = "insert into users (NAME,AGE,CITY) values (?,?,?)";
				PreparedStatement pst = getConn().prepareStatement(sql);
				pst.setString(1, t.getName());
				pst.setString(2, t.getAge()+"");
				pst.setString(3, t.getCity());
				pst.executeUpdate();

			} catch (SQLException e1) {
//				e1.printStackTrace();
				return false;
			}

		return true;
	}

	@Override
	public boolean deleteOne(String id) {

		String sql = "delete from users where ID=?";
		PreparedStatement pst;
		try {
			pst = getConn().prepareStatement(sql);
			pst.setString(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean updateOne(String key, User t) {
		try {
			String sql = "update users set NAME=?,AGE=?,CITY=? where ID=?";
			PreparedStatement pst = getConn().prepareStatement(sql);
			pst.setString(1, t.getName());
			pst.setString(2, t.getAge()+"");
			pst.setString(3, t.getCity());
			pst.setString(4, t.getId()+"");
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return false;
		}

		return true;

	}

	
	
	
}
