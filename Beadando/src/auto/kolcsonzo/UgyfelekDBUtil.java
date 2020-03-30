package auto.kolcsonzo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class UgyfelekDBUtil {
	
	private DataSource dataSource;
	
	public UgyfelekDBUtil(DataSource theDataSource) {	
		dataSource = theDataSource;	
	}
	
	public List<Ugyfelek> getUgyfelek() throws Exception{
		
		List<Ugyfelek> ugyfelek = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			myConn = dataSource.getConnection();
			
			String sql = "select * from ugyfelek";
			
			myStmt = myConn.createStatement();
				
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()) {
				
				int id = myRs.getInt("id");
				String igazolvanySzam = myRs.getString("igazolvany_szam");
				String vezetekNev = myRs.getString("vezetek_nev");
				String keresztNev = myRs.getString("kereszt_nev");
				String telefonSzam = myRs.getString("telefon_szam");
				String email = myRs.getString("email");
				
				
				Ugyfelek tempUgyfelek = new Ugyfelek(id, igazolvanySzam, vezetekNev, keresztNev, telefonSzam, email);
				
				ugyfelek.add(tempUgyfelek);
			}
			
			return ugyfelek;
			
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

	public void addUgyfelek(Ugyfelek theUgyfelek) throws Exception{
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			
			myConn = dataSource.getConnection();
			
			String sql = "insert into ugyfelek "
						+ "(igazolvany_szam, vezetek_nev, kereszt_nev, telefon_szam, email)"
						+ "values (?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, theUgyfelek.getIgazolvanySzam());
			myStmt.setString(2, theUgyfelek.getVezetekNev());
			myStmt.setString(3, theUgyfelek.getKeresztNev());
			myStmt.setString(4, theUgyfelek.getTelefonSzam());
			myStmt.setString(5, theUgyfelek.getEmail());
			
			myStmt.execute();
			
		}
		finally {
			
			close(myConn, myStmt, null);

		}

	}

	public Ugyfelek getUgyfelek(String theUgyfelekId) throws Exception{
		
		Ugyfelek theUgyfelek = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int ugyfelekId;
		
		try {
			
			ugyfelekId = Integer.parseInt(theUgyfelekId);
			
			myConn = dataSource.getConnection();
			
			String sql = "select * from ugyfelek where id=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, ugyfelekId);
			
			myRs = myStmt.executeQuery();
			
			if(myRs.next()) {
				String igazolvanySzam = myRs.getString("igazolvany_szam");
				String vezetekNev = myRs.getString("vezetek_nev");
				String keresztNev = myRs.getString("kereszt_nev");
				String telefonSzam = myRs.getString("telefon_szam");
				String email = myRs.getString("email");
			
				theUgyfelek = new Ugyfelek(ugyfelekId, igazolvanySzam, vezetekNev, keresztNev, telefonSzam, email);
	
			}
			else {
				
				throw new Exception("Nem találtam ügyfelet aminek az id-ja: " + ugyfelekId);
			}
			
			return theUgyfelek;
		}
		finally {
			
			close(myConn, myStmt, myRs);
			
		}
		
	}

	public void updateUgyfelek(Ugyfelek theUgyfelek) throws Exception{
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			
		myConn = dataSource.getConnection();
		
		String sql = "update ugyfelek "
					+"set igazolvany_szam=?, vezetek_nev=?, kereszt_nev=?, telefon_szam=?, email=? "
					+"where id=?";
		
		myStmt = myConn.prepareStatement(sql);
		
		myStmt.setString(1, theUgyfelek.getIgazolvanySzam());
		myStmt.setString(2, theUgyfelek.getVezetekNev());
		myStmt.setString(3, theUgyfelek.getKeresztNev());
		myStmt.setString(4, theUgyfelek.getTelefonSzam());
		myStmt.setString(5, theUgyfelek.getEmail());
		
		myStmt.setInt(6,theUgyfelek.getId());
		
		myStmt.execute();
		}
		finally {
			
			close(myConn, myStmt, null);
		}

	}

	public void deleteUgyfelek(String theUgyfelekId) throws Exception{
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			int ugyfelekId = Integer.parseInt(theUgyfelekId);
			
			myConn = dataSource.getConnection();
			
			String sql = "delete from ugyfelek where id=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, ugyfelekId);
			
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);	
		}
	}
}
