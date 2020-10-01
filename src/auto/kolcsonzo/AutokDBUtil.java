package auto.kolcsonzo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;


public class AutokDBUtil {
	
	
private DataSource dataSource;
	
	public AutokDBUtil(DataSource theDataSource) {	
		dataSource = theDataSource;	
	}
	
	public List<Autok> getAutok() throws Exception{
		
		List<Autok> autok = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			myConn = dataSource.getConnection();
			
			String sql = "select * from autok";
			
			myStmt = myConn.createStatement();
				
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()) {
				
				int id = myRs.getInt("id");
				String marka = myRs.getString("marka");
				String rendSzam = myRs.getString("rendszam");
				String tipus = myRs.getString("tipus");
				String statusz = myRs.getString("statusz");
				String kolcsonzesiDij = myRs.getString("kolcsonzesi_dij");
				
				
				Autok tempAutok = new Autok(id, marka, rendSzam, tipus, statusz, kolcsonzesiDij);
				
				autok.add(tempAutok);
			}
			
			return autok;
			
		}
		finally {
			
			close(myConn, myStmt, myRs);
			
		}
		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   
			}
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
			
		}
		
	}

	public void addAutok(Autok theAutok) throws Exception{
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			
			myConn = dataSource.getConnection();
			
			String sql = "insert into autok "
						+ "(marka, rendszam, tipus, statusz, kolcsonzesi_dij)"
						+ "values (?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, theAutok.getmarka());
			myStmt.setString(2, theAutok.getrendSzam());
			myStmt.setString(3, theAutok.gettipus());
			myStmt.setString(4, theAutok.getstatusz());
			myStmt.setString(5, theAutok.getkolcsonzesiDij());
			
			myStmt.execute();
			
		}
		finally {
			
			close(myConn, myStmt, null);

		}

	}

	public Autok getAutok(String theAutokId) throws Exception{
		
		Autok theAutok = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int autokId;
		
		try {
			
			autokId = Integer.parseInt(theAutokId);
			
			myConn = dataSource.getConnection();
			
			String sql = "select * from autok where id=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, autokId);
			
			myRs = myStmt.executeQuery();
			
			if(myRs.next()) {
				String marka = myRs.getString("marka");
				String rendSzam = myRs.getString("rendszam");
				String tipus = myRs.getString("tipus");
				String statusz = myRs.getString("statusz");
				String kolcsonzesiDij = myRs.getString("kolcsonzesi_dij");
			
				theAutok = new Autok(autokId, marka, rendSzam, tipus, statusz, kolcsonzesiDij);
	
			}
			else {
				
				throw new Exception("Nem találtam autot aminek az id-ja: " + autokId);
			}
			
			return theAutok;
		}
		finally {
			
			close(myConn, myStmt, myRs);
			
		}
		
	}

	public void updateAutok(Autok theAutok) throws Exception{
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			
		myConn = dataSource.getConnection();
		
		String sql = "update autok "
					+"set marka=?, rendszam=?, tipus=?, statusz=?, kolcsonzesi_dij=? "
					+"where id=?";
		
		myStmt = myConn.prepareStatement(sql);
		
		myStmt.setString(1, theAutok.getmarka());
		myStmt.setString(2, theAutok.getrendSzam());
		myStmt.setString(3, theAutok.gettipus());
		myStmt.setString(4, theAutok.getstatusz());
		myStmt.setString(5, theAutok.getkolcsonzesiDij());
		
		myStmt.setInt(6,theAutok.getId());
		
		myStmt.execute();
		}
		finally {
			
			close(myConn, myStmt, null);
		}

	}

	public void deleteAutok(String theAutokId) throws Exception{
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			int autokId = Integer.parseInt(theAutokId);
			
			myConn = dataSource.getConnection();
			
			String sql = "delete from autok where id=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, autokId);
			
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);	
		}
	}

}
