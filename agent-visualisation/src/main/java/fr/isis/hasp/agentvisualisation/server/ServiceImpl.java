package fr.isis.hasp.agentvisualisation.server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.isis.hasp.agentvisualisation.client.IService;
import fr.isis.hasp.agentvisualisation.shared.Evenement;

public class ServiceImpl extends RemoteServiceServlet implements IService {

	private static final long serialVersionUID = 1L;
	private SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATE_SQL);
	private SimpleDateFormat format_base = new SimpleDateFormat(
			FORMAT_DATE_SQL_BASE);
	public static final String FORMAT_DATE_SQL = "dd/MM/yyyy - HH:mm:ss";
	public static final String FORMAT_DATE_SQL_BASE = "yyyy-MM-dd HH:mm:ss";

	public ServiceImpl() {
	}

	public ArrayList<Evenement> getEvenements(Date dateDebut, Date dateFin,
			ArrayList<String> categories) {
		ArrayList<Evenement> result = new ArrayList<Evenement>();

		Connection conn = null;

		try {
			String userName = "hasp_usr";
			String password = "hasp_pwd";
			String url = "jdbc:mysql://localhost/hasp";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Database connection established");
		} catch (Exception e) {
			System.err.println("Cannot connect to database server");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					String querry = "select * from message where categorieMessage in(";

					if (categories == null
							|| (categories != null && categories.size() == 0)) {
						System.out.println(" > 0");
						return result;
					} else {
						int i = 0;
						for (String c : categories) {
							if (i != 0)
								querry += ", ";
							querry += "'" + c + "'";
							i++;
						}
						querry += ") ";

						if (dateDebut != null && dateFin == null) {
							System.out.println(" > 1");
							querry += "and dateMessage > '"
									+ format_base.format(dateDebut) + "'";
						} else if (dateDebut == null && dateFin != null) {
							System.out.println(" > 2");
							querry += "and dateMessage < '"
									+ format_base.format(dateFin) + "'";
						} else if (dateDebut != null && dateFin != null) {
							System.out.println(" > 3");
							querry += "and dateMessage > '"
									+ format_base.format(dateDebut)
									+ "' and dateMessage < '"
									+ format_base.format(dateFin) + "'";
						}
					}

					System.out.println(querry);

					CallableStatement stmt = conn.prepareCall(querry);
					ResultSet rs = stmt.executeQuery();

					while (rs.next()) {
						Evenement evt = new Evenement();

						evt.setCategorie(rs.getString("categorieMessage"));

						String dateS = rs.getString("dateMessage");
						String dateSub = dateS.substring(0, 19);

						Date date = format_base.parse(dateSub);
						String dateIHM = format.format(date);

						evt.setDateString(dateIHM);
						evt.setDate(date);

						evt.setNumero(rs.getInt("numeroCapteur"));
						evt.setValeur(rs.getFloat("message"));

						result.add(evt);
					}

					conn.close();
					System.out.println("Database connection terminated");
				} catch (Exception e) { /* ignore close errors */
				}
			}
		}

		return result;
	}

	public Integer getCountEvenements(Date dateDebut, Date dateFin,
			ArrayList<String> categories) {
		int result = 0;

		Connection conn = null;

		try {
			String userName = "hasp_usr";
			String password = "hasp_pwd";
			String url = "jdbc:mysql://localhost/hasp";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Database connection established");
		} catch (Exception e) {
			System.err.println("Cannot connect to database server");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					String querry = "select * from message where categorieMessage in(";

					if (categories == null
							|| (categories != null && categories.size() == 0)) {
						System.out.println(" > 0");
						return result;
					} else {
						int i = 0;
						for (String c : categories) {
							if (i != 0)
								querry += ", ";
							querry += "'" + c + "'";
							i++;
						}
						querry += ") ";

						if (dateDebut != null && dateFin == null) {
							System.out.println(" > 1");
							querry += "and dateMessage > '"
									+ format_base.format(dateDebut) + "'";
						} else if (dateDebut == null && dateFin != null) {
							System.out.println(" > 2");
							querry += "and dateMessage < '"
									+ format_base.format(dateFin) + "'";
						} else if (dateDebut != null && dateFin != null) {
							System.out.println(" > 3");
							querry += "and dateMessage > '"
									+ format_base.format(dateDebut)
									+ "' and dateMessage < '"
									+ format_base.format(dateFin) + "'";
						}
					}

					System.out.println(querry);

					CallableStatement stmt = conn.prepareCall(querry);
					ResultSet rs = stmt.executeQuery();

					while (rs.next()) {
						result++;
					}

					conn.close();
					System.out.println("Database connection terminated");
				} catch (Exception e) { /* ignore close errors */
				}
			}
		}
		return result;
	}

	public ArrayList<Evenement> getDerniersEvenements(ArrayList<String> categories) {
		ArrayList<Evenement> result = new ArrayList<Evenement>();

		Connection conn = null;

		try {
			String userName = "hasp_usr";
			String password = "hasp_pwd";
			String url = "jdbc:mysql://localhost/hasp";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Database connection established");
		} catch (Exception e) {
			System.err.println("Cannot connect to database server");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					String querry = "select * from message where categorieMessage in(";

					if (categories == null
							|| (categories != null && categories.size() == 0)) {
						System.out.println(" > 0");
						return result;
					} else {
						int i = 0;
						for (String c : categories) {
							if (i != 0)
								querry += ", ";
							querry += "'" + c + "'";
							i++;
						}
						querry += ") order by idMessage desc";

					}

					System.out.println(querry);

					CallableStatement stmt = conn.prepareCall(querry);
					ResultSet rs = stmt.executeQuery();

					int i = 0;
					while (rs.next() && i < 20) {
						Evenement evt = new Evenement();

						evt.setCategorie(rs.getString("categorieMessage"));

						String dateS = rs.getString("dateMessage");
						String dateSub = dateS.substring(0, 19);

						Date date = format_base.parse(dateSub);
						String dateIHM = format.format(date);

						evt.setDateString(dateIHM);
						evt.setDate(date);

						evt.setNumero(rs.getInt("numeroCapteur"));
						evt.setValeur(rs.getFloat("message"));

						result.add(evt);
						i++;
					}

					conn.close();
					System.out.println("Database connection terminated : "+result.size());
				} catch (Exception e) { /* ignore close errors */
				}
			}
		}

		return result;
	}

}