package BDD;

import java.sql.*;


import java.text.ParseException;
import PFE.Conteneur;
import PFE.DATE;
import PFE.PRODUIT;
import PFE.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConnectionBD {

	public static Connection Connect() {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/port";
			String username = "root";
			String password = "rouba";
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Erreur :   " + e);
		}
		return null;

	}

	// ______________________________________ LA BASE DE CONTENEURS
	// _____________________________________//

	public static int save(Conteneur Cont) {
		int stt = 0;
		try {
			String SQL = "INSERT INTO CONTENEUR VALUES (?,?,?,?,?) ";
			Connection con = ConnectionBD.Connect();
			PreparedStatement stat = con.prepareStatement(SQL);
			stat.setInt(1, Integer.valueOf(Cont.getNumero()));
			stat.setString(2, Cont.getP().toString());
			stat.setString(3, Cont.getDimension().toString());
			stat.setString(4, Cont.getD_arrive().toString());
			stat.setString(5, Cont.getD_depart().toString());
			stt = stat.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println("Erreur :   " + e);
			e.printStackTrace();
		}
		return stt;

	}

	public static int modifier(Conteneur Cont) throws ParseException {
		int stt = 0;
		try {
			String SQL = "UPDATE CONTENEUR SET ProduitC=?, Typec=? ,d_arrive=? ,d_depart=? WHERE num=?";
			Connection con = ConnectionBD.Connect();
			PreparedStatement stat = con.prepareStatement(SQL);
			stat.setString(1, Cont.getP().toString());
			stat.setString(2, Cont.getDimension().toString());
			stat.setString(3, Cont.getD_arrive().toString());
			stat.setString(4, Cont.getD_depart().toString());
			stat.setInt(5, Integer.valueOf(Cont.getNumero()));
			stt = stat.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erreur :   " + e);
			e.printStackTrace();
		}
		return stt;

	}

	public static int DELETE(int id) {
		int stt = 0;
		try {
			String SQL = "delete from Conteneur where num =?";

			Connection con = ConnectionBD.Connect();
			PreparedStatement stat = null;
			stat = con.prepareStatement(SQL);
			stat.setInt(1, id);
			stt = stat.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erreur :   " + e);
			e.printStackTrace();
		}
		return stt;
	}

	public static Conteneur getConteneur(int ide) throws ParseException {
		
		Conteneur Cont = new Conteneur();
		try {
			String SQL = "SELECT * FROM CONTENEUR WHERE num = ?";
			Connection con = ConnectionBD.Connect();
			PreparedStatement stat = con.prepareStatement(SQL);
			stat.setInt(1, ide);
			ResultSet rst = stat.executeQuery();
			if (rst.next()) {
				Cont.setNumero(rst.getString(1));
				Cont.setP(PRODUIT.valueOf(rst.getString(2)));
				Cont.setDimension(Type.valueOf(rst.getString(3)));
				Cont.setD_arrive(new DATE(rst.getString(4)));
				Cont.setD_depart(new DATE(rst.getString(5)));
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Erreur :   " + e);
			e.printStackTrace();
		}
		return Cont;

	}

	public static ObservableList<Conteneur> getDatausers() {
		Connection con = ConnectionBD.Connect();
		ObservableList<Conteneur> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = con.prepareStatement("select * from Conteneur");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				DATE d = new DATE(rs.getString(4));
				DATE d1 = new DATE(rs.getString(5));
				list.add(new Conteneur("C".concat(rs.getString(1)), PRODUIT.valueOf(rs.getString(2)),
						Type.valueOf(rs.getString(3)), d, d1));
			}
		} catch (Exception e) {
		}
		return list;
	}

	// ______________________________________ LA BASE DE NOMBRES
	// _____________________________________//

	public static int modifiernombr(int VAL1, int VAL2) throws ParseException {
		int stt = 0;
		try {
			String SQL = "UPDATE NOMBRES  SET nombpiles=? ,nombconteneurs=? WHERE id=?";
			Connection con = ConnectionBD.Connect();
			PreparedStatement stat = con.prepareStatement(SQL);
			stat.setInt(1, VAL1);
			stat.setInt(2, VAL2);
			stat.setInt(3, 1);
			stt = stat.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erreur :   " + e);
			e.printStackTrace();
		}
		return stt;

	}
	public static void modifiernombrc(int VAL1) throws ParseException {
	
		try {
			String SQL = "UPDATE NOMBRES  SET nombpiles=? WHERE id=?";
			Connection con = ConnectionBD.Connect();
			PreparedStatement stat = con.prepareStatement(SQL);
			stat.setInt(1, VAL1);
			stat.setInt(2, 1);
			stat.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erreur :   " + e);
			e.printStackTrace();
		}
	}
	public static void  modifiernombrINdiv(int VAL1) throws ParseException {
		
		try {
			String SQL = "UPDATE NOMBRES  SET nombindiv=? WHERE id=?";
			Connection con = ConnectionBD.Connect();
			PreparedStatement stat = con.prepareStatement(SQL);
			stat.setInt(1, VAL1);
			stat.setInt(2, 1);
			stat.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erreur :   " + e);
			e.printStackTrace();
		}
		

	}
	public static int nombindiv() {
		int N = 0;
		try {
			java.sql.Connection con1 = ConnectionBD.Connect();
			String sql1 = " SELECT nombindiv FROM NOMBRES ";
			PreparedStatement stat1 = con1.prepareStatement(sql1);
			ResultSet rs1 = stat1.executeQuery();
			rs1.next();
			N = rs1.getInt(1);
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return N;
	}

	public static int nombcont() {
		int N = 0;
		try {
			java.sql.Connection con1 = ConnectionBD.Connect();
			String sql1 = " SELECT nombconteneurs FROM NOMBRES ";
			PreparedStatement stat1 = con1.prepareStatement(sql1);
			ResultSet rs1 = stat1.executeQuery();
			rs1.next();
			N = rs1.getInt(1);
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return N;
	}

	public static int nombpile() {
		int N = 0;
		try {
			java.sql.Connection con1 = ConnectionBD.Connect();
			String sql1 = " SELECT nombpiles FROM NOMBRES ";
			PreparedStatement stat1 = con1.prepareStatement(sql1);
			ResultSet rs1 = stat1.executeQuery();
			rs1.next();
			N = rs1.getInt(1);
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return N;
	}

	// ______________________________________ LA BASE DE Piles
	// _____________________________________//
	public static int modifierP(int VAL) throws ParseException {
		int stt = 0;
		try {
			String SQL = "UPDATE pile  SET nombreC=? WHERE nombreC IS NOT NULL";
			Connection con = ConnectionBD.Connect();
			PreparedStatement stat = con.prepareStatement(SQL);
			stat.setInt(1, VAL);

			stt = stat.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erreur :   " + e);
			e.printStackTrace();
		}
		return stt;

	}
	public static void  modifierPc(int VAL) throws ParseException {
		
		try {
			String SQL = "UPDATE pile  SET nombreC=? WHERE nombreC IS NOT NULL";
			Connection con = ConnectionBD.Connect();
			PreparedStatement stat = con.prepareStatement(SQL);
			stat.setInt(1, VAL);
            stat.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erreur :   " + e);
			e.printStackTrace();
		}
		

	}


	public static int lengthP() {
		int count = 0;
		try {
			java.sql.Connection con1 = ConnectionBD.Connect();
			String sql1 = " SELECT COUNT(*) FROM pile ";
			PreparedStatement stat1 = con1.prepareStatement(sql1);
			ResultSet rs1 = stat1.executeQuery();
			rs1.next();
			count = rs1.getInt(1);
          } catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return count;

	}

	// ______________________________________ LA BASE DE ZONE
	// _____________________________________//

	public static void modifierZone(int VAL1, String VAL2, int VAL3, int VAL4) throws ParseException {

		try {
			String SQL = "UPDATE zonec SET typeZ= ?,nombreP=?,nombreC=? WHERE numeroZ=?";
			Connection con = ConnectionBD.Connect();
			PreparedStatement stat = con.prepareStatement(SQL);
			stat.setString(1, VAL2);
			stat.setInt(2, VAL3);
			stat.setInt(3, VAL4);
			stat.setInt(4, VAL1);
			stat.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erreur :   " + e);
			e.printStackTrace();
		}
	}

	public static void modifierZonereg(int VAL1, int VAL2) throws ParseException {

		try {
			String SQL = "UPDATE zonec SET nombreC=? WHERE numeroZ=?";
			Connection con = ConnectionBD.Connect();
			PreparedStatement stat = con.prepareStatement(SQL);
			stat.setInt(1, VAL2);
			stat.setInt(2, VAL1);
			stat.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erreur :   " + e);
			e.printStackTrace();
		}

	}

}
